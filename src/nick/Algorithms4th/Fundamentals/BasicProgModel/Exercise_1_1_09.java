package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdOut;

public class Exercise_1_1_09 {

    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("请输入需要转换为二进制的十进制数");
            return;
        }
        int num = Integer.parseInt(args[0]);
        /**
         * q1 =  a / 2, a0 =  a % 2, a =                                  q1 * 2^1 + a0 * 2^0
         * q2 = q1 / 2, a1 = q1 % 2, a =                       q2 * 2^2 + a1 * 2^1 + a0 * 2^0
         * q3 = q2 / 2, a2 = q2 % 2, a =            q3 * 2^3 + a2 * 2^2 + a1 * 2^1 + a0 * 2^0
         * q4 = q3 / 2, a3 = q3 % 2, a = q4 * 2^4 + a3 * 2^3 + a2 * 2^2 + a1 * 2^1 + a0 * 2^0
         */
        // 从第 0 位开始转换
        String binaryString = "";
        int q = num;
        while (q != 0) {
            // 计算余数，余数即是当前位的数值
            int r = q % 2;
            q = q / 2;
            // 拼接二进制字符串
            binaryString = r + binaryString;
        }
        StdOut.println(binaryString);
    }
}
