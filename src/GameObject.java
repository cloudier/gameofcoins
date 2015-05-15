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
	
	public void Draw()
	{
		if (visible) {
			OnRender();
		}
	}
	
	abstract public void OnRender();
	abstract public void OnUpdate();
	abstract public void OnResize();
	
	public GameObject GetParent() { return parent; }
	public LinkedList<GameObject> GetChildren() { return children; }
	
	public boolean isActive() {	return active; }
	public void setActive(boolean active) {	this.active = active; }
	public boolean isVisible() { return visible; }
	public void setVisible(boolean visible) { this.visible = visible; }
	
	
	
	private Vec2 position;
	
	private boolean active; //If true, the object executes OnUpdate every frame
	private boolean visible; //If true, the object executes OnRender every frame
	
	private GameObject parent;
	private LinkedList<GameObject> children;

}
