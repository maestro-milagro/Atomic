import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        beautyCheck(generate100000());
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static String[] generate100000() {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        return texts;
    }

    public static void beautyCheck(String[] texts) {
        AtomicInteger counter3 = new AtomicInteger(0);
        AtomicInteger counter4 = new AtomicInteger(0);
        AtomicInteger counter5 = new AtomicInteger(0);

        Thread length3 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if ((texts[i].length() == 3) && ((istPalindrom(texts[i].toCharArray())) || (isIdentical(texts[i].toCharArray())) || (isIncreases(texts[i].toCharArray())))) {
                    counter3.addAndGet(1);
                }
            }
            System.out.printf("Красивых слов с длиной 3: %s шт\n", counter3);
        });
        length3.start();
        Thread length4 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if ((texts[i].length() == 4) && ((istPalindrom(texts[i].toCharArray())) || (isIdentical(texts[i].toCharArray())) || (isIncreases(texts[i].toCharArray())))) {
                    counter4.addAndGet(1);
                }
            }
            System.out.printf("Красивых слов с длиной 4: %s шт\n", counter4);
        });
        length4.start();
        Thread length5 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if ((texts[i].length() == 5) && ((istPalindrom(texts[i].toCharArray())) || (isIdentical(texts[i].toCharArray())) || (isIncreases(texts[i].toCharArray())))) {
                    counter5.addAndGet(1);
                }
            }
            System.out.printf("Красивых слов с длиной 5: %s шт\n", counter5);
        });
        length5.start();

    }

    public static boolean istPalindrom(char[] word) {
        int i1 = 0;
        int i2 = word.length - 1;
        while (i2 > i1) {
            if (word[i1] != word[i2]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }

    public static boolean isIdentical(char[] word) {
        for (int i = 0; i < word.length; i++) {
            if (word[i] != word[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIncreases(char[] word) {
        for (int i = 1; i < word.length - 1; i++) {
            if (word[i + 1] < word[i]) {
                return false;
            }
        }
        return true;
    }
}
