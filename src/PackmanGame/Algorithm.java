package PackmanGame;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
import myGeom.Point3D;


public class Algorithm {

	/**
	 * In this function we gets array list with all the points of the corners of the box and find all the points that are not in another box.
	 * @return ArrayList<Point3D>
	 */
	public ArrayList<Point3D> boxesPoints(ArrayList<Box> b) {
		Box box = new Box();
		Point3D[] temp = new Point3D[4];
		ArrayList<Point3D[]> boxPoint = new ArrayList<Point3D[]>();
		ArrayList<Point3D> points = new ArrayList<Point3D>();
		for (int i = 0; i < b.size(); i++) {
			temp = box.boxCorners(b.get(i));
			boxPoint.add(box.plusMeter(temp));
		}
		for (int i = 0; i < b.size(); i++) {
			for (int j = 0; j < 4; j++) {
				Point3D corner = boxPoint.get(i)[j];
				if(box.IsIn(corner, b) == false) {
					points.add(corner);
				}
			}
		}
		return points;
	}
	/**
	 * In this function we gets 2 points which represent the path we do, and array list of boxes.
	 * We check if the line between the 2 points cuts any of the boxes side.
	 * @return false only if the line between the 2 points do not cut any if the boxes side.
	 */
	public boolean IsCut (Point3D A, Point3D B, ArrayList<Box> b) {
		Box box = new Box();
		for (int i = 0; i < b.size(); i++) {
			Point3D[] box4Points = box.boxCorners(b.get(i));
			Line2D l1 = new Line2D.Float((int)A.y(), (int)A.x(), (int)B.y(), (int)B.x());
			if(l1.intersectsLine(new Line2D.Float((int)box4Points[0].y(),(int)box4Points[0].x(),(int)box4Points[3].y(),(int)box4Points[3].x())) == true ||
					l1.intersectsLine(new Line2D.Float((int)box4Points[1].y(),(int)box4Points[1].x(),(int)box4Points[2].y(),(int)box4Points[2].x())) == true ||
					l1.intersectsLine(new Line2D.Float((int)box4Points[0].y(),(int)box4Points[0].x(),(int)box4Points[2].y(),(int)box4Points[2].x())) == true ||
					l1.intersectsLine(new Line2D.Float((int)box4Points[1].y(),(int)box4Points[1].x(),(int)box4Points[3].y(),(int)box4Points[3].x())) == true) {
				return true;
			}
		}
		return false;
	}
	/**
	 * In this function we gets array list of boxes and one point, for this point we find all of the box corners
	 * that the point can't go to without cutting a side of one of the boxes.  
	 * @return array list of point3D with the points we found.
	 */
	public ArrayList<Point3D> Neighbors(ArrayList<Box> b , Point3D start){
		ArrayList<Point3D> point = boxesPoints(b);
		ArrayList<Point3D> optionalPoints = new ArrayList<Point3D>();
		for (int i = 0; i < point.size(); i++) {
			if(IsCut(start, point.get(i), b) == false) {
				optionalPoints.add(point.get(i));
			}
		}
		return optionalPoints;
	}
	/**
	 * In this function we gets game which contains array list of fruit, array list of packman and array list of boxes and the player location.
	 * The function finds the fruit or the packman that is closest to the player by calculation the distance and checking if there is a box between them.
	 * @return the closest fruit or packman to the player.
	 */
	public Point3D Closest(Game game, Point3D player) {
		double disP=0, dis1=0,disF=0;
		Point3D bestP=null, bestF=null;
		boolean wall = false;

		if(!game.Packmans.isEmpty()) {
			disP = player.distance2D(game.Packmans.get(0).locationP);
			bestP = game.Packmans.get(0).locationP;
		}
		for(int i = 1; i < game.Packmans.size(); i++) {
			dis1 = player.distance2D(game.Packmans.get(i).locationP);
			wall = IsCut(player, game.Packmans.get(i).locationP, game.Boxes);
			if(dis1 < disP && wall == false) {
				if(IsCut(player, bestP, game.Boxes) == true) {
					disP = dis1;
					bestP = game.Packmans.get(i).locationP;
				}
				else if(disP - dis1 > 50) {
					disP = dis1;
					bestP = game.Packmans.get(i).locationP;
				}
			}
		}
		if(!game.Fruits.isEmpty()) {
			disF = player.distance2D(game.Fruits.get(0).locationF);
			bestF = game.Fruits.get(0).locationF;
		}
		for(int i = 1; i < game.Fruits.size(); i++) {
			dis1 = player.distance2D(game.Fruits.get(i).locationF);
			wall = IsCut(player, game.Fruits.get(i).locationF, game.Boxes);
			if(dis1 < disF && wall == false) {
				if(IsCut(player, bestF, game.Boxes) == true) {
					disF = dis1;
					bestF = game.Fruits.get(i).locationF;
				}
				else if(disF - dis1 > 50) {
					disF = dis1;
					bestF = game.Fruits.get(i).locationF;
				}
			}
		}
		if(game.Packmans.isEmpty()) {
			return bestF;
		}
		else if(game.Fruits.isEmpty()) {
			return bestP;
		}
		wall = IsCut(player, bestP, game.Boxes);
		if(disP < disF && wall == false) {
			if(IsCut(player, bestF, game.Boxes) == true && disF - disP > 50) {
				return bestP;
			}
		}
		return bestF;
	}
	/**
	 * This function gets array list of ghosts and the player location and checking if one of the ghosts is close to the player.
	 * @return true if one of the ghosts is close to the player.
	 */
	public boolean ClosestGhost(ArrayList<Ghost> Ghosts, Point3D player) {
		double disP=0;
		for (int i = 0; i < Ghosts.size(); i++) {
			disP = player.distance2D(Ghosts.get(i).locationG);
			if(disP<1) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This function gets array list of fruits, array list of packmans and a fruit or packman location and checking if the
	 * point is at the same location.
	 * @return true if the point is still at the same location.
	 */
	public boolean stillThere(Point3D A, ArrayList<Packman> p, ArrayList<Fruit> f) {
		for (int i = 0; i < f.size() || i < p.size(); i++) {
			if(i < f.size() && f.get(i).locationF.equals(A)) {
				return true;
			}
			if(i < p.size() && p.get(i).locationP.equals(A)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This function gets array list of fruits and array list of packmans and randomly selects one of the fruits or packmans as start point.
	 * @return the random location.
	 */
	public Point3D playerStartPoint(ArrayList<Fruit> f, ArrayList<Packman> p) {
		Point3D startPoint;
		int location;
		location = (int)(Math.random()*f.size());
		startPoint = f.get(location).locationF;	
		return startPoint;
	}
	/**
	 * In this function we gets start point, end point, array list of boxes.
	 * This function calculate the shortest path between the start point to the end point by using the Neighbors 
	 * function and Boaz algorithm.
	 * @return the shortest path between 2 points.
	 */
	public ArrayList<String> path(Point3D A,Point3D B, ArrayList<Box> boxes) {
		ArrayList<Point3D> points = boxesPoints(boxes);
		Graph G = new Graph();
		for (int i = 0; i < G.size(); i++) { 
			G.getNodeByIndex(i).get_ni().clear();
		}
		points.add(0, A);
		points.add(B);
		for(int i=0;i<points.size();i++) {
			G.add( new Node(points.get(i).toString()));
		}
		for (int i = 0; i < points.size(); i++) {
			ArrayList<Point3D> path = Neighbors(boxes ,points.get(i));
			if(IsCut(points.get(i), B, boxes) == false) {
				G.addEdge(points.get(i).toString(), B.toString(), points.get(i).distance2D(B));
			}
			else
				for (int j = 0; j < path.size(); j++) {
					G.addEdge(points.get(i).toString(),path.get(j).toString(),points.get(i).distance2D(path.get(j)));
				}
		}
		Graph_Algo.dijkstra(G,A.toString());

		Node b = G.getNodeByName(B.toString());
		b.getPath().add(B.toString());
		return b.getPath();
	}
}
