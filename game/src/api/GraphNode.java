package api;


public interface GraphNode {
	
	public GraphNode getNNode();
	public GraphNode getSNode();
	public GraphNode getENode();
	public GraphNode getWNode();
	
	public GraphNode getNENode();
	
	public GraphNode getSENode();
	
	public GraphNode getNWNode();
	public GraphNode getSWNode();
	public Obstruction getObstruction();
	public String getBackground();
	public Actor getActor();
	public void setNNode(GraphNode n);
	public void setSNode(GraphNode s);
	public void setENode(GraphNode e);
	public void setWNode(GraphNode w);
	
	public void setObstruction(Obstruction o);
	public void setBackground(String str);
	
	public void setActor(Actor a);
	public String getCoordinates();
	public boolean getIsChanged();
	
	public void setIsChanged(boolean b);
}
