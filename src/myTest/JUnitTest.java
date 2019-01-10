package myTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import MyCoords.MyCoords;
import PackmanGame.Map;
import myGeom.Point3D;

class JUnitTest {
	Point3D expectedPoint, g1, g2, g3, g4, g5, g6, g7, p, p1, p2, p3, p4;
	MyCoords g;
	Map m;
	double output;
	@BeforeEach
	void setUp() throws Exception {
		g = new MyCoords();
		g1 = new Point3D(4,5,6);
		g2 = new Point3D(8.774964387392123,51.34019174590991,46.861562380328934);
		g3 = new Point3D(13.928388277184,48.81407483429,49.747120023952);
		g4 = new Point3D(3,45,70.528779365509);
		g5 = new Point3D(7,26.565051177078,73.39845040098);
		g6 = new Point3D(32.103315,35.209039,670);
		g7 = new Point3D(32.106352,35.205225,650);
		m = new Map();
		p = new Point3D(35.212405,32.101858);
		p1 = new Point3D(35.209039,32.103315);
		p2 = new Point3D(1433.0,642.0,0.0);
		p3 = new Point3D(942.0,418.0,0.0);
	}


	@Test
	void ConvertToGpsTest() {
		expectedPoint = new Point3D(8.774964387392123,51.34019174590991,46.861562380328934);
		if(!expectedPoint.equals(MyCoords.ConvertToGps(g1))) {
			fail("fail");
		}	
	}
	@Test
	void ConvertToVectorTest() {
		expectedPoint = new Point3D(4.000000000000001,5.0,6.0);
		if(!expectedPoint.equals(MyCoords.ConvertToVector(g2))) {
			fail("fail");
		}
	}
	@Test
	void addTest() {
		expectedPoint = new Point3D(17.549928774784245,51.34019174590991,46.861562380328934);
		if(!expectedPoint.equals(g.add(g2, g1))) {
			fail("fail");
		}
	}
	@Test
	void distance3dTest() {
		output = 619728.2778496796;
		if(output != g.distance3d(g2, g3)) {
			fail("fail");
		}
	}
	@Test
	void vector3DTest() {
		expectedPoint = new Point3D(444418.4942238223,-1706623.307933256,2.8696710354710007);
		if(!expectedPoint.equals(g.vector3D(g4, g5))) {
			fail("fail");
		}
	}
	@Test
	void azimuth_elevation_distTest() {
		double[]expected = {313.2304203264989, -2.3247637717454013, 493.05227791579307};
		double[]output = g.azimuth_elevation_dist(g6, g7);
		for (int i = 0; i < expected.length; i++) {
			if(expected[i] != output[i]){
				fail("fail");
			}

		}
	}
	@Test
	void testToPixel() {
		expectedPoint = new Point3D(942.0,418.0,0.0);
		if(!expectedPoint.equals(m.toPixel(p1))) {
			fail("fail");
		}
	}
	
	@Test
	void testToGPS() {
		expectedPoint = new Point3D(35.212405,32.101858,0.0);
		if(!expectedPoint.equals(m.toGps(p2))) {
			fail("fail");
		}
	}
	
	@Test
	void testDistancePixels() {
		output = 399.04496074775903;
		if(output != m.distancePixels(p2, p3)) {
			fail("fail");
		}
	}
	@Test
	void testDisToGUI() {
		output = 3.7241856035613026;
		if(output != m.disToGUI(p1, p)) {
			fail("fail");
		}
	}
	
}
