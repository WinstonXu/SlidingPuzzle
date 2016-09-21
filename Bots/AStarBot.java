
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
/**
 * 
 * @author Winston
 *Bot that uses A* heuristic to solve Sliding Puzzle
 *Works with up to 3X3 boards very quickly
 *Modeled after github code
 *
 *@param SlidingBoard
 */
public class AStarBot extends SlidingPlayer{

	private ArrayList<SlidingMove> path;
	private int move_number = -1;

	public AStarBot(SlidingBoard _sb){
		super(_sb);
		path = aStar(_sb);
	}
	/**
	 * Given a Board, gives an underestimate of how many moves would be required to solve the puzzle
	 * Uses Manhattan Distance to estimate, also implements linear conflict heuristic
	 * 
	 * @param SlidingBoard
	 * @result integer
	 */
	public int estMove(SlidingBoard curr){

		int counter = 0;
		for(int r = 0; r < curr.size; r++){
			for(int c = 0; c < curr.size; c++){
				int goal_num = r*curr.size+c;
				if(curr.board[r][c] != goal_num){
					int row_diff = (int) (curr.board[r][c]/curr.size);
					int col_diff = curr.board[r][c] % curr.size;
					counter += row_diff + col_diff;
					if(Math.abs(curr.board[r][c]-goal_num) < curr.size){
						counter += 2;
					}
				}
			}
		}
		return counter;
	}
	/**
	 * Search algorithm implementing priority queue to find next state to explore
	 * Returns an ArrayList of SlidingMoves in the order from the beginning state of the board
	 * to reach what algorithm determines is the optimal path to a solved board
	 * 
	 * @param SlidingBoard
	 * @result ArrayList<SlidingMove>
	 */
	public ArrayList<SlidingMove> aStar(SlidingBoard sb){
		HashSet<String> prevStates = new HashSet<String>();
		AStarNode start = new AStarNode(sb, 0, estMove(sb), new ArrayList<SlidingMove>());
		PriorityQueue<AStarNode> frontier = new PriorityQueue<AStarNode>();
		frontier.add(start);
		if(!sb.isSolved()){
			AStarNode curr = frontier.poll();
			while(!curr.board.isSolved()){
				ArrayList<SlidingMove> legalMoves = curr.board.getLegalMoves();
				for(SlidingMove s: legalMoves){
					SlidingBoard newState = new SlidingBoard(curr.board.size);
					newState.setBoard(curr.board);
					newState.doMove(s);
					if(!prevStates.contains(newState.toString())){
						prevStates.add(newState.toString());
						ArrayList<SlidingMove> childPath = (ArrayList<SlidingMove>) curr.path.clone();
						childPath.add(s);
						AStarNode newNode = new AStarNode(newState, curr.currDist+1, estMove(newState), childPath);
						frontier.add(newNode);
					}
				}
				curr = frontier.poll();
			}
			return curr.path;
		}
		return start.path;
	}
	/**
	 * Returns moves one by one from path returned by A* search algorithm
	 * 
	 * @param SlidingBoard
	 * @result SlidingMove
	 */
	public SlidingMove makeMove(SlidingBoard b){

		move_number++;
		return path.get(move_number);
	}
}
