package PackmanGame;

import java.util.ArrayList;
import myGeom.Point3D;

public class Box {

	Point3D locationB2, locationB1; //B1=> left down , B2=> up right
	int ID, WTF;
	String type;
	ArrayList<Point3D> boxPoints;

	public Box() {
		this.locationB1 = new Point3D(0,0,0);
		this.locationB2 = new Point3D(0,0,0);
		this.ID = 0;
		this.type = "";
	}

	public Box(Point3D b1, Point3D b2, int id, String t) {
		this.locationB1 = b1;
		this.locationB2 = b2;
		this.ID = id;
		this.type = t;
	}

	public Box(Point3D b1, Point3D b2) {
		this.locationB1 = b1;
		this.locationB2 = b2;
	}

	public Box(Box other) {
		this.locationB1 = other.locationB1;
		this.locationB2 = other.locationB2;
		this.ID = other.ID;
		this.type = other.type;
	}

	public Box(String LineCsv) {
		String[] Getdata=LineCsv.split(",");
		this.locationB1 = new Point3D(Double.parseDouble(Getdata[3]),Double.parseDouble(Getdata[2]),Double.parseDouble(Getdata[4]));
		this.locationB2 = new Point3D(Double.parseDouble(Getdata[6]),Double.parseDouble(Getdata[5]),Double.parseDouble(Getdata[7]));
		this.ID = (int)Double.parseDouble(Getdata[1]);
		this.type = Getdata[0];
		this.WTF = (int)Double.parseDouble(Getdata[8]); 
	}

	public Point3D getLocationB1() {
		return this.locationB1;
	}

	public void setLocationB1(Point3D p) {
		this.locationB1 = new Point3D(p.x(),p.y(),p.z());
	}

	public Point3D getLocationB2() {
		return this.locationB2;
	}

	public void setLocationB2(Point3D p) {
		this.locationB2 = new Point3D(p.x(),p.y(),p.z());
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
		return "Type: " + this.type + " Id: " + this.ID +"  Start point: " + this.locationB1 + " End point: " + this.locationB2 + " WTF: " + this.WTF;
	}

	/**
	 * This function gets array of points which represent the four corners of a box and increases or decreases it according to its x-axis position.
	 * @return the array after increasing and decreasing points.
	 */
	public Point3D[] plusMeter(Point3D[] p) {
		p[0] = new Point3D(p[0].x()-1, p[0].y()+1);
		p[1] = new Point3D(p[1].x()+1, p[1].y()-1);
		p[2] = new Point3D(p[2].x()-1, p[2].y()-1);
		p[3] = new Point3D(p[3].x()+1, p[3].y()+1);
		return p;
	}

	/**
	 * This function gets one box which containing the point in the down left corner and the point in the up right corner,
	 * and calculate the point in the down right corner and the point in the up left corner. 
	 * @return the array of points of the four corners of the box.
	 */
	public Point3D[] boxCorners(Box b) {
		Point3D[] points = new Point3D[4];
		Point3D leftUp = new Point3D(b.locationB1.x(),b.locationB2.y());
		Point3D rightDown = new Point3D(b.locationB2.x(),b.locationB1.y());
		points[0] = b.locationB1;// left down
		points[1] = b.locationB2;//up right
		points[2] = leftUp;
		points[3] = rightDown;
		return points;
	}
	/**
	 * This function gets one point and array list of boxes and checks whether the point is inside the box.
	 * @return true if the point is in one of the boxes, otherwise false.
	 */
	public boolean IsIn(Point3D p, ArrayList<Box> b) {
		Point3D in = p;
		for (int i = 0; i < b.size(); i++) {
			Point3D[] boxPoint = boxCorners(b.get(i));
			if(in.x() > boxPoint[2].x() && in.x() < boxPoint[1].x() && in.y() > boxPoint[2].y() && in.y() < boxPoint[0].y()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This function gets array list of boxes and one point and if the point is equal to one of the
	 * boxes corner its increases or decreases it according to its x-axis position.
	 * @return the same point after increases or decreases.
	 */
	public Point3D corner(ArrayList<Box> box,Point3D p) {
		Map map = new Map();
		for (int i = 0; i < box.size(); i++) {
			if(map.equalS(p, box.get(i).locationB2)) {
				p = new Point3D(p.x()+35,p.y()-35);
			}
			else if(map.equalS(p, box.get(i).locationB1)) {
				p = new Point3D(p.x()-35,p.y()+35);
			}
			else if(Math.abs(p.x() - box.get(i).locationB1.x()) <=2&& Math.abs(p.y() - box.get(i).locationB2.y())<=2) {
				p = new Point3D(p.x()-35,p.y()-35);
			}
			else if(Math.abs(p.x() - box.get(i).locationB2.x()) <=2&& Math.abs(p.y() - box.get(i).locationB1.y())<=2) {
				p = new Point3D(p.x()+35,p.y()+35);
			}
		}
		return p;
	}
}