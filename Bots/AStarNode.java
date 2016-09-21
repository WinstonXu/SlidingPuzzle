import java.util.ArrayList;
/**
 * 
 * @author Winston
 *node used in the priority queue of the A* search algorithm
 *Contains board state, estimated moves to solve, path to reach current state, current distance from beginning
 *
 *@param SlidingBoard, int, int, ArrayList<SlidingMove>
 */
public class AStarNode implements Comparable{

	public SlidingBoard board;
	public int totalMoveEst;
	public ArrayList<SlidingMove> path;
	public int currDist;
	
	public AStarNode(SlidingBoard _current, int curr_distance, int move_estimate, ArrayList<SlidingMove> _path){
		this.board = _current;
		this.currDist = curr_distance;
		this.totalMoveEst = curr_distance + move_estimate;
		this.path = _path;
	}
	/**
	 * Description Compares two nodes by their total estimated moves needed to solve
	 * @param Object
	 * @result int
	 */
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		AStarNode other = (AStarNode) o;
		return new Integer(totalMoveEst).compareTo(new Integer(other.totalMoveEst));
	}
}