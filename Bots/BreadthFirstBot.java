import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
//Uses github code
public class BreadthFirstBot extends SlidingPlayer{

	private ArrayList<SlidingMove> path;
    private int move_number = -1;

	public BreadthFirstBot(SlidingBoard sb){
		super(sb);
		path = bfs(sb);
	}

	public ArrayList<SlidingMove> bfs(SlidingBoard sb){
		
		ArrayDeque<TreeNode> breadthTree = new ArrayDeque<TreeNode>();
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
					breadthTree.add(newNode);
				}
			}
			curr = breadthTree.poll();
		}
		
		return curr.getPath();
	}

	public SlidingMove makeMove(SlidingBoard b){

		move_number++;
		return path.get(move_number);
	}
}