package gameEngine;

public abstract class UIObject extends GameObject {

	public UIObject() {
		
	}
	
	
	abstract public boolean MouseSelected();
	abstract public void OnMouseDown();
	abstract public void OnMouseUp();
	
}
