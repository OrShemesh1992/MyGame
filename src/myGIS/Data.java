package myGIS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import myGeom.Point3D;
public class Data implements Meta_data{
	String time="";
	String info="";


	/**
	 * this constructor initializing the variables to null.
	 */
	public Data() {
		this.time = "";
		this.info="";
	}
	/**
	 * This constructor get Time and information and initializes the variables.
	 */
	public Data(String time, String info) {
		this.time = time;
		this.info=info;
	}

	/**
	 * this constructor is a copy constructor, it get a data and initializes the variables in the data to the variables in the other data.
	 * @param other
	 */
	public Data(Data other) {
		this.time = other.time;
		this.info = other.info;

	}
	/**
	 * String toString
	 */
	public String toString()
	{
		return "Time: "+time+" information: "+info;
	}

	@Override
	public long getUTC()  {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date;
		try {
			date = sdf.parse(time);
			long timeInMillis = date.getTime();
			 return timeInMillis;
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return 0;
	}


	@Override
	public Point3D get_Orientation() {
		return null;
	}
	public static void main(String[] args) {
		Data a=new Data("29/10/2014 18:10","stammmmmm");
		System.out.println(a.getUTC());
		System.out.println(a);


	}
}
