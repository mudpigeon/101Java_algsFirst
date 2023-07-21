package lib.other;

public class TextProcessor {
    public static String reverse(String s) {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N/2);
        String b = s.substring(N/2, N);
        return reverse(b) + reverse(a);
    }

    // 判断两个字符串之间环状关系
    public static boolean isCircularShift(String a, String b) {
        if (a.length() != b.length()) return false;
        if (a.concat(a).indexOf(b) < 0) return false;
        return true;
    }
}
