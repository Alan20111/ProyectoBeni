package inicioarchivos.betatester;

/**
 *
 * @author markb
 */

import javax.swing.*;
public class BetaTester 
{
    JFrame ven;
    public BetaTester(){
        initComponents();
    }
    
    public void initComponents()
    {
        ven = new JFrame("Easter Egg");
        ven.setAlwaysOnTop(true);
        ven.setSize(300,300);
        ven.setVisible(true);
    }
    public static void main(String[] args)
    {
        BetaTester bt = new BetaTester();
    }
}
