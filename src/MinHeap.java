import java.util.PriorityQueue;

/**
 * Created by julin on 2019-04-01 18:46
 */
public class MinHeap {
    private PriorityQueue<HeapEntity> queue;
    private int maxSize;

    public MinHeap(int maxSize, PriorityQueue<HeapEntity> queue) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    public void add(HeapEntity entity) {
        if (queue.size() < maxSize) {
            queue.add(entity);
        } else {
            // Min heap
            HeapEntity min = queue.peek();
            if (min != null && entity.getCount() > min.getCount()) {
                queue.poll();
                queue.add(entity);
            }
        }
    }

    public void merge(MinHeap minHeap){
        PriorityQueue<HeapEntity> priorityQueue = minHeap.getQueue();
        while (!priorityQueue.isEmpty()){
            this.add(priorityQueue.poll());
        }
    }

    public PriorityQueue<HeapEntity> getQueue() {
        return queue;
    }

    public void setQueue(PriorityQueue<HeapEntity> queue) {
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
