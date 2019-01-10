package myTest;
import java.io.File;

import MyCoords.MultiCSV;
import MyCoords.MyCoords;
import myGIS.Data;
import myGIS.Element;
import myGIS.GIS_layer;
import myGIS.Layer;
import myGIS.Project;
import myGeom.Geom_element;
import myGeom.Point3D;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;


public class mainTest {

	public static void main(String[] args) {
		System.out.println("MyCoords test:");
		MyCoords g = new MyCoords();
		Point3D gps0=new Point3D(4,5,6);
		Point3D gps1=new Point3D(8.774964387392123,51.34019174590991,46.861562380328934);
		Point3D g5 = new Point3D(8.774964387392123,51.34019174590991,46.861562380328934);
		Point3D g6 = new Point3D(13.928388277184,48.81407483429,49.747120023952);
		Point3D g7 = new Point3D(3,45,70.528779365509);
		Point3D g8 = new Point3D(7,26.565051177078,73.39845040098);
		Point3D gps10=new Point3D(51.50,0);
		Point3D gps11=new Point3D(-22.97,-43.18);
		Point3D gps12=new Point3D(32.103315,35.209039,670);
		Point3D gps13=new Point3D(32.106352,35.205225,650);
		System.out.println("Convert vector to gps: \n" + MyCoords.ConvertToGps(gps0));
		System.out.println( MyCoords.ConvertToGps(gps10)+"\n");
		System.out.println("Convert gps to vector: \n" + MyCoords.ConvertToVector(gps1));
		System.out.println(MyCoords.ConvertToVector(g6));
		System.out.println("add 2 vector (one gps and one vector): \n" + g.add(g5, gps0) + "\n");
		System.out.println("checking the distance between 2 gps point: \n" + g.distance3d(g5, g6));
		System.out.println(g.distance3d(gps12, gps13)+ "\n");
		System.out.println("calculate the vector in meters: \n" + g.vector3D(g7, g8));
		System.out.println(g.vector3D(gps12, gps13)+ "\n");
		System.out.println("calculate the azimuth, elevation and the distance: ");
		double[]t = g.azimuth_elevation_dist(gps12, gps13);
		for (int i = 0; i < t.length; i++) {
			System.out.print(" "+t[i]+" ");
		}
		System.out.println();
		double[]t1 = g.azimuth_elevation_dist(gps10, gps11);
		for (int i = 0; i < t1.length; i++) {
			System.out.print(" "+t1[i]+" ");
		}
		System.out.println("\n");
		System.out.println("MultiCSV test:");
		//		File dir= new File("D:\\yaara\\Downloads");
		File dir= new File("C:\\Users\\OrShemesh\\Downloads");
		MultiCSV.displayDirectoryContents(dir);
		System.out.println("\n");
		System.out.println("Element test:");
		Element b1=new Element ();
		b1.LineCsvToElement("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],01/12/2017 10:49,1,-75,32.17218268,34.81446402,13.65040889,6,WIFI\r\n");
		System.out.println(b1.getGeom());
		Point3D g1 = new Point3D((Point3D)b1.getGeom());
		System.out.println(g1);
		System.out.println(b1);

		System.out.println("\n");
		System.out.println("Layer test:");
		Element aa=new Element();
		Element b=new Element();
		Element c=new Element();
		Element d=new Element();
		Element e=new Element();
		aa.LineCsvToElement("40:65:a3:3u:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],01/12/2017 10:49,1,-75,32.17218268,34.81446402,13.65040889,6,WIFI\r\n");
		b.LineCsvToElement("40:65:a3:3u:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],01/12/2017 10:49,1,-75,32.17218268,34.81446402,13.65040889,6,WIFI\r\n");
		c.LineCsvToElement("40:65:a3:3u:4c:c4,Efr5at,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],01/12/2017 10:49,1,-75,32.17218268,34.81446402,13.65040889,6,WIFI\r\n");
		d.LineCsvToElement("40:65:a3:3u:4c:c4,Ef3rat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],01/12/2017 10:49,1,-75,32.17218268,34.81446402,13.65040889,6,WIFI\r\n");
		Layer n = new Layer();
		Layer n1 = new Layer();
		n.add(aa);
		n.add(b);
		n1.add(aa);
		n1.add(b);
		System.out.println(n1.size());
		System.out.println(n1.isEmpty());
		n1.clear();
		System.out.println(n1.isEmpty());
		System.out.println(n.containsAll(n1));
		n.addAll(n1);
		System.out.println(n);
		n.removeAll(n1);
		System.out.println(n);
		Layer a=new Layer();
		a.parseCSV("C:\\Users\\OrShemesh\\Downloads\\מונחה עצמים\\Ex2\\WigleWifi_20171201110209.csv","info");
		//		a.parseCSV("D:\\yaara\\Downloads\\מונחה עצמים\\Ex2\\data\\WigleWifi_20171201110209.csv","info");
		System.out.println(a);
		//		a.writeFileKML("D:\\yaara\\Downloads\\" +  "8.kml");
		a.writeFileKML("C:\\Users\\OrShemesh\\Downloads\\" +  "8.kml");
		n.remove(aa);
		System.out.println(n);
		n1.add(c);
		n1.add(d);
		System.out.println(n.contains(aa));
		System.out.println(n);

		System.out.println("\n");
		System.out.println("Project test:");
		File dir1= new File("C:\\Users\\OrShemesh\\Downloads");
		//		File dir1= new File("D:\\yaara\\Downloads");
		Project a1=new Project();
		a1.displayDirectoryContents(dir1,"info");
		//		File dir= new File("C:\\Users\\OrShemesh\\Downloads");
		//		displayDirectoryContents(dir);
		System.out.println(a1);
		Data stam=(Data) a1.get_Meta_data();
		System.out.println(stam);
		Iterator<GIS_layer> It = a1.iterator();
		int i=15;
		while(It.hasNext())
		{
			i++;
			((Layer) It.next()).writeFileKML("C:\\Users\\OrShemesh\\Downloads\\" +i+  ".kml");
		}
		//	while(It.hasNext())
		//		{
		//			i++;
		//			((Layer) It.next()).writeFileKML("D:\\yaara\\Downloads\\" +i+  ".kml");
		//		}










	}



}
