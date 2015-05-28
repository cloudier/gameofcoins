package gameEngine;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.*;

import javax.swing.JPanel;

/**
 * Game Object class. This is the base class for all elements that are in the
 * game universe. Game objects have a visual representation as well as a
 * behaviour. The visual representation is created by implementing the
 * OnRender() function, and the behaviour is created by implementing OnUpdate().
 * 
 * @author ivormetcalf
 */
public abstract class GameObject {

	// Maybe use a static linkedlist of all game objects

	public static JPanel JPANEL;
	public static int TICK_RATE;
	public static WindowManager GAME_MANAGER;
	public static ImageStore IMAGE_STORE = new ImageStore();

	public Vec2 position = new Vec2();
	public boolean active = true; // If true, the object executes OnUpdate every
									// frame
	public boolean visible = true; // If true, the object executes OnRender
									// every frame

	// These are the main functions that need to be implemented
	abstract protected void onUpdate();
	abstract protected void onRender(Graphics2D g2d);

	// The position of the game object is relative to the parent game object
	protected GameObject parent = null;
	protected LinkedList<GameObject> children = new LinkedList<GameObject>();

	public GameObject() {
		// Maybe add to the static list of game objects
	}
	
	public void tick() {
		if (active) {
			onUpdate();

			// Recursively tick all children
			for (GameObject g : children) {
				g.tick();
			}
		}
	}

	public void draw(Graphics2D g2d) {
		if (visible) {
			onRender(g2d);

			// Recursively draw all children
			for (GameObject g : children) {
				g.draw(g2d);
			}
		}
	}

	public GameObject getParent() {
		return parent;
	}

	/**
	 * Unlinks the GameObject from its parent
	 */
	public void unParent() {
		if (parent != null) {
			parent.getChildren().remove(this);
		} else {
			System.out
					.println("Can't unparent when object doesn't have a parent!");
		}
		parent = null;
	}

	/**
	 * Unparents the current parent, and then adds the object as a child of the
	 * specified new parent.
	 * 
	 * @param Parent
	 *            - the GameObject to set as the parent
	 */
	public void setParent(GameObject Parent) {
		// Unparent from current parent first
		if (parent != null)
			unParent();
		parent = Parent;
		Parent.getChildren().add(this);
	}

	public LinkedList<GameObject> getChildren() {
		return children;
	}

	/**
	 * Adds the specified game object as a child of this GameObject
	 * 
	 * @param child
	 *            - the GameObject to be attached as a child
	 */
	public void addChild(GameObject child) {
		if (child.getParent() == null) {
			child.setParent(this);
		} else {
			System.out.println("The specified object is already the child of another.");
		}
	}

	/**
	 * Removes the specified
	 * 
	 * @param child
	 *            - the GameObject to be unlinked from this GameObject
	 */
	public void removeChild(GameObject child) {
		if (children.contains(child)) {
			child.unParent();
		} else {
			System.out.println("Object was not a child!");
		}
	}

	/**
	 * @return the position of the GameObject with respect to the panel
	 */
	public Vec2 getWorldPosition() {
		Vec2 retVal = position;
		GameObject currentParent = parent;

		while (currentParent != null) {
			retVal = retVal.plus(currentParent.position);
			currentParent = currentParent.getParent();
		}

		return retVal;
	}
	
	/**
	 * @return the position of the GameObject with respect to the panel
	 */
	public Vec2 getWorldPosition(Vec2 Position)
	{
		Vec2 retVal = Position;
		GameObject currentParent = parent;
		
		while(currentParent != null) {
			retVal = retVal.plus(currentParent.position);
			currentParent = currentParent.getParent();
		}
		
		return retVal;
	}
	
	/**
	 * Sets the position of the GameObject such that it
	 * is located at the specified world position
	 */
	public void setWorldPosition(Vec2 WorldPosition)
	{
		Vec2 oldWorldPos = getWorldPosition();
		Vec2 displacement = WorldPosition.minus(oldWorldPos);
		position = position.plus(displacement);
	}

	/**
	 * @return Position of mouse scaled to game coordinates
	 */
	public Vec2 getScaledMousePosition() 
	{
		Point mousePos = JPANEL.getMousePosition();
		if (mousePos != null) {
			float mx = (float) mousePos.x / JPANEL.getWidth();
			float my = (float) mousePos.y / JPANEL.getHeight();
			return new Vec2(mx, my);
		} else {
			return null;
		}
	}
	
	/**
	 * Converts a position to pixel coordinates
	 */
	public Point toPixelCoordinates(Vec2 position)
	{
		int x = (int) (position.x * JPANEL.getWidth());
		int y = (int) (position.y * JPANEL.getHeight());
		return new Point(x,y);
	}

	/**
	 * @return List of all children of children of children
	 */
	public LinkedList<GameObject> getChildrenRecursively() {
		LinkedList<GameObject> allGameObject = new LinkedList<GameObject>();
		for (GameObject obj : children) {
			allGameObject.add(obj);
			allGameObject.addAll(obj.getChildrenRecursively());
		}
		return allGameObject;
	}

	/**
	 * @return true if item is actually visible (all parents are visible)
	 */
	public boolean isVisible() {
		boolean retVal = visible;
		GameObject currentParent = parent;

		while (retVal && currentParent != null) {
			retVal = currentParent.visible;
			currentParent = currentParent.getParent();
		}

		return retVal;
	}
	
	/**
	 * Sets the active field of a GameObject and all its children.
	 */
	public void setActive(boolean b) {
		this.active = b;
		for (GameObject c : children) {
			c.active = b;
		}
	}

	/**
	 * Sets the visible field of a GameObject and all its children.
	 */
	public void setVisible(boolean b) {
		this.visible = b;
		for (GameObject c : children) {
			c.visible = b;
		}
	}

	/**
	 * Sets active and visible field of a GameObject and all its children.
	 */
	public void setActiveVisible(boolean b) {
		this.active = b;
		this.visible = b;
		for (GameObject c : children) {
			c.active = b;
			c.visible = b;
		}
	}
}
