import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
/**
 * 
 * @author Winston
 *Bot using Iterative Depth First search to go through state tree
 *Modeled after github code
 *
 *@param SlidingBoard
 */
public class IterDepthBot extends SlidingPlayer {

	private ArrayList<SlidingMove> path;
	private int move_number = -1;

	public IterDepthBot(SlidingBoard _sb) {
		super(_sb);
		path = idfs(_sb, 0);
	}
	/**
	 * Iterative depth first search implemented with Java's Stack object
	 * Returns path from starting board to solved board
	 * 
	 * @param SlidingBoard, int
	 * @return ArrayList<SlidingMove>
	 */
	public ArrayList<SlidingMove> idfs(SlidingBoard sb, int maxDepth){

		Stack<TreeNode> depthTree = new Stack<TreeNode>();
		HashSet<String> prevStates = new HashSet<String>();
		TreeNode curr = new TreeNode(sb, new ArrayList<SlidingMove>());


		while(!curr.getS().isSolved()){
			int currDepth = curr.getPath().size();
			if(currDepth == maxDepth){
				maxDepth++;
			}
			if(currDepth < maxDepth){
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
		}

		return curr.getPath();
	}
	/**
	 * Returns moves one by one from path returned by iterative depth first search algorithm
	 * 
	 * @param SlidingBoard
	 * @result SlidingMove
	 */
	public SlidingMove makeMove(SlidingBoard b){

		move_number++;
		return path.get(move_number);
	}
}