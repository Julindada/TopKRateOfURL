import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public final static String PATH = "/Users/julin/Documents/IdeaProjects/TopKRateOfURL/data";

    public static void main(String[] args) {
        int num = FileUtil.divideFile(Main.PATH + "/testdata", 1048576);
        if (num <= 0) return;
        List<MinHeap> queues = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            queues.add(new CalculateTopK().calculate(i));
        }
        MinHeap ret = queues.get(0);
        for (int i = 1; i < num; i++){
            MinHeap priorityQueue = queues.get(i);
            ret.merge(priorityQueue);
        }
        System.out.println("size:"+queues.get(0).getSize()+"|"+Arrays.toString(queues.get(0).getQueue().toArray()));
    }
}
