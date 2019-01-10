package PackmanGame;
import myGeom.Point3D;
public class Fruit {

	Point3D locationF;
	int weight, ID;
	String type;

	/**
	 * A defult constructor
	 */
	public Fruit() {
		this.ID = 0;
		this.weight = 0;
		this.locationF = new Point3D(0,0);
		this.type = "";
	}

	/**
	 * A constructor that gats 4 element and initializing the variables.
	 * @param weight
	 * @param ID
	 * @param p
	 * @param type
	 */
	public Fruit(int weight, int ID, Point3D p, String type) {
		this.ID = ID;
		this.weight = weight;
		this.locationF = p;
		this.type = type;
	}

	public Fruit(Point3D f) {
		this.locationF = f;
	}
	
	/**
	 * A copy constructor.
	 * @param other
	 */
	public Fruit(Fruit other) {
		this.ID = other.ID;
		this.weight = other.weight;
		this.locationF = other.locationF;
		this.type = other.type;
	}

	/**
	 * A constructor that gets a line from csv file and initializing the variables.
	 * @param LineCsv
	 */
	public Fruit (String LineCsv) {
		String[] Getdata=LineCsv.split(",");
		this.type = Getdata[0];
		this.ID = (int)Double.parseDouble(Getdata[1]);
		this.locationF = new Point3D(Double.parseDouble(Getdata[3]),Double.parseDouble(Getdata[2]),Double.parseDouble(Getdata[4]));
		this.weight = (int)Double.parseDouble(Getdata[5]);
	}

	/**
	 * getters and settrs methods.
	 * 
	 */
	public int getweight() {
		return this.weight;
	}

	public int getID() {
		return this.ID;
	}

	public Point3D getLocationF() {
		return this.locationF;
	}

	public String getType() {
		return this.type;
	}

	public void setweight(int weight) {
		this.weight = weight;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public void setLocationF(double x , double y,double z) {
		this.locationF = new Point3D(x ,y,z);
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return "Type: " + this.type + " Id: " + this.ID + " Weight: " + this.weight + " Point: " + this.locationF;
	}
}
