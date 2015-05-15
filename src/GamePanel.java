import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	public Dimension getPreferredSize() {
        return new Dimension(600,600);
    }
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        
        Graphics2D g2d = (Graphics2D) g;        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        		RenderingHints.VALUE_ANTIALIAS_ON);
        
        if(windowResized)
        {
        	for(GameObject gameObj : gameObjects)
        	{
        		gameObj.OnResize(getSize());
        	}
        }
        
        for(GameObject gameObj : gameObjects)
        {
        	gameObj.Draw(g2d);
        }
    }
    
    

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void SetGameObjectList(LinkedList<GameObject> GameObjects)
	{
		gameObjects = GameObjects;
	}
	
	boolean windowResized = false;
	
	LinkedList<GameObject> gameObjects;

}
