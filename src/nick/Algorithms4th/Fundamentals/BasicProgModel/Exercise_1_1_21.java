package nick.Algorithms4th.Fundamentals.BasicProgModel;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Exercise_1_1_21 {

    /**
     * 从标准输入读取数据并打印表格
     * 命令：java -cp .;C:\Programming\Libraries\algs4.jar Exercise_1_1_32 < ../../../data/names_and_nums.txt
     */
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        List<Integer> nums1 = new ArrayList<Integer>();
        List<Integer> nums2 = new ArrayList<Integer>();
        // 逐行读取数据并存储起来
        while (!StdIn.isEmpty()) {
            names.add(StdIn.readString());
            nums1.add(StdIn.readInt());
            nums2.add(StdIn.readInt());
        }
        // 按表格样式打印出来
        for (int i = 0; i < names.size(); i++) {
            StdOut.printf(" %10s | %10d | %10d | %10.3f \n", names.get(i), nums1.get(i).intValue(),
                    nums2.get(i).intValue(), nums1.get(i).floatValue() / nums2.get(i).floatValue());
        }
    }

}
