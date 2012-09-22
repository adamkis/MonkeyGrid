package monkeyGrid;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class MonkeyGrid {

	// Max number that the monkey can go
	private int MAX;
	
	private HashSet<Point> nodesVisited = new HashSet<Point>();
	private Queue <Point> nodesToVisit = new LinkedList <Point> ();
	private Point startNode = new Point(0, 0);
	
	// Counter of cell the monkey has stepped on
	private int reached = 0;
	
	// Neighbours of the current point
	private Point up, down, right, left;

	
	public MonkeyGrid(int max){
		this.MAX = max;
		nodesToVisit.add(startNode);
	}
	
	
	/**
	 * Counts the reachable cells with BFS algorithm.
	 * Removes node from queue and marks visited. Adds all its neighbours if not already added or not out of bound. Does it until queue is empty
	 * @return The number of cells, the monkey has stepped on
	 */
	public int breadthFirstSearch(){
		
		while( ! nodesToVisit.isEmpty() ){
			
			Point currentNode = nodesToVisit.remove();
			reached++;
			nodesVisited.add(currentNode);
			
			// Gets all its neighbours and adds if ~Not already visited(marked) ~Not in the queue to visit later ~Not out of bound
			if	( ! ( 	nodesVisited.contains( up = new Point(currentNode.x, currentNode.y+1) ) 
					||	nodesToVisit.contains( up ) 
					||	sumOfDigits(up.x)+sumOfDigits(up.y) > MAX ) )
				nodesToVisit.add(up);	
			
			if	( ! ( 	nodesVisited.contains( right = new Point(currentNode.x+1, currentNode.y) ) 
					||	nodesToVisit.contains( right ) 
					||	sumOfDigits(right.x)+sumOfDigits(right.y) > MAX) )
				nodesToVisit.add(right);
			
			if	( ! (	nodesVisited.contains( down = new Point(currentNode.x, currentNode.y-1) ) 
					||	nodesToVisit.contains( down )
					||	sumOfDigits(down.x)+sumOfDigits(down.y) > MAX) )
				nodesToVisit.add(down);
			
			if	( ! ( nodesVisited.contains( left = new Point(currentNode.x-1, currentNode.y) ) 
					||	nodesVisited.contains( left )
					||	sumOfDigits(left.x)+sumOfDigits(left.y) > MAX) )	
				nodesToVisit.add(left);
			
		}	
		return reached;
	}
	
	
	public int sumOfDigits(int num){
		
		int sumOfDigits = 0;
		LinkedList<Integer> numDigits = new LinkedList<Integer>();
		
		num = Math.abs(num);
		
		while (num > 0) {
		    numDigits.push( num % 10 );
		    num = num / 10;
		}
		for( int digit : numDigits ){
			sumOfDigits += digit;
		}
		
		return sumOfDigits;
	}
	

	/**
	 *  Displays the result to a table
	 *  Ugly, but simple and good for debug
	 */
	private void displayMonkeyResult(){
		
		boolean bigEnough = false;
		int tableMAX = 0;
		
		while ( ! bigEnough )
		try{
		
			tableMAX++;
			String Points[][] = new String[2*tableMAX+1][2*tableMAX+1];
			
			JFrame frame = new JFrame();
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		    //Column Names
		    Object columnNames[] = new Object[2*tableMAX+1];
		    for ( int i=-tableMAX ; i<=tableMAX ; i++) 
		    	columnNames[i+tableMAX] = Integer.toString(i);
		    // Fill Points
		    for ( int x=-tableMAX ; x<=tableMAX ; x++ ){
				for ( int y=-tableMAX ; y<=tableMAX ; y++){
						Points[x+tableMAX][y+tableMAX] = "";
				}
			}
		    		
		    for(Point c: nodesVisited)
		    	Points[c.x+tableMAX][c.y+tableMAX] = "xxxxxxxxxxxxxx";
		    JTable table = new JTable(Points, columnNames);
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    
		    JScrollPane scrollPane = new JScrollPane(table);
		    frame.add(scrollPane, BorderLayout.CENTER);
		    frame.setSize(1920, 1040);
		    frame.setVisible(true);
		    
		    bigEnough = true;
	    
	    }
	    catch(Exception e){}
	    
		
	}
	

	public static void main(String[] args) {

		MonkeyGrid mg = new MonkeyGrid(19);
		System.out.println("Result: " + Integer.toString(mg.breadthFirstSearch()));
		mg.displayMonkeyResult();
	}
	

}
