package inicioarchivos.betatester;

/**
 *
 * @author markb
 */

import java.awt.*;
import javax.swing.*;
public class BetaTester 
{
    JFrame ven;
    ImageIcon Duke1, Duke2, Duke3;
    
    public BetaTester(){
        initComponents();
    }
    
    public void initComponents()
    {
        ven = new JFrame("Easter Egg");
        ven.setAlwaysOnTop(true);
        ven.setSize(300,300);
        ven.setVisible(true);
        
        JPanel panelTODO = new JPanel();
        panelTODO.setVisible(true);
        
        JLabel showImage = new JLabel();
        
        Duke1 = new ImageIcon((getClass().getResource("/inicioarchivos/Duke.png")));
        Image image1 = Duke1.getImage();
        Image duke = image1.getScaledInstance(120, 200, Image.SCALE_SMOOTH);
        Duke1 = new ImageIcon(duke);
        
        Duke2 = new ImageIcon((getClass().getResource("/inicioarchivos/betatester/Duke3D.png")));
        Image image2 = Duke2.getImage();
        Image duke2 = image2.getScaledInstance(120, 200, Image.SCALE_SMOOTH);
        Duke1 = new ImageIcon(duke2);
        
        Duke3 = new ImageIcon((getClass().getResource("/inicioarchivos/betatester/Duke-Guitar.png")));
        Image image3 = Duke3.getImage();
        Image duke3 = image3.getScaledInstance(120, 200, Image.SCALE_SMOOTH);
        Duke3 = new ImageIcon(duke3);
        
        int i= (int)(Math.random()*3)+1;
        switch(i)
        {
            case 1 -> showImage.setIcon(Duke1);
            case 2 -> showImage.setIcon(Duke2);
            case 3 -> showImage.setIcon(Duke3);
                
        }
        panelTODO.add(showImage);
        ven.add(panelTODO);
    }
    public static void main(String[] args)
    {
        BetaTester bt = new BetaTester();
    }
}
