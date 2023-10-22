package nemo;

public class South extends Directions {

    public Directions turnLeft() {
    	return new East();
    }

    public Directions turnRight() {
    	return new West();
    }

    public String toString(){
        return "South";
    }

	public Point2D directionVector() {
		return new Point2D(0,-1);
	}

}
