
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * 
 * @author Winston
 *Bot using depth first searcht to go through state tree
 *Does not consistently work on 3x3 puzzles-i.e. gets really lucky to solve
 *Modeled after github code
 *
 *@param SlidingBoard
 */
public class DepthFirstBot extends SlidingPlayer {

	private ArrayList<SlidingMove> path;
	private int move_number = -1;

	public DepthFirstBot(SlidingBoard _sb) {
		super(_sb);
		path = dfs(_sb);
	}
	/**
	 * Depth first search implemented with Java's Stack object
	 * Returns path from starting board to solved board
	 * 
	 * @param SlidingBoard
	 * @return ArrayList<SlidingMove>
	 */
	public ArrayList<SlidingMove> dfs(SlidingBoard sb){

		Stack<TreeNode> depthTree = new Stack<TreeNode>();
		HashSet<String> prevStates = new HashSet<String>();
		TreeNode curr = new TreeNode(sb, new ArrayList<SlidingMove>());

		while(!curr.getS().isSolved()){

			ArrayList<SlidingMove> legalMoves = curr.getS().getLegalMoves();
			for(SlidingMove s: legalMoves){
				SlidingBoard newState = new SlidingBoard(curr.getS().size);
				newState.setBoard(curr.getS());
				newState.doMove(s);		
				if(!prevStates.contains(newState.toString())){
					prevStates.add(newState.toString());
					ArrayList<SlidingMove> childPath = (ArrayList<SlidingMove>) curr.getPath().clone();
					childPath.add(s);
					TreeNode newNode = new TreeNode(newState, childPath);
					depthTree.push(newNode);
				}
			}
			curr = depthTree.pop();
		}

		return curr.getPath();
	}
	/**
	 * Returns moves one by one from path returned by depth first search algorithm
	 * 
	 * @param SlidingBoard
	 * @result SlidingMove
	 */
	public SlidingMove makeMove(SlidingBoard b){

		move_number++;
		return path.get(move_number);
	}
}
