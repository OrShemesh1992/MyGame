package PackmanGame;

import myGeom.Point3D;

public class Ghost {
	
	Point3D locationG;
	int ID, speed, radius;
	String type;
	
	public Ghost() {
		this.locationG = new Point3D(0,0,0);
		this.ID = 0;
		this.speed = 0;
		this.radius = 0;
		this.type = "";
	}
	
	public Ghost(Point3D p) {
		this.locationG = p;
		
	}
	
	public Ghost(Point3D p, int id, int s, int r, String t) {
		this.locationG = p;
		this.ID = id;
		this.speed = s;
		this.radius = r;
		this.type = t;
	}
	
	public Ghost(Ghost other) {
		this.locationG = other.locationG;
		this.ID = other.ID;
		this.speed = other.speed;
		this.radius = other.radius;
		this.type = other.type;
	}
	
	public Ghost(String LineCsv) {
		String[] Getdata=LineCsv.split(",");
		this.locationG = new Point3D(Double.parseDouble(Getdata[3]),Double.parseDouble(Getdata[2]),Double.parseDouble(Getdata[4]));
		this.ID = (int)Double.parseDouble(Getdata[1]);
		this.speed = (int)Double.parseDouble(Getdata[5]);
		this.radius = (int)Double.parseDouble(Getdata[6]);
		this.type = Getdata[0];
	}
	
	public Point3D getLocationG() {
		return this.locationG;
	}

	public void setLocationG(Point3D p) {
		this.locationG = new Point3D(p.x(),p.y(),p.z());
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void sedID(int id) {
		this.ID = id;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	public int getRadius() {
		return this.radius;
	}
	
	public void setRadius(int r) {
		this.radius = r;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public String toString() {
		return "Type: " + this.type + " Id: " + this.ID + " Speed: " + this.speed + " Radius: " + this.radius + " Point: " + this.locationG;
	}
}
