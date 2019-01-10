package PackmanGame;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import PackmanGame.Fruit;
import PackmanGame.Packman;

public class Game {
	ArrayList<Fruit> Fruits = new ArrayList<Fruit>();
	ArrayList<Packman> Packmans = new ArrayList<Packman>();
	ArrayList<Ghost> Ghosts = new ArrayList<Ghost>();
	ArrayList<Box> Boxes = new ArrayList<Box>();
	Player player = new Player();

	public Game(ArrayList<Fruit> Fruits,ArrayList<Packman> Packmans) {
		this.Fruits = Fruits;
		this.Packmans=Packmans;
	}
	

	public Game(ArrayList<Fruit> f, ArrayList<Packman> p, ArrayList<Ghost> g, ArrayList<Box> b) {
		this.Fruits = f;
		this.Packmans = p;
		this.Ghosts = g;
		this.Boxes = b;
	}

	/**
	 * A default constructor
	 */
	public Game() {
		Fruits = new ArrayList<Fruit>();
		Packmans = new ArrayList<Packman>();
		Ghosts = new ArrayList<Ghost>();
		Boxes = new ArrayList<Box>();
	}

	/**
	 * A constructor that gets a line from csv file and initializing the variables.
	 * @param LineCsv
	 */
	public Game (ArrayList<String> filePath) {

		for(int i=0;i<filePath.size();i++) {
			String[] fields = filePath.get(i).split(",");
			if(fields[0].equals("M")) {
				player = new Player(filePath.get(i));
			}
			else if(fields[0].equals("F")) {
				Fruit f = new Fruit(filePath.get(i));
				Fruits.add(f);
			}
			else if(fields[0].equals("P")){
				Packman p = new Packman(filePath.get(i));
				Packmans.add(p);
			}
			else if(fields[0].equals("G")) {
				Ghost g = new Ghost(filePath.get(i));
				Ghosts.add(g);
			}
			else if(fields[0].equals("B")) {
				Box b = new Box(filePath.get(i));
				Boxes.add(b);
			}
		}
	}
	/**
	 * This function create a csv file from values that its gets from the game we create.
	 * @param filePath
	 * @param Creat
	 */
	public static void writeCsv(String filePath,Game Creat) {
		ArrayList<Packman> Packmans = Creat.Packmans;
		ArrayList<Fruit> Fruits = Creat.Fruits;
		ArrayList<Ghost> Ghosts = Creat.Ghosts;
		ArrayList<Box> Boxes = Creat.Boxes;
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath);
			fileWriter.append("Type, id, Lat,Lon,Alt,Speed/Weight,Radius,"+String.valueOf(Packmans.size())+","+
					String.valueOf(Fruits.size())+","+String.valueOf(Boxes.size()));
			fileWriter.append("\n");
			for(Packman p: Packmans) {
				fileWriter.append(p.type);
				fileWriter.append(",");
				fileWriter.append(String.valueOf(p.getID()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(p.locationP.y()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(p.locationP.x()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(p.locationP.z()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(p.speed));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(p.radius));
				fileWriter.append('\n');
			}
			for(Fruit f: Fruits) {
				fileWriter.append(f.type);
				fileWriter.append(",");
				fileWriter.append(String.valueOf(f.getID()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(f.locationF.y()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(f.locationF.x()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(f.locationF.z()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(f.weight));
				fileWriter.append('\n');
			}
			for(Ghost g: Ghosts) {
				fileWriter.append(g.type);
				fileWriter.append(",");
				fileWriter.append(String.valueOf(g.getID()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(g.locationG.y()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(g.locationG.x()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(g.locationG.z()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(g.getSpeed()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(g.getRadius()));
				fileWriter.append('\n');
			}
			for(Box b: Boxes) {
				fileWriter.append(b.type);
				fileWriter.append(",");
				fileWriter.append(String.valueOf(b.getID()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(b.locationB1.y()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(b.locationB1.x()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(b.locationB1.z()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(b.locationB2.y()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(b.locationB2.x()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(b.locationB2.z()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(b.WTF));
				fileWriter.append('\n');
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public String toString() {
		String s = "";
		s+=player.toString()+"\n";
		Iterator<Packman> ItP = Packmans.iterator();
		Iterator<Fruit> ItF = Fruits.iterator();
		Iterator<Ghost> ItG = Ghosts.iterator();
		Iterator<Box> ItB = Boxes.iterator();
		while(ItP.hasNext()) {
			s += ItP.next().toString()+"\n";
		}
		while(ItF.hasNext()) {
			s += ItF.next().toString()+"\n";
		}
		while(ItG.hasNext()) {
			s += ItG.next().toString()+"\n";
		}
		while(ItB.hasNext()) {
			s += ItB.next().toString()+"\n";
		}
		return s;
	}	
}
