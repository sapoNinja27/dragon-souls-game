package World;

public class Node {
	public Vector2i tile;
	public Node parent;
	public double fCost,gCost,hCost;
	public Node (Vector2i tile,Node parent, double gCost, double hCost) {
		this.tile=tile;
		this.gCost=gCost;
		this.parent=parent;
		this.hCost=hCost;
		this.fCost=hCost+gCost;
	}
}
