import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TaskQueue {
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition full = lock.newCondition();
	private Condition empty = lock.newCondition();
	private Queue<ITask> taskQueue;
	private int capacity;
	
	public TaskQueue(int capacity) {
		this.capacity = capacity;
		taskQueue = new LinkedList<ITask>();
	}
	
	public void enqueue(ITask task) throws InterruptedException
	{
		lock.lock();
		try {
			while(taskQueue.size() >= capacity) {
				full.await();
			}
			taskQueue.add(task);
			empty.signalAll();
		}finally {
			lock.unlock();
		}
	}
	
	public ITask dequeue() throws InterruptedException
	{
		lock.lock();
		try {
			while(taskQueue.size() == 0) {
				empty.await();
			}
			ITask task = taskQueue.remove();
			full.signalAll();
			return task;
		}finally {
			lock.unlock();
		}
	}
	
}
