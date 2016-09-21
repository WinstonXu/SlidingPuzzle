
import java.util.ArrayList;
/**
 * 
 * @author Winston
 *Node modeled after Matthew's github code
 *Used in breadth and depth first searches
 *Only has getter and setter methods
 *
 *@param SlidingBoard, ArrayList<SlidingMove>
 */
public class TreeNode {

	private SlidingBoard s;
	private ArrayList<SlidingMove> path = new ArrayList<SlidingMove>();
	
	public TreeNode(SlidingBoard sb, ArrayList<SlidingMove> _path){
		this.s = sb;
		this.path = _path;
	}
	
	public SlidingBoard getS() {
		return s;
	}

	public void setS(SlidingBoard s) {
		this.s = s;
	}

	public void setPath(ArrayList<SlidingMove> _path){
		this.path = _path;
	}
	
	public ArrayList<SlidingMove> getPath(){
		return this.path;
	}
}
