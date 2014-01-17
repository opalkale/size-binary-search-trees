package sizebst;
//GET RID OF STATIC STATEMENTS

/**
 * Class SizeBST represents a Binary Search Tree that can also be used, for any integer j,
 *  to answer the question "how many numbers in the tree are less than or equal to j" in worst 
 *  case time h where h is the height of the tree (not the number of nodes).
 * 
 *  The actual nodes of the tree are of class SizeBSTN.  SizeBST represents the overall tree.
 *  IF instance variable rootNode is null, the tree is empty, otherwise rootNode is the root
 *  of the tree of SizeBSTN's
 * @author lou
 *
 */
public class SizeBST {
	SizeBSTN rootNode;

	public SizeBST(SizeBSTN root){
		rootNode =  root;
	}

	public String toString(){
		if (rootNode == null)
			return "(null)";
		else {

			return "("+ rootNode.toString() + ")";
		}
	}

	/**
	 * @param target the number to search for
	 * @return true iff target is in this tree
	 */
	public boolean search(int target)
	{
		return search_recur(rootNode, target); 	
	}
	private boolean search_recur(SizeBSTN root, int target) 
	{
		if (root == null)
		{
			return false;
		}
		else if (target < root.data)
		{
			return search_recur(root.LSubtree, target);
		}
		else if (target > root.data)
		{
			return search_recur(root.RSubtree, target);
		}
		else
		{
			return true; 
		}

	}

	/**
	 * insert newData into tree;  if already there, do not change tree
	 * @param newData int to insert
	 */
	public void insert(int newData){
		if (rootNode == null)
		{
			rootNode = new SizeBSTN(newData); 
			return; 
		}
		SizeBSTN temp = rootNode.getNode(newData);
		if (temp.data == newData)
			return; 
		else
		{
			SizeBSTN newNode = new SizeBSTN(newData);

			if (newNode.data > temp.data)
			{
				rootNode.getNodeIncr(newData);
				temp.RSubtree = newNode; 
				newNode.size = 1; 
			}
			else 
			{
				rootNode.getNodeIncr(newData);
				temp.LSubtree = newNode; 
				newNode.size = 1; 
			}
		}
	}

	/**
	 * @return returns how many numbers in the tree are less than or equal to target.  Returns -1 if tree is empty
	 * @param target
	 */
	public int numLEq(int target){
		int sum = 0; 
		if (rootNode == null){
			return -1;
		}
		else
		{
			return numLEq_recur(rootNode, target, sum); 
		}
	}
	private int numLEq_recur(SizeBSTN root, int target, int sum)
	{
		if (root == null)
		{
			//System.out.println(sum);
			return sum;
		}
		else if (target < root.data)
		{
			//System.out.println(sum);
			return numLEq_recur(root.LSubtree, target, sum);
		}
		else if (target > root.data)
		{
			sum = sum + root.size; 
			//System.out.println(sum);
			return numLEq_recur(root.RSubtree, target, sum);
		}
		else
		{
			sum += root.size;
			//System.out.println(sum);
			return sum; 
		}
	}	

	public static void main(String args []){
		SizeBST tree1 = new SizeBST(null);
		System.out.println("empty: "+tree1);
		tree1.insert(40);
		System.out.println("40 "+tree1);



		// add any test code you want here - this is not graded 
		//SizeBSTN tree = new SizeBSTN(40);
		//tree.RSubtree = new SizeBSTN(60);
		//tree.LSubtree = new SizeBSTN(20);
		
		//System.out.println(tree.toString());
		
		/*SizeBST masterTree = new SizeBST(tree);
		masterTree.insert(20);
		masterTree.insert(30);
		masterTree.insert(60);  
		masterTree.insert(10); 
		masterTree.insert(50); 
		masterTree.insert(55);
		masterTree.insert(53);
		masterTree.numLEq(55);
		*/

	}
}
