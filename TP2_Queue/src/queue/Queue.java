package queue;

public class Queue {
	
	private QueueElements elementsInQueue = new QueueElements();
	
	public boolean isEmpty() {
		return elementsInQueue.isEmpty();
	}
	public Queue add(Object cargo) {
		elementsInQueue.add(cargo);
		return this;
	}
	public Object take() {
		return elementsInQueue.take();
	}
	public Object head() {
		return elementsInQueue.head();
    }
	public int size() {
		return elementsInQueue.size();
	}
}
