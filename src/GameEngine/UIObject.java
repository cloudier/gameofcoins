package gameEngine;

public abstract class UIObject extends GameObject {
	abstract public boolean mouseSelected();
	abstract public void onMouseDown();
	abstract public void onMouseUp();	
}
