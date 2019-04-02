import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Calculate top k rate of URL
 * <p>
 * Created by julin on 2019-04-01 15:30
 */
public class CalculateTopK {
    public static MinHeap calculateByMinHeap(int n) {
        Map<String, Integer> map = calculate(n);
        //MinHeap(time:NlogK) to sort todo OR quickSort(time:N)
        MinHeap minHeap = new MinHeap(100, new PriorityQueue<>(Comparator.comparing(URLEntity::getCount)));
        for (String key : map.keySet()) {
            minHeap.add(new URLEntity(key, map.get(key)));
        }
        return minHeap;
    }

    public static URLEntity[] calculateByQuickSort(int n) {
        return null;
    }

    public static Map<String, Integer> calculate(int n) {
        //index to constructe name
        int index = 0;
        //calculate rate of URL by HashMap todo OOM
        Map<String, Integer> map = new HashMap<>();
        File file = new File(Main.PATH + "/" + n + index);
        //traversal zipper
        while (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String lineData;
                while ((lineData = reader.readLine()) != null) {
                    if (map.containsKey(lineData)) {
                        map.put(lineData, map.get(lineData) + 1);
                    } else {
                        map.put(lineData, 1);
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //traversal next file
            file = new File(Main.PATH + n + (++index));
        }
        return map;
    }
}
