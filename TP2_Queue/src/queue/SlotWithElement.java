package queue;

public class SlotWithElement extends Slot{
    private Object element;
    public SlotWithElement(Object cargo) {
    	element = cargo;
    }
    public boolean isEmpty() {
        return false;
    }
    public Object getElement() {
        return element;
    }
}
