package inicioarchivos;

import java.util.Scanner;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ArchivoInscripciones extends Archivos {

    Alumno al = new Alumno();
    Materia ma;
    Inscripcion in = new Inscripcion();
    ArchivoAlumnos archAl = new ArchivoAlumnos();
    ArchivoMaterias archMa = new ArchivoMaterias();
    ;
    final int tr = 16;
    private Scanner sc = new Scanner(System.in);

    @Override
    public void inicioMen() {
        canal = null;
        canal1 = null;
        canal2 = null;
        try {
            canal = new RandomAccessFile("inscripciones.dat", "rw");
            canal1 = new RandomAccessFile("alumnos.dat", "rw");
            canal2 = new RandomAccessFile("materias.dat", "rw");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public boolean altas(RandomAccessFile canal, String a, String b, byte c) {
        ordenar(canal);
        try {
            int n;
            char opc = 0;
            al = new Alumno();
            ma = new Materia();
            in = new Inscripcion();

            int reg = (int) canal.length() / tr;
            do {
                n = archAl.busqueda(canal1, a);
                if (n == -1) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } while (n == -1);
            archAl.leerReg(canal1, n, al);
            n = archMa.busqueda(canal2, b);
            System.out.println(a + "   " + b);
            archMa.leerReg(canal2, n, ma);
            System.out.println("ma: " + ma.cve + " y n es: " + n);
            in.nroCtrl = a;
            in.cve = b;
            System.out.println("reg: " + reg);
            grabarReg(canal, reg, in);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

    public void leerReg(RandomAccessFile canal, int nReg, Inscripcion x) {
        try {
            System.out.println(nReg);
            canal.seek(nReg * tr);
            x.nroCtrl = canal.readUTF();
            x.cve = canal.readUTF();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void grabarReg(RandomAccessFile canal, int nReg, Inscripcion x) {
        try {
            canal.seek(nReg * tr);
            canal.writeUTF(String.format("%8s", x.nroCtrl.length() > 8 ? x.nroCtrl.substring(0, 8) : x.nroCtrl));
            canal.writeUTF(String.format("%4s", x.cve.length() > 4 ? x.cve.substring(0, 4) : x.cve));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public int busqueda(RandomAccessFile canal, String bus) {
        return 0;
    }

        public void reporte(RandomAccessFile canal) {
        String fichero, aux;
        PrintWriter salida = null;
        try {
            archAl = new ArchivoAlumnos();
            archMa = new ArchivoMaterias();
            fichero = "reporteInscripciones.rep.txt";
            salida = new PrintWriter(new FileWriter(fichero, true));
            salida.println("REPORTE DE INSCRIPCIONES");
            salida.println("__________________________________________________________________________");
            salida.println(String.format("%11s \t| %-28s", "No. Control","Clave de la Materia"));
            salida.println("__________________________________________________________________________");
            for (int i = 0; i < (int) canal.length() / tr; i++) {
                leerReg(canal, i, in);
                salida.println(String.format("%8s \t| %4s", in.nroCtrl, in.cve));
            }
            salida.println("\n\n");
            salida.println("NULL NULL NULL SAHUR");
            salida.close();
        } catch (IOException e) {
            System.out.println(" No se abrio bien el fichero \n" + e.toString());
        }
    }

    public int modificaciones(RandomAccessFile canal, int ID, String A, String B, byte C) {
        return 1;
    }

    public void ordenar(RandomAccessFile canal) {
        try {
            Inscripcion in2 = new Inscripcion();
            for (int pas = 1; pas < (int) (canal.length()) / tr; pas++) {
                for (int co = 1; co <= ((int) (canal.length() / tr) - pas); co++) {
                    leerReg(canal, co - 1, in);
                    leerReg(canal, co, in2);
                    if (in.nroCtrl.compareTo(in2.nroCtrl) > 0) {
                        grabarReg(canal, co - 1, in2);
                        grabarReg(canal, co, in);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
    }
    
    public void reporteGen(RandomAccessFile canal){
        String fichero, aux;
        PrintWriter salida = null;
        try {
            archAl = new ArchivoAlumnos();
            archMa = new ArchivoMaterias();
            fichero = "reporte.rep.txt";
            salida = new PrintWriter(new FileWriter(fichero, true));
            String res = "";
            salida.println("REPORTE DE ALUMNOS");
            salida.println("__________________________________________________________________________");
            salida.println(String.format("%11s \t| %-40s \t|%9s", "No. Control", "Nombre", "Semestre"));
            salida.println("__________________________________________________________________________");
            for (int i = 0; i < (int) canal1.length() / archAl.tr; i++) {
                Alumno al = new Alumno();
                archAl.leerReg(canal1, i, al);
                salida.println(String.format("%8s \t| %-40s \t|%9d", al.nroCtrl, al.nom, al.sem));
            }
            salida.println("\n\n");
            salida.println("REPORTE DE MATERIAS");
            salida.println("__________________________________________________________________________");
            salida.println(String.format("%5s \t| %-28s \t|%5s", "Clave","Nombre","Créditos"));
            salida.println("__________________________________________________________________________");            
            for (int i = 0; i < (int) canal2.length() / archMa.tr; i++) {
                Materia ma = new Materia();
                archMa.leerReg(canal2, i, ma);
                salida.println(String.format("%5s \t| %-28s \t|%5d", ma.cve, ma.nom, ma.cred));
            }
            salida.println("\n\n");
            salida.println("REPORTE DE INSCRIPCIONES");
            salida.println("__________________________________________________________________________");
            salida.println(String.format("%11s \t| %-28s", "No. Control","Clave de la Materia"));
            salida.println("__________________________________________________________________________");
            for (int i = 0; i < (int) canal.length() / tr; i++) {
                leerReg(canal, i, in);
                salida.println(String.format("%8s \t| %4s", in.nroCtrl, in.cve));
            }
            salida.println("\n\n");
            salida.println("NULL NULL NULL SAHUR");
            salida.close();
            } catch (IOException e) {
            System.out.println(" No se abrio bien el fichero \n" + e.toString());
            }
    }
}
