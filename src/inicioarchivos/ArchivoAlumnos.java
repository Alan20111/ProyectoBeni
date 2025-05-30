package inicioarchivos;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ArchivoAlumnos extends Archivos {

    Alumno al = new Alumno();
    Scanner sc = new Scanner(System.in);
    final int tr = 53;

    @Override
    public void inicioMen() {
        canal = null;
        try {
            canal = new RandomAccessFile("alumnos.dat", "rw");
        } catch (IOException e) {
                       JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public boolean altas(RandomAccessFile canal, String a, String b, byte c) {
        try {
            al.capturar(a, b, c);
            int reg = (int) canal.length() / tr;
            grabarReg(canal, reg, al);
            JOptionPane.showMessageDialog(null, "Almuno Agregado", "Notificación", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Almuno no Agregado", "Notificación", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    @Override
    public void reporte(RandomAccessFile canal) {
        String fichero, aux;
            PrintWriter salida = null;
            try {
                fichero = "reporteAlumnos.rep.txt";
                salida = new PrintWriter(new FileWriter(fichero, true));
                String res = "";
                salida.println("REPORTE DE ALUMNOS");
                salida.println("__________________________________________________________________________");
                salida.println(String.format("%11s \t| %-40s \t|%9s", "No. Control", "Nombre", "Semestre"));
                salida.println("__________________________________________________________________________");
                for (int i = 0; i < (int) canal1.length() / tr; i++) {
                    Alumno al = new Alumno();
                    leerReg(canal1, i, al);
                    salida.println(String.format("%8s \t| %-40s \t|%9d", al.nroCtrl, al.nom, al.sem));
                }
                salida.println("\n\n");
                salida.println("NULL NULL NULL SAHUR");
                salida.close();
            }catch (IOException e) {
                       JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    @Override
    public int busqueda(RandomAccessFile canal, String bus) {
        ordenar(canal);
        if (bus.isEmpty()) {
            bus = sc.nextLine();
        }
        int li = 0;
        int pm;
        try {
            int ls = (int) (canal.length() / tr) - 1;
            do {
                pm = (li + ls) / 2;
                leerReg(canal, pm, al);
                if (al.nroCtrl.compareTo(bus) < 0) {
                    li = pm + 1;
                } else {
                    ls = pm - 1;
                }
            } while (!bus.equals(al.nroCtrl) && li <= ls);
            if (bus.equals(al.nroCtrl)) {
                return pm;
            } else {
                System.out.println(bus + " no existe");
                return -1;
            }
        } catch (IOException e) {
                       JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

            return 0;
        }
    }

    @Override
    public int modificaciones(RandomAccessFile canal, int ID, String noCtrl, String nom, byte sem) {
        ordenar(canal);
        System.out.println("Id recibido:" + ID);
        al.capturar(noCtrl, nom, sem);
        grabarReg(canal, ID, al);
        return 0;
    }

    @Override
    public void ordenar(RandomAccessFile canal) {
        try {
            Alumno al2 = new Alumno();
            for (int pas = 1; pas < (int) (canal.length()) / tr; pas++) {
                for (int co = 1; co <= ((int) (canal.length() / tr) - pas); co++) {
                    leerReg(canal, co - 1, al);
                    leerReg(canal, co, al2);
                    if (al.nroCtrl.compareTo(al2.nroCtrl) > 0) {
                        grabarReg(canal, co - 1, al2);
                        grabarReg(canal, co, al);
                    }
                }
            }
        } catch (IOException e) {
                       JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void leerReg(RandomAccessFile canal, int nReg, Alumno x) {
        try {
            canal.seek(nReg * tr);
            x.nroCtrl = canal.readUTF();
            x.nom = canal.readUTF();
            x.sem = canal.readByte();
        } catch (IOException e) {
                       JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void grabarReg(RandomAccessFile canal, int nReg, Alumno x) {
        try {
            canal.seek(nReg * tr);
            canal.writeUTF(String.format("%8s", x.nroCtrl.length() > 8 ? x.nroCtrl.substring(0, 8) : x.nroCtrl));
            canal.writeUTF(String.format("%-40s", x.nom.length() > 40 ? x.nom.substring(0, 40) : x.nom));
            canal.write(x.sem);
        } catch (IOException e) {
                       JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
}
