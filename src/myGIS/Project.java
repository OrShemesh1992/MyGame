package myGIS;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
public class Project implements GIS_project{
	/**
	 * here we actualizing the data structures -Set-. 
	 */

	Set<GIS_layer> SetLayer = new HashSet<>();
	String time="";
	String info="";
	@Override
	public boolean add(GIS_layer arg0) {
		return SetLayer.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> arg0) {
		return SetLayer.addAll(arg0);
	}

	@Override
	public void clear() {
		SetLayer.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return SetLayer.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return SetLayer.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return SetLayer.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return SetLayer.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return SetLayer.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return SetLayer.retainAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return SetLayer.retainAll(arg0);
	}

	@Override
	public int size() {
		return SetLayer.size();
	}

	@Override
	public Object[] toArray() {
		return SetLayer.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return SetLayer.toArray(arg0);
	}
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

	public void displayDirectoryContents(File dir,String info) {
		try {
			this.info=info;
			this.time=create_time();
			//			int i = 1;
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					displayDirectoryContents(file.getCanonicalFile(),info);
				} else {
					if(file.getCanonicalPath().contains(".csv"))
					{
						Layer a=new Layer();
						a.parseCSV(file.getAbsolutePath(),info);
						this.SetLayer.add(a);
						System.out.println("     file:" + file.getAbsolutePath());
						//						Csv2kml.parseCSV(file.getAbsolutePath(), "C:\\Users\\OrShemesh\\Downloads\\" + i + ".kml");
						//						i++;
						//						System.out.println(i);
					}
				}
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String s="";
		Iterator<GIS_layer> It = this.iterator();
		int i=1;
		while (It.hasNext()) {
			s+="The "+i+" Layer\n"+It.next()+"\n";
			i++;
		}
		return s;
	}
	@Override
	public Meta_data get_Meta_data() {
		return new Data(time,info);
	}
}