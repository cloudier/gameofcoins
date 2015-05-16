/**
 * Game Object class.
 * This is the base class for all elements that are in the
 * game universe. Game objects have a visual representation 
 * as well as a behaviour. The visual representation is
 * created by implementing the OnRender() function, and
 * the behaviour is created by implementing OnUpdate().
 *  
 * @author ivormetcalf
 */

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.*;

public abstract class GameObject {

	//Maybe use a static linkedlist of all game objects  
	
	public GameObject() {
		// Maybe add to the static list of game objects
	}
	
	public void Tick()
	{
		if(active) {
			OnUpdate();
		}
	}
	
	public void Draw(Graphics2D g2d)
	{
		if (visible) {
			OnRender(g2d);
		}
	}
	
	public GameObject GetParent() { return parent; }
	public LinkedList<GameObject> GetChildren() { return children; }
	
	public boolean isActive() {	return active; }
	public void setActive(boolean active) {	this.active = active; }
	public boolean isVisible() { return visible; }
	public void setVisible(boolean visible) { this.visible = visible; }
	
	//These are the main functions that need to be implemented
	abstract protected void OnUpdate();
	abstract protected void OnRender(Graphics2D g2d);
	abstract protected void OnResize(Dimension panelSize);
	
	protected Vec2 position;
	
	protected boolean active = true; //If true, the object executes OnUpdate every frame
	protected boolean visible = true; //If true, the object executes OnRender every frame
	
	protected GameObject parent;
	protected LinkedList<GameObject> children;

}
