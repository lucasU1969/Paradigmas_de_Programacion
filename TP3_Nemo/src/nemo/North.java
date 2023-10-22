package nemo;

public class North extends Directions {

    public Directions turnLeft() {
    	return new West();
    }

    public Directions turnRight() {
    	return new East();
    }

    public String toString(){
        return "North";
    }

	public Point2D directionVector() {
		return new Point2D(0,1);
	}
    
}
