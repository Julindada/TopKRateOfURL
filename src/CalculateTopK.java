import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by julin on 2019-04-01 15:30
 */
public class CalculateTopK {
    public MinHeap calculate(int n) {
        int index = 0;
        Map<String, Integer> map = new HashMap<>();
        File file = new File(Main.PATH + "/" + n + index);
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
            file = new File(Main.PATH + n + (++index));
        }
        MinHeap minHeap = new MinHeap(100, new PriorityQueue<>(Comparator.comparing(HeapEntity::getCount)));
        for (String key : map.keySet()) {
            minHeap.add(new HeapEntity(key, map.get(key)));
        }
        return minHeap;
    }
}
