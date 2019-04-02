import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public final static String PATH = "/Users/julin/Documents/IdeaProjects/TopKRateOfURL/data";

    public static void main(String[] args) {
        calculateRateByMinHeap();
//        calculateRateByQuickSort();
    }

    /**
     * calculate rate by min heap
     */
    private static void calculateRateByMinHeap() {
        long start = System.currentTimeMillis();
        //divide big file
        int num = FileUtil.divideFile(Main.PATH + "/testdata", 1048576);
        if (num <= 0) return;
        List<MinHeap> queues = new ArrayList<>();
        //mock distributed system
        for (int i = 0; i < num; i++) {
            queues.add(CalculateTopK.calculateByMinHeap(i));
        }
        MinHeap ret = queues.get(0);
        //merge MinHeap
        for (int i = 1; i < num; i++) {
            MinHeap priorityQueue = queues.get(i);
            ret.merge(priorityQueue);
        }
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start) + "ms|" + Arrays.toString(queues.get(0).getQueue().toArray()));
    }

    /**
     * calculate rate by quick sort
     */
    private static void calculateRateByQuickSort(){
        long start = System.currentTimeMillis();
        //divide big file
        int num = FileUtil.divideFile(Main.PATH + "/testdata", 1048576);
        if (num <= 0) return;

        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start) + "ms");
    }
}
