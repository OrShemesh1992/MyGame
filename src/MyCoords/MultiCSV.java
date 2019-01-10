package MyCoords;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
public class MultiCSV {
	
	public static void displayDirectoryContents(File dir) {
		try {
			int i = 1;
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					displayDirectoryContents(file.getCanonicalFile());
				} else {
					if(file.getCanonicalPath().contains(".csv"))
					{
						System.out.println("     file:" + file.getAbsolutePath());
						Csv2kml.parseCSV(file.getAbsolutePath(), "C:\\Users\\OrShemesh\\Downloads\\" + i + ".kml");
//						Csv2kml.parseCSV(file.getAbsolutePath(), "D:\\yaara\\Downloads\\" + i + ".kml");
						i++;
					}
				}
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
