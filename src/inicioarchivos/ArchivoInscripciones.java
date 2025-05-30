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

    Alumno al;
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
            System.out.println("Error en el archivo");
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
            int reg = (int) canal.length() / tr;
            do {
                n = archAl.busqueda(canal1, a);
                if (n == -1) {
                    System.out.println("El alumno no existe, ¿Desea volver a intentar? (y/n)");
                    opc = sc.next().charAt(0);
                }
            } while (n == -1 && opc == 'y');
            if (opc == 'n') {
                return false;
            }
            archAl.leerReg(canal1, n, al);
            n = archMa.busqueda(canal2, b);
            archMa.leerReg(canal2, n, ma);
            System.out.println("ma: " + ma.cve + " y n es: " + n);
            in = new Inscripcion();
            in.nroCtrl = al.nroCtrl;
            in.cve = ma.cve;
            grabarReg(canal, reg, in);
            return true;
        } catch (IOException e) {
            System.out.println("| Error en el archivo");
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
            System.out.println("Error en el archivo");
        }
    }

    public void grabarReg(RandomAccessFile canal, int nReg, Inscripcion x) {
        try {
            canal.seek(nReg * tr);
            canal.writeUTF(String.format("%8s", x.nroCtrl.length() > 8 ? x.nroCtrl.substring(0, 8) : x.nroCtrl));
            canal.writeUTF(String.format("%4s", x.cve.length() > 4 ? x.cve.substring(0, 4) : x.cve));
        } catch (IOException e) {
            System.out.println("|Error en el archivo");
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
            fichero = "C:\\Users\\monro\\Desktop\\ITCelaya\\Semestre 2\\Programacion Orientada a Objetos\\reporte.rep.txt";
            salida = new PrintWriter(new FileWriter(fichero, true));
            String res = "";
            salida.println("\t\tReporte alumnos");
            salida.println("");
            salida.println("Número de control\t\t\tNombre\t\t\tSemestre");
            for (int i = 0; i < (int) canal1.length() / archAl.tr; i++) {
                Alumno al = new Alumno();
                archAl.leerReg(canal1, i, al);
                salida.println(String.format("%8s   %-40s   %5d", al.nroCtrl, al.nom, al.sem));
            }
            salida.println("\n\n");
            salida.println("\t\tReporte alumnos");
            salida.println("");
            salida.println("Clave de la materia\t\t\tNombre\t\t\tCréditos");
            for (int i = 0; i < (int) canal2.length() / archMa.tr; i++) {
                Materia ma = new Materia();
                archMa.leerReg(canal2, i, ma);
                salida.println(String.format("%4s   %-28s   %5d", ma.cve, ma.nom, ma.cred));
            }
            salida.println("\n\n");
            salida.println("\t\tReporte inscripciones");
            salida.println("");
            salida.println("Número de control\t\t\tMAteria");
            for (int i = 0; i < (int) canal.length() / tr; i++) {
                leerReg(canal, i, in);
                salida.println(String.format("%8s   %4s", in.nroCtrl, in.cve));
            }
            salida.close();
        } catch (IOException e) {
            System.out.println(" No se abrio bien el fichero \n" + e.toString());
        }
    }

    public int modificaciones(RandomAccessFile canal, int ID, String A, String B, byte C) {
        return 1;
    }

    public void ordenar(RandomAccessFile canal) {
        // Crear los botones de opción
        JRadioButton opcion1 = new JRadioButton("No. Control");
        JRadioButton opcion2 = new JRadioButton("Clave Materia");

        // Agrupar los botones para que solo se pueda seleccionar uno
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opcion1);
        grupo.add(opcion2);

        // Crear un panel y agregar los botones
        JPanel panel = new JPanel();
        panel.add(opcion1);
        panel.add(opcion2);

        // Mostrar el panel en un cuadro de diálogo
        int resultado = JOptionPane.showConfirmDialog(null, panel, "Seleccione una opción", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        // Procesar la selección
        try {
            Inscripcion in2 = new Inscripcion();
            if (resultado == JOptionPane.OK_OPTION) {
                if (opcion1.isSelected()) {
                    for (int pas = 1; pas < (int) (canal.length()) / tr; pas++) {
                        for (int co = 1; co <= ((int) (canal.length() / tr) - pas); co++) {
                            leerReg(canal, co - 1, in);
                            leerReg(canal, co, in2);
                            if (in.nroCtrl.compareTo(in2.nroCtrl) > 0) {
                                grabarReg(canal, co - 1, in2);
                                grabarReg(canal, co, in2);
                            }
                        }
                    }
                } else if (opcion2.isSelected()) {
                    for (int pas = 1; pas < (int) (canal.length()) / tr; pas++) {
                        for (int co = 1; co <= ((int) (canal.length() / tr) - pas); co++) {
                            leerReg(canal, co - 1, in);
                            leerReg(canal, co, in2);
                            if (in.cve.compareTo(in2.cve) > 0) {
                                grabarReg(canal, co - 1, in2);
                                grabarReg(canal, co, in2);
                            }
                        }
                    }
                } else {
                    System.out.println("No seleccionaste ninguna opción.");
                }
            } else {
                System.out.println("Cancelaste la selección.");
            }
        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
    }
}
