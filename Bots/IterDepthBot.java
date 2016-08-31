import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
//Uses github code, Does not work for most 3x3 arrays
public class IterDepthBot extends SlidingPlayer {

	private ArrayList<SlidingMove> path;
	private int move_number = -1;

	public IterDepthBot(SlidingBoard _sb) {
		super(_sb);
		path = idfs(_sb, 0);
	}

	public ArrayList<SlidingMove> idfs(SlidingBoard sb, int maxDepth){

		Stack<TreeNode> depthTree = new Stack<TreeNode>();
		HashSet<String> prevStates = new HashSet<String>();
		TreeNode curr = new TreeNode(sb, new ArrayList<SlidingMove>());
		int currDepth = 0;

		while(!curr.getS().isSolved()){
			maxDepth++;
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
				currDepth++;
				curr = depthTree.pop();
			}
		}

		return curr.getPath();
	}

	public SlidingMove makeMove(SlidingBoard b){

		move_number++;
		return path.get(move_number);
	}
}