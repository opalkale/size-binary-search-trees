package sizebst;

/**
 * Instances of class SizeBSTN are Nodes of the Size Binary Search Tree 
 * @author lou
 *
 */
public class SizeBSTN {
	SizeBSTN LSubtree;  // left subtree of this tree (may be null)
	SizeBSTN RSubtree;  // right subtree of this tree (may be null)
	int data; // data at this node of the tree
	int size; // number of tree entries that are less than or equal to data

	/**
	 * create a new leaf of the tree with the given data
	 * @param data
	 */
	public SizeBSTN(int data){
		LSubtree = null;
		RSubtree = null;
		this.data = data;
		size = 1;
	}

	/* see assignment for proper format for toString
	 */
	public String toString(){

		if (this.RSubtree == null && this.LSubtree == null)
		{
			return  "[null "+this.data+","+this.size+" null]";
		}
		else if (this.RSubtree != null && this.LSubtree == null){

			return "[null "+this.data+","+this.size+","+this.RSubtree.toString() + "]";
		}
		else if (this.LSubtree != null && this.RSubtree == null){

			return "["+this.LSubtree.toString()+"," +this.data+" "+this.size+" null]";
		}
		else 
		{
			return "["+this.LSubtree.toString()+this.data+","+this.size + this.RSubtree.toString() +"]";
		}
	}

	/**
	 * search for the number target in this tree
	 * @param target number to search for
	 * @return either the node that holds target,
	 * if there is one, or the node which should point to the node that 
	 * will hold target if it is added now  
	 */
	public SizeBSTN getNode(int target)
	{	
		if (this.data == target)
		{
			return this;
		}
		else if (this.RSubtree == null && this.LSubtree == null)
		{
			return this; 
		}
		else if (target < this.data)
		{
			if (this.LSubtree == null)
				return this; 
			else
				return this.LSubtree.getNode(target);
		}
		else if(target > this.data)
		{
			if (this.RSubtree == null)
				return this; 
			else
				return this.RSubtree.getNode(target);
		}
		else {
			return this;
		}

	}

	private SizeBSTN getNodeIncr_recur(int target)
	{
		if (this.data == target)
		{
			return this;
		}
		else if (this.RSubtree == null && this.LSubtree == null)
		{
			if (target < this.data)
				this.size++;
			return this; 
		}
		else if (target < this.data)
		{
			this.size++; 
			if (this.LSubtree == null)
				return this; 
			else
				return this.LSubtree.getNodeIncr_recur(target);
		}
		else if(target > this.data)
		{
			if (this.RSubtree == null)
				return this; 
			else
				return this.RSubtree.getNodeIncr_recur(target);
		}
		else {
			return this;
		}
	}

	/**
	 * like getNode but increments size fields as appropriate
	 * @param target number to search for
	 */
	public void getNodeIncr(int target){
		SizeBSTN temp = this.getNode(target);
		if (temp.data != target)
		{
			getNodeIncr_recur(target); 
		}		
	}


	/**
	 * actually calculates number of numbers <= target.  
	 * Does search for target like getNode but adds up 
	 * the size fields of all nodes whose data is <= target.
	 * @return the number of nodes in this tree with data <= target
	 */
	public int getNodeSum(int target){
		int sum = 0; 
		if (this == null){
			return -1;
		}
		else
		{
			return getNodeSum_recur(this, target, sum); 
		}
	}
	private int getNodeSum_recur(SizeBSTN root, int target, int sum)
	{
		if (root == null)
		{
			return sum;
		}
		else if (target < root.data)
		{
			return getNodeSum_recur(root.LSubtree, target, sum);
		}
		else if (target > root.data)
		{
			sum = sum + root.size; 
			return getNodeSum_recur(root.RSubtree, target, sum);
		}
		else
		{
			return sum; 
		}

	}
}	

