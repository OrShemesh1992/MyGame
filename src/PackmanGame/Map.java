package PackmanGame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import MyCoords.MyCoords;
import myGeom.Point3D;

public class Map{
	public BufferedImage myImage;
	public BufferedImage Packman;
	public BufferedImage Fruit;
	public BufferedImage Ghost;
	public BufferedImage Box;
	public BufferedImage Player;
	public Point3D Start = new Point3D(35.202574,32.106046);
	public Point3D End = new Point3D(35.212405,32.101858);

	/**
	 * A constructor that create the map image, fruit image and packman image.
	 */
	public Map() {
		try {
			myImage = ImageIO.read(new File("Ariel1.png"));
			Packman = ImageIO.read(new File("Pack.png"));
			Fruit = ImageIO.read(new File("Fruit.png"));
			Ghost = ImageIO.read(new File("Ghost.png"));
			Box = ImageIO.read(new File("Box.png"));
			Player = ImageIO.read(new File("Player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * This function gets a gps point and convert it to a pixel point by using the height and width of the image.
	 * @param gps
	 * @return
	 */
	public Point3D toPixel(Point3D gps){
		double Width =  End.x()-Start.x();
		double high = Start.y()-End.y();
		double x = gps.x()-Start.x();
		double y = Start.y()-gps.y();
		x = (myImage.getWidth())*(x/Width);
		y = (myImage.getHeight())*(y/high);    
		return new Point3D( (int)x,  (int)y);
	}
	/**
	 * This function gets 2 points and checks if they are equal.
	 * @param p
	 * @param p1
	 * @return
	 */
	public boolean equalS(Point3D p, Point3D p1) {
		if(Math.abs(p.x() - p1.x()) <=1&&Math.abs(p.y() - p1.y()) <=1) {
			return true;
		}
		return false;
	}
	/**
	 * This function gets a pixel point and convert it to a gps point.
	 * @param pixel
	 */
	public Point3D toGps(Point3D pixel){
		double GpsX = (pixel.x()/myImage.getWidth())*(End.x()-Start.x())+Start.x();
		double GpsY = -((pixel.y()/myImage.getHeight())*(Start.y()-End.y())-Start.y());
		return new Point3D(GpsX,GpsY,0);
	}
	/**
	 * This function calculates the distance between 2 points by using the function we already created at MyCoords class.
	 * @param p1
	 * @param p2
	 */
	public double distancePixels(Point3D p1, Point3D p2) {
		Point3D X = toGps(p1);
		Point3D Y = toGps(p2);
		MyCoords m = new MyCoords();
		return m.distance3d(X, Y);
	}

	/**
	 * This function calculates the distance between 2 points, we use this function when in the 
	 * threadPackman function so that we could adjust the fruit location and the packman location,
	 * to the width and the height of the map image.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public double disToGUI(Point3D p1, Point3D p2) {
		p1= new Point3D(p1.x()*myImage.getWidth(),p1.y()*myImage.getHeight());
		p2= new Point3D(p2.x()*myImage.getWidth(),p2.y()*myImage.getHeight());
		Point3D X = toGps(p1);
		Point3D Y = toGps(p2);
		MyCoords m = new MyCoords();
		return m.distance3d(X, Y);
	}
	/**
	 * This function gets 2 gps points and calculate the north angle between them.
	 * @return the north angle.
	 */
	public double north_angle(Point3D gps0, Point3D gps1) {
		double ans = 0;
		double a_rad = Math.atan2((gps0.y()-gps1.y()), (gps0.x()-gps1.x()));
		double a_deg = Math.toDegrees(a_rad);
		if(a_deg<=90) ans = 90-a_deg;
		else ans = 450-a_deg;
		return ans;
	}
	/**
	 * This function gets 2 gps points and calculate the angle between them.
	 * @return the angle.
	 */
	public double Angle(Point3D gps1, Point3D gps2) {
		MyCoords m = new MyCoords();
		double deg=m.azimuth_elevation_dist(gps1, gps2)[0];
		if(deg<=90) deg = 90-deg;
		else deg = 450-deg;
		return deg;
	}
}