package MyCoords;

import myGeom.Point3D;

public class MyCoords implements coords_converter{	
    //https://keisan.casio.com/exec/system/1359533867
	//http://www.apsalin.com/convert-geodetic-to-cartesian.aspx
	public static final double radius=6371000;
	public static final double lonNorm = 0.847091;
	
	/**
	 * To calculate the radius of the point we the square root of each quadratic point,
	 * for the longitude we calculate the arctan of point y divided by the point x,
	 * for the latitude we calculate the arctan of the square root of each quadratic point.
	 * @param vector
	 * @return gps point3D
	 */
	public static Point3D ConvertToGps(Point3D vector) {	
		double	r = Math.sqrt(Math.pow(vector.x(), 2)+Math.pow(vector.y(), 2)+Math.pow(vector.z(), 2));
		double longitude= Math.atan(vector.y() / vector.x());
		double latidude = Math.atan2(Math.sqrt(Math.pow(vector.x(), 2)+Math.pow(vector.y(), 2)), vector.z());
		return new Point3D (r,Math.toDegrees(longitude),Math.toDegrees(latidude));	
	}
	/**
	 *Conversion between polar coordinates to cartesian coordinates.
	 * @param gps
	 * @return vector point3D
	 */
	public static Point3D ConvertToVector(Point3D gps) {			
		double x = gps.x()*Math.sin(gps.z()/180*Math.PI)*Math.cos(gps.y()/180*Math.PI);
		double y = gps.x()*Math.sin(gps.z()/180*Math.PI)*Math.sin(gps.y()/180*Math.PI);
		double z = gps.x()*Math.cos(gps.z()/180*Math.PI);
		return new Point3D (x,y,z);
	}
	/**
	 * In this function we get 2 3D point, one is polar coordinates and the other is cartesian coordinates.
	 * To add them to each other, first we need to convert the polar coordinates to a cartesian coordinates
	 * then add each point in one vector to the appropriate point in the other vector.
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		  Point3D gpsToVector = new Point3D(ConvertToVector(gps));
	       gpsToVector.add(local_vector_in_meter);
	       return (ConvertToGps(gpsToVector));
	}
	/**
	 * In this function we calculate between two 3D points.
	 * calculate the square root of sinus subtraction of two x points multiplied by the radius of the earth,
	 * and the two y points multiplied by the radius of the earth and the longitude.
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double d_x = Math.sin(Math.toRadians(gps1.x()-gps0.x()))*radius;
		double d_y = Math.sin(Math.toRadians(gps1.y()-gps0.y()))*radius*lonNorm;
		return Math.sqrt(Math.pow(d_x, 2)+Math.pow(d_y, 2));
	}
	/**
	 * In this function we calculate the vector in meters.
	 * calculate the square root of sinus subtraction of two x points multiplied by the radius of the earth,
	 * and the two y points multiplied by the radius of the earth and the longitude.
	 * Additionally we calculate the subtraction between the two z points.
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double m_x = Math.sin(Math.toRadians(gps1.x()-gps0.x()))*radius;
		double m_y = Math.sin(Math.toRadians(gps1.y()-gps0.y()))*radius*lonNorm;
		double m_z = gps1.z()-gps0.z();
		return new Point3D(m_x, m_y, m_z);
	}
	/**
	 * In this fuction we calculate the azimuths, elevation angles and the distance between two 3D points.
	 * to calculate the distance we use the distance3d function, for the elevation we calculate the arcsin of the subtraction of two z points
	 * and to calculate the azimuth we use the formula from spherical trigonometry for the azimuth angle between two peaks,
	 *  for starting points other than the poles.
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double dlon = Math.toRadians(gps1.y()-gps0.y());
		double dist = distance3d(gps0, gps1);
		double gps0_x = Math.toRadians(gps0.x());
		double gps1_x = Math.toRadians(gps1.x());
		double azi = Math.atan2(Math.sin(dlon)*Math.cos(gps1_x),(Math.cos(gps0_x)*Math.sin(gps1_x))-Math.sin(gps0_x)*Math.cos(gps1_x)*Math.cos(dlon));
		double elevation = Math.asin((gps1.z()-gps0.z())/dist);
		double[] azimuth_elevation_dist = {Math.toDegrees(azi)+360,Math.toDegrees(elevation),dist};
		return azimuth_elevation_dist;
	}
	/**
	 * In this function we check if the 3D point is valid, by checking if each of the three points is in the right range.
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		return (p.x()>-180 && p.x()<180 && p.y()<-90 && p.y()>90 && p.z()>-450);
	}
}
