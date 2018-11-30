package net.thumbtack.school.base;

import java.util.Locale;

public class StringOperations {

    public static int getSummaryLength(String[] strings) {
        int length = 0;
        for (String s : strings) {
            length += s.length();
        }
        return length;
    }

    public static String getFirstAndLastLetterString(String string) {
        return String.valueOf(string.charAt(0)) + string.charAt(string.length() - 1);
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        return string1.charAt(index) == string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        return string1.indexOf(str) == string2.indexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {
        return string1.equalsIgnoreCase(string2);
    }

    public static boolean isLess(String string1, String string2) {
        return string1.compareTo(string2) < 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {
        return string1.compareToIgnoreCase(string2) < 0;
    }

    public static String concat(String string1, String string2) {
        return string1.concat(string2);
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2) {
        if (string1.charAt(0) != string2.charAt(0)) return "";
        if (string1.startsWith(string2)) return string2;
        if (string2.startsWith(string1)) return string1;
        int len;
        if (string1.length() > string2.length()) len = string2.length();
        else len = string1.length();
        int i = 0;
        boolean notMatch = false;
        while (i < len && !notMatch) {
            if (string1.charAt(i) == string2.charAt(i)) i++;
            else notMatch = true;
        }
        return string1.substring(0, i);
    }

    public static String reverse(String string) {
        int length = string.length();
        if (length == 0 || length == 1) return string;
        char[] chars = string.toCharArray();
        char c;
        for (int i = 0; i < length / 2; i++) {
            c = chars[i];
            chars[i] = chars[length - i - 1];
            chars[length - i - 1] = c;
        }
        return String.valueOf(chars);
    }

    public static boolean isPalindrome(String string) {
        String reversed = reverse(string);
        return string.equals(reversed);
    }

    public static boolean isPalindromeIgnoreCase(String string) {
        String reversed = reverse(string);
        return string.equalsIgnoreCase(reversed);
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        int longest = 0;
        int longestIndex = 0;
        boolean hasPalindrome = false;
        for (int i = 0; i < strings.length; i++) {
            if (StringOperations.isPalindromeIgnoreCase(strings[i])) {
                hasPalindrome = true;
                if (strings[i].length() > longest) {
                    longestIndex = i;
                    longest = strings[i].length();
                }
            }
        }
        if (hasPalindrome) return strings[longestIndex];
        else return "";
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        String fragment1;
        String fragment2;
        if (string1.length() <= index + length) fragment1 = string1.substring(index, string1.length() - 1);
        else fragment1 = string1.substring(index, index + length - 1);
        if (string2.length() <= index + length) fragment2 = string2.substring(index, string2.length() - 1);
        else fragment2 = string2.substring(index, index + length - 1);
        return fragment1.equals(fragment2);
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1, String string2, char replaceInStr2, char replaceByInStr2) {
        string1 = string1.replace(replaceInStr1, replaceByInStr1);
        string2 = string2.replace(replaceInStr2, replaceByInStr2);
        return string1.equals(string2);
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1, String string2, String replaceInStr2, String replaceByInStr2) {
        string1 = string1.replace(replaceInStr1, replaceByInStr1);
        string2 = string2.replace(replaceInStr2, replaceByInStr2);
        return string1.equals(string2);
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        string = string.replace(" ", "");
        return StringOperations.isPalindromeIgnoreCase(string);
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {
        string1 = string1.trim();
        string2 = string2.trim();
        return string1.equals(string2);
    }

    public static String makeCsvStringFromInts(int[] array) {
        return makeCsvStringBuilderFromInts(array).toString();
    }

    public static String makeCsvStringFromDoubles(double[] array) {
        return makeCsvStringBuilderFromDoubles(array).toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        int length = array.length;
        if (length == 0) return new StringBuilder("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - 1; i++) {
            sb.append(array[i]).append(',');
        }
        return sb.append(array[length - 1]);
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        int length = array.length;
        if (length == 0) return new StringBuilder("");
        StringBuilder sb = new StringBuilder();
        Locale locale = Locale.ENGLISH;
        String format = "%.2f";
        for (int i = 0; i < length - 1; i++) {
            String strDouble = String.format(locale, format, array[i]);
            sb.append(strDouble).append(',');
        }
        return sb.append(String.format(locale, format, array[length - 1]));
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder sb = new StringBuilder(string);
        for (int i = positions.length - 1; i >= 0; i--) {
            sb.deleteCharAt(positions[i]);
        }
        return sb;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder sb = new StringBuilder(string);

        for (int i = positions.length - 1; i >= 0; i--) {
            sb.insert(positions[i], characters[i]);
        }
        return sb;
    }
}
