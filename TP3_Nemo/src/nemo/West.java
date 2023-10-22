package nemo;

public class West extends Directions {

    public Directions turnLeft(){
    	return new South();
    }

    public Directions turnRight(){
    	return new North();
    }

    public String toString(){
        return "West";
    }
    
    public Point2D directionVector() {
    	return new Point2D(-1, 0);
    }

}
