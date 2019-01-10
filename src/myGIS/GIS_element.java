package myGIS;

import myGeom.Geom_element;
import myGeom.Point3D;

/**
 * This interface represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Boaz Ben-Moshe
 *
 */
public interface GIS_element {
	public Geom_element getGeom();
	public Meta_data getData();
	public void translate(Point3D vec);
}
