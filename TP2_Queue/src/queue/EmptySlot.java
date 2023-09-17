package queue;

import static org.junit.Assert.fail;

public class EmptySlot extends Slot {
	
	static String EmptyQueue = "Queue is empty";

	public boolean isEmpty() {
	    return true;
	}
	public Object getElement() {
	    fail(EmptyQueue);
	    return null;
	}
}