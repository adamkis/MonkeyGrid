package monkeyGrid;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;


public class MonkeyGrid_simple {

	// Max number that the monkey can go
	private static int MAX = 19;
	
	private static HashSet<Point> nodesVisited = new HashSet<Point>();
	private static Queue <Point> nodesToVisit = new LinkedList <Point> ();
	private static Point startNode = new Point(0, 0);
	
	// Counter of cell the monkey has stepped on
	private static int reached = 0;
	
	// Neighbours of the current point
	private static Point up, down, right, left;

	
	/**
	 * Counts the reachable cells with BFS algorithm.
	 * Removes node from queue and marks visited. Adds all its neighbours if not already added or not out of bound. Does it until queue is empty
	 * @return The number of cells, the monkey has stepped on
	 */
	public static int breadthFirstSearch(){
		
		nodesToVisit.add(startNode);
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
	
	
	public static int sumOfDigits(int num){
		
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
	

	public static void main(String[] args) {

		System.out.println( Integer.toString(breadthFirstSearch()) );
	
	}
	

}
