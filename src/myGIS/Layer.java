package myGIS;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
public class Layer implements GIS_layer {
	/**
	 * here we actualizing the data structures -Set-. 
	 */

	Set<GIS_element> SetElement = new HashSet<>();
	String time="";
	String info="";


	/**
	 * adding a new element to the data structures.
	 */
	@Override
	public boolean add(GIS_element arg0) {
		return SetElement.add(arg0);
	}

	/**
	 * adding a collection of elements to the data structures.
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		return SetElement.addAll(arg0);
	}

	/**
	 * clearing the data structures.
	 */
	@Override
	public void clear() {
		SetElement.clear();
	}

	/**
	 * checking if the data structures contains the object we gets.
	 */
	@Override
	public boolean contains(Object arg0) {
		return SetElement.contains(arg0);
	}

	/**
	 * checking it the data structures contains the collection of elements we gets.
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		return SetElement.containsAll(arg0);
	}

	/**
	 * checking it the data structures is empty.
	 */
	@Override
	public boolean isEmpty() {
		return SetElement.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return SetElement.iterator();
	}

	/**
	 * remove an object from the data structures.
	 */
	@Override
	public boolean remove(Object arg0) {
		return SetElement.remove(arg0);
	}

	/**
	 * removing all of the object in the collection from the data structures.
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		return SetElement.removeAll(arg0);
	}

	/**
	 * retain a specific collection at the data structures.
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		return SetElement.retainAll(arg0);
	}

	/**
	 * checking the size of the data structures.
	 */
	@Override
	public int size() {
		return SetElement.size();
	}

	@Override
	public Object[] toArray() {
		return SetElement.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return SetElement.toArray(arg0);
	}
	public String toString()
	{
		String s="";
		Iterator<GIS_element> iterator = this.iterator();
		while (iterator.hasNext()) {
			s+=iterator.next()+"\n";
		}
		return s;
	}
	@Override
	public Meta_data get_Meta_data() {
		return new Data(time,info);
	}
	/**
	 * this function 
	 * @return
	 */
	public String create_time()
	{
		//		"dd/MM/yyyy HH:mm"
		String s="";
		Date dateCreated = new Date();
		Calendar calendar = new GregorianCalendar();
		s=dateCreated.getDate()+"/"+(dateCreated.getMonth()+1)+"/"+
				calendar.get(calendar.YEAR)+" "+dateCreated.getHours()
				+":"+dateCreated.getMinutes();
		return s;
	}

	/**
	 * In this function we search for a csv file in our computer and sending it to the writeFileKML function.
	 */
	public void parseCSV(String csv,String information) {
		info=information;
		time=create_time();
		String csvFile = csv;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int i=0;
		ArrayList<String[]> a = new ArrayList<String[]>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				if(i>1)
				{
					Element temp=new Element();
					temp.LineCsvToElement(line);
					this.SetElement.add(temp);
				}
				a.add(line.split(cvsSplitBy));
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * In this function we get a csv file and converts it to a kml file. 
	 */
	public void writeFileKML(String kml) {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
				"<Document>";
		content.add(kmlstart);

		String kmlend = "</Document>" + "\n</kml>";
		try{
			FileWriter fw = new FileWriter(kml);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator It=this.iterator();
			while (It.hasNext())
			{
				Element a=(Element) It.next();
				String kmlelement ="<Placemark>\n" +
						"<name>"+a.SSID+"</name>\n" +
						"<description>"+a.MAC+", Capabilities:"+a.AuthMode+", Date:"+a.time+
						"</description>\n" +
						"<Point>\n" +
						"<coordinates>"+a.gps.x()+","+a.gps.y()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";
				content.add(kmlelement);
			}
			content.add(kmlend);
			String csv = content.toString().replace("[", "").replace("]", "");
			bw.write(csv);
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}