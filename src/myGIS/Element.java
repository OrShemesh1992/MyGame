package myGIS;
import MyCoords.MyCoords;
import myGeom.Geom_element;
import myGeom.Point3D;
public class Element implements  GIS_element{
	
	String MAC,SSID,AuthMode;
	String time;
	Point3D gps;
	
	/**
	 * this constructor initializing the variables to null.
	 */
	public Element ()
	{
		MAC="";
		SSID="";
		AuthMode="";
		time="";
		gps=null;
	}
	/**
	 * this constructor is a copy constructor, it get a data and initializes the variables in the data to the variables in the other data.
	 * @param other
	 */
	public Element (Element other)
	{
		this.MAC=other.MAC;
		this.SSID=other.SSID;
		this.AuthMode=other.AuthMode;
		this.time=other.time;
		this.gps=other.gps;
	}
	/**
	 * this constructor gets the parameters MAC, SSID, AuthMode, time and gps and initializes the variables.
	 */
	public Element (String MAC,String SSID,String AuthMode,String time,Point3D gps)
	{
		this.MAC=MAC;
		this.SSID=SSID;
		this.AuthMode=AuthMode;
		this.time=time;
		this.gps=gps;
	}
	public String toString() {
		String str=MAC+","+SSID+","+AuthMode+","+time+","+gps;
		return str;
	}
	@Override
	public Geom_element getGeom() {
		return gps;
	}
	/**
	 * here we get a line from the CSV file and initializes the parameters according to the data suitable to them.
	 */
	public Element LineCsvToElement(String LineCsv)
	{
		String[] Getdata=LineCsv.split(",");
		this.MAC=Getdata[0];
		this.SSID=Getdata[1];
		this.AuthMode=Getdata[2];
		this.time=Getdata[3];
		this.gps = new Point3D(Double.parseDouble(Getdata[7]),Double.parseDouble(Getdata[6]),Double.parseDouble(Getdata[8]));
		return new Element(this.MAC,this.SSID,this.AuthMode,this.time,this.gps);
	}
	/**
	 * this function gets a 3D point and adds it to a GPS vector by sending if to the add function in MyCoords class.
	 */
	public void translate(Point3D vec) {
		MyCoords a=new MyCoords();	
		this.gps=a.add(gps, vec);;
	}
	@Override
	public Meta_data getData() {
		return new Data(time,this.toString());
	}
}
