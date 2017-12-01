package nick.Algorithms4th.test;

import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Method;

public class DoublingRatio {

    /**
     * 上次运行时间
     */
    protected double prev;

    protected void start(String className) {
        try {
            // 使用反射创建被测试类，类名通过程序参数传递进来
            Class<?> testClass = Class.forName(className);
            // 通过反射获取被测试类的 timeTrial 方法
            Method timeTrial = testClass.getMethod("timeTrial", int[].class);
            // 迭代进行倍率实验
            StdOut.println("对 " + testClass.getName() + " 进行倍率实验：");
            // 计算第一个参考时间
            int N = 125;
            // 调用静态方法时，obj 参数被忽略，留空即可
            prev = ((double[]) timeTrial.invoke(null, getScale(N)))[0];
            // 显示结果表格的表头
            printColumns();
            while (true) {
                N *= 2;
                // 进行本次测试
                double[] result = (double[]) timeTrial.invoke(null, getScale(N));
                // 显示本次测试结果
                printResult(N, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int[] getScale(int N) {
        return new int[]{N};
    }

    protected void printColumns() {
        StdOut.printf("%-16s %7s %5s\n", "N", "time(s)", "ratio");
    }

    protected void printResult(int N, double[] result) {
        double time = result[0];
        // 规模 N , 花费的时间 , 与上一次时间之比
        StdOut.printf("%-16d %7.2f %5.1f\n", N, time, time/prev);
        prev = time;
    }

    public static void main(String[] args) {
        DoublingRatio test = new DoublingRatio();
        test.start(args[0]);
    }

}
