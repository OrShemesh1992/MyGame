package PackmanGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import Robot.Play;
import myGeom.Point3D;
public class MyFrame extends JFrame implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private char IsGamer = ' ';
	private int Radius = 1;
	private int speed = 1;
	private int id = 1;
	private double dgr;
	private String Statistics = "";
	private Font font= new Font("Arial", Font.BOLD, 20);
	private Map map = new Map();
	private Game game = new Game();
	private Play play;
	private Server server;
	private boolean ser=false;
	public MyFrame() {
		initGUI();		
		this.addMouseListener(this); 
		this.addMouseMotionListener(this);
	}
	private void initGUI() {
		creatMenu();
	}
	/**
	 * here we create a menu for the game.
	 */
	public void creatMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Game Menu"); 
		Menu menu1 = new Menu("Import and Export");  
		MenuItem CsvImport = new MenuItem("Import to csv");
		MenuItem CsvExport = new MenuItem("Export to csv");
		MenuItem Pack = new MenuItem("Packman");
		MenuItem Fruit = new MenuItem("Fruit");
		MenuItem Start = new MenuItem("Start");
		MenuItem Play = new MenuItem("Play");
		MenuItem Clear = new MenuItem("Clear");
		MenuItem Ghost = new MenuItem("Ghost");
		MenuItem Box = new MenuItem("Box");
		MenuItem player = new MenuItem("Player");
		menuBar.add(menu);
		menu.add(player);
		menu.add(Pack);
		menu.add(Fruit);
		menu.add(Ghost);
		menu.add(Box);
		menu.add(Clear);
		menu.add(Start);
		menu.add(Play);
		menuBar.add(menu1);
		menu1.add(CsvImport);
		menu1.add(CsvExport);
		/**
		 * when we press the packman button, a image of packman will appears on the map.
		 */
		Pack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IsGamer = 'p';
			}
		});
		/**
		 * when we press the fruit button, a image of fruit will appears on the map.
		 */
		Fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IsGamer = 'f';
			}
		});
		player.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IsGamer = 'm';
			}
		});
		Ghost.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IsGamer = 'g';
			}
		});
		Box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IsGamer = 'b';
			}
		});
		/**
		 * when we press the clear button, we clear all the fruit and packman from the game.
		 */
		Clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.Fruits.clear();
				game.Packmans.clear();
				game.Boxes.clear();
				game.Ghosts.clear();
				id = 0;
				repaint();
			}
		});

		/**
		 * when we press the start button, will get the path for each packman.
		 */
		Start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IsGamer = 's';
				try {
					play.setIDs(203640768, 308529296);
					play.start();	
					Thread thread = new Thread(){
						public void run(){ 
							while(play.isRuning()) {
								play.rotate(dgr);
								game = new Game(play.getBoard());
								AllTheBoardToPixel();
								Statistics = play.getStatistics();
								try {
									Thread.sleep(50);
								}catch(InterruptedException e) {
									e.printStackTrace();
								}
								repaint();
							}
							ser=true;
						}

					};
					thread.start();
				}catch(NullPointerException e) {}
			}
		});
		/**
		 * when we press the csvImport button we create a new csv file and add to the file all of the data from the game.
		 */
		CsvImport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setDialogTitle("Select a Csv File");
				fileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","CSV");
				fileChooser.addChoosableFileFilter(filter);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					play = new Play(fileChooser.getSelectedFile().getPath());
					//פה אני נותן לו את הhasCode של המפה
					server = new Server(play.getHash1());
					game = new Game(play.getBoard());
					AllTheBoardToPixel();
					Algorithm algo = new Algorithm();
					game.player = new Player(new Point3D(algo.playerStartPoint(game.Fruits, game.Packmans)),0);
					Point3D point =map.toGps( new Point3D(game.player.locationPL.x(),game.player.locationPL.y()));
					play.setInitLocation(point.y(),point.x());

				}
				IsGamer = 'c';
				repaint();
			}
		});
		/**
		 * when we press the csvExport button, a window appears where we could choose the csv file we want to use in the game.
		 */
		Play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				play.setIDs(203640768, 308529296);
				play.start();		
				Algorithm algo = new Algorithm();
				game.player = new Player(new Point3D(algo.playerStartPoint(game.Fruits, game.Packmans)),0);
				game = new Game(play.getBoard());
				Thread thread = new Thread(){
					public void run(){
						try {
							Point3D Point = new Point3D(0,0,0);
							Point3D next = new Point3D(0,0,0);
							while(play.isRuning()) {
								game = new Game(play.getBoard());
								AllTheBoardToPixel();
								next = algo.Closest(game, game.player.locationPL);
								ArrayList<String> path = algo.path(game.player.locationPL, next, game.Boxes);
								int i =1;
								repaint();
								boolean flag = true;
								Point =new Point3D(0,0,0);
								while(i < path.size() && flag == true) {
									if(algo.stillThere(next, game.Packmans,game.Fruits) == false && i < path.size()) {
										flag = false;
									}
									else if(algo.Closest(game, game.player.locationPL) != next && i < path.size()) {
										flag = false;
									}
									String[] split = path.get(i).split(",");
									Point3D a = map.toPixel(Point);
									Point3D t = corner(game.Boxes,new Point3D(Double.parseDouble(split[0]),Double.parseDouble(split[1])));
									if(map.equalS(a,game.player.locationPL) == true && i < path.size()) {
										++i;
									}
									Statistics=play.getStatistics();
									game = new Game(play.getBoard());
									Point = map.toGps(t);
									dgr = map.Angle(game.player.locationPL,Point);
									repaint();
									play.rotate(dgr);
									AllTheBoardToPixel();
									try {
										Thread.sleep(30);
									}catch(InterruptedException e) {
										e.printStackTrace();
									}
									repaint();
								}
							}
							repaint();
							//פה אני הופך דגל לtrue מתי שהוא מסיים את הטרייד
							ser=true;
						}catch(NullPointerException e) {}
					}
				};
				thread.start();


			}
		});

		this.setMenuBar(menuBar);
	}

	public Point3D corner(ArrayList<Box> box,Point3D p) {
		for (int i = 0; i < box.size(); i++) {
			if(map.equalS(p, box.get(i).locationB2)) {
				p = new Point3D(p.x()+40,p.y()-40);
			}
			else if(map.equalS(p, box.get(i).locationB1)) {
				p = new Point3D(p.x()-40,p.y()+40);
			}
			else if(map.equalS(p, new Point3D(box.get(i).locationB1.x(),box.get(i).locationB2.y()))) {
				p = new Point3D(p.x()-40,p.y()-40);
			}
			else if(map.equalS(p, new Point3D(box.get(i).locationB2.x(),box.get(i).locationB1.y()))) {
				p = new Point3D(p.x()+40,p.y()+40);
			}
		}
		return p;
	}
	public void AllTheBoardToPixel() {
		try {
			Iterator<Fruit> ItF = game.Fruits.iterator();
			Iterator<Packman> ItP = game.Packmans.iterator();
			Iterator<Ghost> ItG = game.Ghosts.iterator();
			Iterator<Box> ItB = game.Boxes.iterator();
			while(ItF.hasNext()) {
				Fruit F = ItF.next();
				F.locationF = map.toPixel(F.locationF);
				F.locationF = new Point3D(F.locationF.x(),F.locationF.y());
			}
			while(ItP.hasNext()) {
				Packman P = ItP.next();
				P.locationP = map.toPixel(P.locationP);
				P.locationP = new Point3D(P.locationP.x(),P.locationP.y());
			}
			while(ItG.hasNext()) {
				Ghost g = ItG.next();
				g.locationG = map.toPixel(g.locationG);
				g.locationG = new Point3D(g.locationG.x(),g.locationG.y());
			}
			while(ItB.hasNext()) {
				Box b = ItB.next();
				b.locationB1 = map.toPixel(b.locationB1);
				b.locationB2 = map.toPixel(b.locationB2);
				b.locationB1 = new Point3D(b.locationB1.x(),b.locationB1.y());
				b.locationB2 = new Point3D(b.locationB2.x(),b.locationB2.y());
			}
			game.player.locationPL = map.toPixel(game.player.locationPL);
			game.player.locationPL=new Point3D(game.player.locationPL.x(),game.player.locationPL.y());
		}catch(NullPointerException e) {}
	}
	/**
	 * here we draw the game and setting the size of the fruit and packman image and for the size and color for the lines 
	 * that represent the path that each packman does.
	 */
	public void paint(Graphics g) {	
		Image image = createImage(5000,5000);
		Graphics g1 = image.getGraphics();
		g1.drawImage(map.myImage,-8 ,-8, getWidth(), getHeight(), this);
		Iterator<Packman> ItP = game.Packmans.iterator();
		Iterator<Fruit> ItF = game.Fruits.iterator();
		Iterator<Ghost> ItG = game.Ghosts.iterator();
		Iterator<Box> ItB = game.Boxes.iterator();
		try {
			g1.setFont(font);
			g1.setColor(Color.WHITE);
			g1.drawString(Statistics, 60, 620);
			while(ItB.hasNext()) {
				Box b = ItB.next();
				g1.drawImage(map.Box, (int)b.locationB1.x(), (int)b.locationB1.y(), (int)b.locationB2.x()-(int)b.locationB1.x(), (int)b.locationB2.y()-(int)b.locationB1.y(), null);
			}
			while(ItF.hasNext()) {
				Fruit fru = ItF.next();
				g1.drawImage(map.Fruit,(int)fru.locationF.x(), (int)fru.locationF.y(), 20, 20, null);

			}
			while(ItP.hasNext()) {
				Packman pac = ItP.next();
				g1.drawImage(map.Packman, (int)pac.locationP.x(), (int)pac.locationP.y(), 25, 25, null);
			}
			while(ItG.hasNext()) {
				Ghost ghost = ItG.next();
				g1.drawImage(map.Ghost, (int)ghost.locationG.x(), (int)ghost.locationG.y(), 20, 20, null);
			}
			g1.drawImage(map.Player,(int)game.player.locationPL.x(), (int)game.player.locationPL.y(), 30, 30, null);	
			//ופה אם הדגל true אני מפעיל את הפונקציה
			if(ser==true) {
				server.GiveMeStatstic();
				//הזאת ומדפיס את הdata שלקחתי משם
				g1.setColor(Color.WHITE);
				g1.drawString("The Map: "+server.getMapName(), 60, 320);
				g1.drawString("My Best Score in This Map: "+server.getMy_Max_Score(), 60, 345);
				g1.drawString("The First Score in This Map: "+server.getScore_First(), 60, 370);
				g1.drawString("The Second Score in This Map: "+server.getScore_Second(), 60, 395);
			}
		}

		catch(CancellationException  e) {}
		g.drawImage(image,0,0,this);
	}
	@Override
	public void mouseClicked(MouseEvent arg) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}

	/**
	 * when we create new packman or fruit each mouse click we get the location of the variable and print the image of the variable.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		double x_temp = arg0.getX();
		double y_temp = arg0.getY();
		if(IsGamer =='p') {
			Packman pac = new Packman(new Point3D(x_temp,y_temp,0),speed,id,Radius,"P");
			game.Packmans.add(pac);
			id++;
		}
		if(IsGamer =='f') {
			Fruit fru = new Fruit(speed,id,new Point3D(x_temp,y_temp,0),"F");
			game.Fruits.add(fru);
			id++;
		}
		if(IsGamer =='g') {
			Ghost g = new Ghost(new Point3D(x_temp,y_temp,0),id,speed,Radius,"G");
			game.Ghosts.add(g);
			id++;
		}
		if(IsGamer =='b') {
			if(game.Boxes.isEmpty()||!game.Boxes.get(game.Boxes.size()-1).locationB2.equals(game.Boxes.get(game.Boxes.size()-1).locationB1)) {
				Box b = new Box(new Point3D(x_temp, y_temp), new Point3D(x_temp,y_temp,0),id , "B");
				game.Boxes.add(b);
				id++;
			}else{
				game.Boxes.get(game.Boxes.size()-1).setLocationB2(new Point3D(x_temp,y_temp));
			}
		}
		if(IsGamer == 'm') {
			try {
				game.player = new Player(new Point3D(x_temp,y_temp),0);
				Point3D point = map.toGps(new Point3D(game.player.locationPL.x(),game.player.locationPL.y()));
				play.setInitLocation(point.y(),point.x());
			}catch(NullPointerException e) {}
		}
		repaint();
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		if(IsGamer == 's')
			try {
				game=new Game(play.getBoard());
				Point3D Point = map.toGps(new Point3D(arg0.getX(),arg0.getY()));
				dgr=map.Angle(game.player.locationPL,Point);
				AllTheBoardToPixel();
				play.rotate(dgr);
			}catch(NullPointerException e) {}	
	}
	public static void main(String[] args){
		MyFrame window = new MyFrame();
		Map map = new Map();
		window.setVisible(true);
		window.setSize(map.myImage.getWidth(),map.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}