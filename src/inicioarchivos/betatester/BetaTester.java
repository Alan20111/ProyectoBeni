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
    ImageIcon Duke1, Duke2, Duke3, DukeG1, DukeG2;
    
    public BetaTester(){
        initComponents();
    }
    
    @SuppressWarnings("empty-statement")
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
        
        DukeG1 = new ImageIcon((getClass().getResource("/inicioarchivos/betatester/java-duke.gif")));
        DukeG2 = new ImageIcon((getClass().getResource("/inicioarchivos/betatester/Duke High Five.gif")));
        
        JLabel case31 = new JLabel("\"Estaban los tomatitos");
        JLabel case32 = new JLabel("Muy Contentitos,");
        JLabel case33 = new JLabel("Cuando llegó el verdugo");
        JLabel case34 = new JLabel(" a hacerlos jugo\"");
        JLabel case35 = new JLabel("*ring* *ring* *ring*");
        JPanel case3 = new JPanel();
        case3.add(case31);
        case3.add(case32);
        case3.add(case33);
        case3.add(case34);
        case3.add(case35);
        case3.setLayout(new BoxLayout(case3,BoxLayout.Y_AXIS));
        case3.setAlignmentY(Box.LEFT_ALIGNMENT);
        case3.setVisible(false);

        int n=(int)(Math.random()*5)+1;
        switch(n)
        {
            case 1 -> muchoTexto.setText("¿Cómo la veis bol?");
            case 2 -> muchoTexto.setText("Déjame Alan no me hagas daño!");
            case 3 -> muchoTexto.setText("Bailan las Rochas y las Chetas");
            case 4 -> {
                int N=(int)(Math.random()*3)+1;
                switch(N)
                    {
                        case 1 -> muchoTexto.setText("Basquetbol!");
                        case 2 -> muchoTexto.setText("Me gusta el café Java");
                        case 3 -> muchoTexto.setText("Te quiero mucho Duke"); 
                    }
            }
            case 5 -> {
                int N = (int)(Math.random()*3)+1;
                switch(N)
                    {
                        case 1 -> muchoTexto.setText("System.out.printl(\"texto\");");
                        case 2 -> muchoTexto.setText("Intente registrar a un alumno con No. Control Sahur y nombre null");
                        case 3 -> muchoTexto.setText("Tiro por suerte! "+(int)(Math.random()*100)+1); 
                    }
                }
            }
        
        int i= (int)(Math.random()*5)+1;
        System.out.println(i);
        switch(i)
        {
            case 1 -> showImage.setIcon(Duke1);
            case 2 -> showImage.setIcon(Duke2);
            case 3 -> showImage.setIcon(Duke3);
            case 4, 5 -> {
                int I = (int)(Math.random()*3)+1;
                switch(I)
                    {
                        case 1 -> showImage.setIcon(Duke1);
                        case 2 -> showImage.setIcon(Duke2);
                        case 3 -> showImage.setIcon(Duke3);
                    }
                }
        }
        if(i==4&n==4){
            showImage.setIcon(DukeG1);
            muchoTexto.setText(null);
            ven.setSize(500, 350);
            ven.setTitle("Java is here, Java is Everywhere");
        }
        if(i==5&n==5){
            showImage.setIcon(DukeG2);
            muchoTexto.setText(null);
            ven.setSize(500, 350);
            ven.setTitle("Duke quiere chocar los cinco");
        }
        
        panelTODO.add(showImage,  BorderLayout.WEST);
        if(n!=3){panelTODO.add(muchoTexto,  BorderLayout.CENTER);}
        if(n==3){panelTODO.add(case3, BorderLayout.CENTER);}
        ven.add(panelTODO);
    }
    
    public static void main(String[] args)
    {
        BetaTester bt = new BetaTester();
    }
}
