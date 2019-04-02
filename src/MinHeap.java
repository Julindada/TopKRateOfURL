import java.util.PriorityQueue;

/**
 * Created by julin on 2019-04-01 18:46
 */
public class MinHeap {
    private PriorityQueue<URLEntity> queue;
    private int maxSize;

    public MinHeap(int maxSize, PriorityQueue<URLEntity> queue) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    public void add(URLEntity entity) {
        if (queue.size() < maxSize) {
            queue.add(entity);
        } else {
            // Min heap
            URLEntity min = queue.peek();
            if (min != null && entity.getCount() > min.getCount()) {
                queue.poll();
                queue.add(entity);
            }
        }
    }

    public void merge(MinHeap minHeap) {
        PriorityQueue<URLEntity> priorityQueue = minHeap.getQueue();
        while (!priorityQueue.isEmpty()) {
            this.add(priorityQueue.poll());
        }
    }

    public PriorityQueue<URLEntity> getQueue() {
        return queue;
    }

    public void setQueue(PriorityQueue<URLEntity> queue) {
        this.queue = queue;
    }

    public int getSize() {
        return queue.size();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
