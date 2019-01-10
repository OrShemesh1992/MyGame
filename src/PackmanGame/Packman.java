package PackmanGame;
import myGeom.Point3D;
public class Packman {

	Point3D locationP;
	int speed, ID, radius;
	String type;
	
	/**
	 * A default constructor.
	 */
	public Packman() {
		this.locationP = new Point3D(0,0);
		this.speed = 0;
		this.ID = 0;
		this.radius = 0;
		this.type = "";
	}

	/**
	 * A constructor that gets 5 element and initializing the variables.
	 * @param p
	 * @param speed
	 * @param id
	 * @param radius
	 * @param type
	 */
	public Packman(Point3D p, int speed, int id, int radius, String type) {
		this.locationP = p;
		this.speed = speed;
		this.ID = id;
		this.radius = radius;
		this.type = type;
	}
	
	public Packman(Point3D p, int speed) {
		this.locationP = p;
		this.speed = speed;
	}

	/**
	 * A copy constructor.
	 * @param other
	 */
	public Packman(Packman other) {
		this.locationP = other.locationP;
		this.speed = other.speed;
		this.ID = other.ID;
		this.radius = other.radius;
		this.type = other.type;
	}

	/**
	 * A constructor that gets a line from csv file and initializing the variables.
	 * @param LineCsv
	 */
	public Packman (String LineCsv) {
		String[] Getdata=LineCsv.split(",");
		this.type = Getdata[0];
		this.ID = (int)Double.parseDouble(Getdata[1]);
		this.locationP = new Point3D(Double.parseDouble(Getdata[3]),Double.parseDouble(Getdata[2]),Double.parseDouble(Getdata[4]));
		this.speed = (int)Double.parseDouble(Getdata[5]);
		this.radius = (int)Double.parseDouble(Getdata[6]);
	}
	
	/**
	 * getters and setters methods.
	 * 
	 */
	public Point3D getLocation() {
		return this.locationP;
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getID() {
		return this.ID;
	}

	public int getRadius() {
		return this.radius;
	}

	public String getType() {
		return this.type;
	}
	
	public void setLocationF(double x , double y, double z) {
		this.locationP = new Point3D(x ,y, z);
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return "Type: " + this.type + " ID: " + this.ID + " Point: " + this.locationP + " Speed: " + this.speed + " Radius: " + this.radius;
	}
	
}
