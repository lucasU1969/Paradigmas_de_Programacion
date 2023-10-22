package nemo;

public class East extends Directions {

    public Directions turnLeft(){
    	return new North();
    }

    public Directions turnRight(){
    	return new South();
    }

    public String toString(){
        return "East";
    }

	public Point2D directionVector() {
		return new Point2D(1,0);
	}
    
}
