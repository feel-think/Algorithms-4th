package nick.Algorithms4th.Fundamentals.DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_2_06 {

    /**
     * 自己实现的判断回环变位的方法
     */
    private static boolean isCircularRotation(String s, String t) {
        /**
         * 若 s he t 是回环变位关系，那么需要找到拆分 s 的正确位置，
         * 由于 t 的首字符可能在 s 中的多个位置出现，因此需要依次判断每个拆分位置是否正确
         */
        return isCircularRotation(s, t, 0);
    }

    private static boolean isCircularRotation(String s, String t, int fromIndex) {
        // 在 s 的指定范围中寻找 t 首字符的位置
        int head = s.indexOf(t.charAt(0), fromIndex);
        // 如果找到了该字符，就将 s 移位，然后和 t 比较
        if (head != -1) {
            String tmp = s.substring(head) + s.substring(0, head);
            if (tmp.equals(t)) return true;
            // 移位后的 s 若和 t 不相同，还有可能是拆分的位置不对，此时再从 head+1 处开始重新寻找
            else return isCircularRotation(s, t, head + 1);
        }
        // 如果在 s 的指定范围中没找到 t 的首字符，则说明不是回环变位
        return false;
    }

    /**
     * 官方网站给出的简洁版本
     * @param s
     * @param t
     * @return
     */
    private static boolean isCircularRotationOneLine(String s, String t) {
        /**
         * 如果 s 和 t 是回环变位关系，那么对于两个 s 连接起来以后的字符串 tmp，t 肯定是 tmp 的子字符串
         * 相反，如果 t 不是 tmp 的子字符串，那么 s 和 t 肯定不是回环变位关系
         */
        return s.length() == t.length() && s.concat(s).indexOf(t) != -1;
    }

    public static void main(String[] args) {
        // ACTGACG TGACGAC
        String s = args[0];
        String t = args[1];
        StdOut.println(isCircularRotation(s, t));
    }

}
