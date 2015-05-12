import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements ActionListener {

	
	
	
	private static final long serialVersionUID = 1L;
	
	public Dimension getPreferredSize() {
        return new Dimension(640,480);
    }
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
