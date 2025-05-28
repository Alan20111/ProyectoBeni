/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicioarchivos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonVisibility extends JFrame implements ActionListener {

    private JRadioButton radioShow, radioHide;
    private JLabel labelToControl;
    private JPanel panelToControl;

    public RadioButtonVisibility() {
        setTitle("Radio Button Visibility Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        radioShow = new JRadioButton("Show Component");
        radioHide = new JRadioButton("Hide Component");

        ButtonGroup group = new ButtonGroup();
        group.add(radioShow);
        group.add(radioHide);

        radioShow.addActionListener(this);
        radioHide.addActionListener(this);

        labelToControl = new JLabel("This is a Label");
        panelToControl = new JPanel();
        panelToControl.add(labelToControl);
        panelToControl.setBackground(Color.LIGHT_GRAY);

        add(radioShow);
        add(radioHide);
        add(panelToControl);

        panelToControl.setVisible(false); // Initially hide the component

        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == radioShow) {
            panelToControl.setVisible(true);
        } else if (e.getSource() == radioHide) {
            panelToControl.setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RadioButtonVisibility::new);
    }
}