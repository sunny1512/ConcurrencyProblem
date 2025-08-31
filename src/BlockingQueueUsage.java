import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueUsage {
	
	BlockingQueue<ITask> taskQueue;
	public BlockingQueueUsage() {
		taskQueue = new LinkedBlockingQueue<ITask>();
	}
	
	void enqueue(ITask task) throws InterruptedException{
		taskQueue.put(task);
	}
	
	ITask dequeue() throws InterruptedException{
		return taskQueue.take();
	}
	
}

