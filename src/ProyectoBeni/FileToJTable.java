/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoBeni;

/**
 *
 * @author markb
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class FileToJTable {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Text File to JTable");
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        
        model.addColumn("Column 1");
        model.addColumn("Column 2"); // Add more columns as needed
        model.addColumn("Column 3");

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                model.addRow(line.split(" ")); // Adjust delimiter as needed
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.add(new JScrollPane(table));
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}