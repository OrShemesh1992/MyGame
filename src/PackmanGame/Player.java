package PackmanGame;

import myGeom.Point3D;

public class Player {
	
	Point3D locationPL;
	int score, radius, speed, ID;
	String type;
	
	public Player() {
		this.locationPL = new Point3D(0,0,0);
		this.score = 0;
		this.speed = 0;
		this.radius = 0;
		this.ID = 0;
		this.type = "M";
	}

	public Player (String LineCsv) {
		String[] Getdata=LineCsv.split(",");
		this.type = Getdata[0];
		this.ID = (int)Double.parseDouble(Getdata[1]);
		this.locationPL = new Point3D(Double.parseDouble(Getdata[3]),Double.parseDouble(Getdata[2]),Double.parseDouble(Getdata[4]));
		this.speed = (int)Double.parseDouble(Getdata[5]);
		this.radius = (int)Double.parseDouble(Getdata[6]);
	}
	
	public Player(Point3D p, int s) {
		this.locationPL = p;
		this.score = s;
	}
	
	public Player(Point3D p, int s, int r, int spe, int id, String t) {
		this.locationPL = p;
		this.score = s;
		this.radius = r;
		this.speed = spe;
		this.ID = id;
		this.type = t;
	}
	
	public Player(Player other) {
		this.locationPL = other.locationPL;
		this.score = other.score;
		this.speed = other.speed;
		this.radius = other.radius;
		this.ID = other.ID;
		this.type = other.type;
	}
	
	public Point3D getLocation() {
		return this.locationPL;
	}
	
	public void setLocation(Point3D p) {
		this.locationPL = new Point3D(p.x(),p.y(),p.z());
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int s) {
		this.score = s;
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
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int id) {
		this.ID = id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public String toString() {
		return "Type: " + this.type + " ID: " + this.ID + " Point: " + this.locationPL + " Speed: " + this.speed + " Radius: " + this.radius;
	}
}
