 package queue;

import java.util.ArrayList;

public class QueueElements {
	
	private ArrayList<Slot> elements = new ArrayList<Slot>();
	private int first = 1;
	
	public QueueElements() {
		elements.add(new EmptySlot());
	}
	public boolean isEmpty() {
		return this.lastElement().isEmpty();
	}
	public void add(Object cargo) {
		elements.add(first, new SlotWithElement(cargo));
	}
	public Object take() {
		return elements.remove(this.lastIndex()).getElement();
	}
	public Object head() {
		return this.lastElement().getElement();
	}
	public int size() {
		return this.lastIndex();
	}
	private int lastIndex() {
		return elements.size() - 1;
	}
	private Slot lastElement() {
		return elements.get(this.lastIndex());
	}
}