
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.*;

import javax.swing.JPanel;

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
public abstract class GameObject {

	//Maybe use a static linkedlist of all game objects  
	
	public static JPanel JPANEL;
	public static int TICK_RATE;
	public static GameManager GAME_MANAGER;
	
	public GameObject() {
		// Maybe add to the static list of game objects
		
	}
	
	public void Tick()
	{
		if(active) {
			OnUpdate();
			
			//Recursively tick all children 
			for(GameObject g : children) { g.Tick(); }
		}
	}
	
	public void Draw(Graphics2D g2d)
	{
		if (visible) {
			OnRender(g2d);
			
			//Recursively draw all children
			for(GameObject g : children) { g.Draw(g2d);	}
		}
	}
	
	public GameObject GetParent() { return parent; }
	
	/**
	 * Unlinks the GameObject from its parent
	 */
	public void UnParent() {
		if(parent != null) {
			parent.GetChildren().remove(this);
		} else {
			System.out.println("Can't unparent when object doesn't have a parent!");
		}
		parent = null;
	}
	

	/**
	 * Unparents the current parent, and then adds the object
	 * as a child of the specified new parent.
	 * @param Parent - the GameObject to set as the parent
	 */
	public void SetParent(GameObject Parent) { 
		//Unparent from current parent first
		if(parent != null) UnParent();
		parent = Parent;
		Parent.GetChildren().add(this);
	} 
	
	public LinkedList<GameObject> GetChildren() { return children; }
	
	/**
	 * Adds the specified game object as a child of this GameObject
	 * @param child - the GameObject to be attached as a child 
	 */
	public void AddChild(GameObject child) {
		if(child.GetParent() == null) {
			child.SetParent(this);
		} else {
			System.out.println("The specified object is already the child of another.");
		}
	}
	
	/**
	 * Removes the specified
	 * @param child - the GameObject to be unlinked from this GameObject
	 */
	public void RemoveChild(GameObject child) {
		if(children.contains(child)) {
			child.UnParent();
		} else {
			System.out.println("Object was not a child!");
		}
	}
	
	/**
	 * @return the position of the GameObject with respect to the panel
	 */
	public Vec2 GetWorldPosition()
	{
		Vec2 retVal = position;
		GameObject currentParent = parent;
		
		while(currentParent != null) {
			retVal = retVal.plus(currentParent.position);
			currentParent = currentParent.GetParent();
		}
		
		return retVal;
	}
	
	/**
	 * @return Position of mouse scaled to game coordinates
	 */
	public Vec2 GetScaledMousePosition()
	{
		Point mousePos = JPANEL.getMousePosition();
		if(mousePos != null) {
			float mx = (float) mousePos.x / JPANEL.getWidth();
			float my = (float) mousePos.y / JPANEL.getHeight();
			return new Vec2(mx, my);
		} else {
			return null;
		}
	}
	
	/**
	 * @return List of all children of children of children
	 */
	public LinkedList<GameObject> GetChildrenRecursively()
	{
		LinkedList<GameObject> allGameObject = new LinkedList<GameObject>();
		for(GameObject obj : children) {
			allGameObject.add(obj);
			allGameObject.addAll(obj.GetChildrenRecursively());
		}
		return allGameObject;
	}
	
	/**
	 * @return true if item is actually visible (all parents are visible)
	 */
	public boolean IsVisible()
	{
		boolean retVal = visible;
		GameObject currentParent = parent;
		
		while(retVal && currentParent != null) {
			retVal = currentParent.visible;
			currentParent = currentParent.GetParent();
		}
		
		return retVal;
	}
	
	public Vec2 position = new Vec2();
	public boolean active = true; //If true, the object executes OnUpdate every frame
	public boolean visible = true; //If true, the object executes OnRender every frame
	
	//These are the main functions that need to be implemented
	abstract protected void OnUpdate();
	abstract protected void OnRender(Graphics2D g2d);
	
	//The position of the game object is relative to the parent game object
	protected GameObject parent = null; 
	protected LinkedList<GameObject> children = new LinkedList<GameObject>();

}
