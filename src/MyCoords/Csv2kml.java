package MyCoords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Csv2kml {

	/**
	 * In this function we get a csv file and converts it to a kml file. 
	 */
	static void writeFileKML(ArrayList<String[]> a, String output) {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
				"<Document>";
		content.add(kmlstart);

		String kmlend = "</Document>" + "\n</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 1; i < a.size(); i++) {
				String[] s = a.get(i);
				String kmlelement ="<Placemark>\n" +
						"<name>"+s[1]+"</name>\n" +
						"<description>"+s[0]+", Capabilities:"+s[2]+", Date:"+s[3]+
						"</description>\n" +
						"<Point>\n" +
						"<coordinates>"+s[7]+","+s[6]+"</coordinates>" +
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
	
	/**
	 * In this function we search for a csv file in our computer and sending it to the writeFileKML function.
	 */
	static void parseCSV(String csv , String kml) {
		String csvFile = csv;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String[]> a = new ArrayList<String[]>();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				a.add(line.split(cvsSplitBy));
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
		writeFileKML(a, kml);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
