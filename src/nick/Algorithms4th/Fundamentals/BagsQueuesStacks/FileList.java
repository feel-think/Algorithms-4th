package nick.Algorithms4th.Fundamentals.BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class FileList {

    private static void printPath(String pathString) {
        File path = new File(pathString);
        printPath(path, 0);
    }

    private static void printPath(File path, int layer) {
        // 输出当前路径
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < layer; i++) {
            indent.append("   ");
        }
        StdOut.println(indent + path.getName());
        // 如果当前路径是文件夹，输出子路径
        if (path.isDirectory()) {
            File[] childPaths = path.listFiles();
            for (File s:
                    childPaths) {
                printPath(s, layer + 1);
            }
        }
    }

    public static void main(String[] args) {
        printPath(args[0]);
    }

}
