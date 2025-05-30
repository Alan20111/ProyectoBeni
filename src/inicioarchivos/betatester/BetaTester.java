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
        ven.setSize(400,200);
        ven.setVisible(true);
        
        JPanel panelTODO = new JPanel();
        panelTODO.setLayout(new BorderLayout(10,10));
        panelTODO.setVisible(true);
        
        JPanel texto = new JPanel();
        texto.setLayout(new BoxLayout(texto,BoxLayout.Y_AXIS));
        
        JLabel showImage = new JLabel();
        JLabel muchoTexto = new JLabel();

        Box.Filler filler1 = new javax.swing.Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        ven.add(filler1, java.awt.BorderLayout.EAST);
        Box.Filler filler2 = new javax.swing.Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        ven.add(filler2, BorderLayout.WEST);
        
        Duke1 = new ImageIcon((getClass().getResource("/inicioarchivos/Duke.png")));
        Image image1 = Duke1.getImage();
        Image duke = image1.getScaledInstance(60, 100, Image.SCALE_SMOOTH);
        Duke1 = new ImageIcon(duke);
        
        Duke2 = new ImageIcon((getClass().getResource("/inicioarchivos/betatester/Duke3D.png")));
        Image image2 = Duke2.getImage();
        Image duke2 = image2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        Duke2 = new ImageIcon(duke2);
        
        Duke3 = new ImageIcon((getClass().getResource("/inicioarchivos/betatester/Duke-Guitar.png")));
        Image image3 = Duke3.getImage();
        Image duke3 = image3.getScaledInstance(90, 100, Image.SCALE_SMOOTH);
        Duke3 = new ImageIcon(duke3);
        
        String t="""
                 \"Estaban los tomatitos,
                 Muy Contentitos,
                 Cuando llegó el verdugo
                 a hacerlos jugo\"
                 *ring* *ring* *ring*
                 """;
        int n=(int)(Math.random()*3)+1;
        switch(n)
        {
            case 1 -> muchoTexto.setText("¿Cómo la veis bol?");
            case 2 -> muchoTexto.setText("Déjame Alan no me hagas daño!");
            case 3 -> muchoTexto.setText("Bailan las Rochas y las Chetas");
        }
        
        int i= (int)(Math.random()*3)+1;
        System.out.println(i);
        switch(i)
        {
            case 1 -> showImage.setIcon(Duke1);
            case 2 -> showImage.setIcon(Duke2);
            case 3 -> { showImage.setIcon(Duke3);
                        muchoTexto.setText(t);}
                
        }
        panelTODO.add(showImage,  BorderLayout.WEST);
        panelTODO.add(muchoTexto,  BorderLayout.CENTER);
        ven.add(panelTODO);
    }
    public static void main(String[] args)
    {
        BetaTester bt = new BetaTester();
    }
}
