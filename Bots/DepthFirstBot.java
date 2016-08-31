
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
//Uses github code
public class DepthFirstBot extends SlidingPlayer {

	private ArrayList<SlidingMove> path;
	private int move_number = -1;

	public DepthFirstBot(SlidingBoard _sb) {
		super(_sb);
		path = dfs(_sb);
	}

	public ArrayList<SlidingMove> dfs(SlidingBoard sb){

		Stack<TreeNode> breadthTree = new Stack<TreeNode>();
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
					breadthTree.push(newNode);
				}
			}
			curr = breadthTree.pop();
		}

		return curr.getPath();
	}

	public SlidingMove makeMove(SlidingBoard b){

		move_number++;
		return path.get(move_number);
	}
}
