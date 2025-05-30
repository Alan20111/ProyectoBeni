package inicioarchivos;

import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;

public class ArchivoMaterias extends Archivos {

    Materia ma = new Materia();
    Scanner sc = new Scanner(System.in);
    final int tr = 37;

    @Override
    public void inicioMen() {
        canal = null;
        try {
            canal = new RandomAccessFile("materias.dat", "rw");
        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
    }

    @Override
    public boolean altas(RandomAccessFile canal, String a, String b, byte c) {
        try {
            ma.capturar(a, b, c);
            int reg = (int) canal.length() / tr;
            grabarReg(canal, reg, ma);
            JOptionPane.showMessageDialog(null, "Materia Agregada", "Notificación", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (IOException e) {
            System.out.println("Error en el archivo");
            return false;
        }
    }

    @Override
    public void reporte(RandomAccessFile canal) {
        try {
            int n = (int) canal.length() / tr;
            for (int i = 0; i < n; i++) {
                leerReg(canal, i, ma);
                System.out.println("Materia " + ma.mostrar());

            }
        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
    }

    @Override
    public int busqueda(RandomAccessFile canal, String bus) {
        ordenar(canal);
        int li = 0;
        int pm;
        try {
            int ls = (int) (canal.length() / tr) - 1;
            do {
                pm = (li + ls) / 2;
                leerReg(canal, pm, ma);
                if (ma.cve.compareTo(bus) < 0) {
                    li = pm + 1;
                } else {
                    ls = pm - 1;
                }
            } while (!bus.equals(ma.cve) && li <= ls);
            if (bus.equals(ma.cve)) {
                System.out.println("Si encontrado" + ma.mostrar());
                return pm;
            } else {
                System.out.println(bus + " no existe");
                return -1;
            }
        } catch (IOException e) {
            System.out.println("Error en el archivo");
            return 0;
        }
    }
    
    
    @Override
    public int modificaciones(RandomAccessFile canal, int ID, String Clave, String Nombre, byte Cred) {
        ordenar(canal);
        ma.capturar(Clave, Nombre, Cred);
        grabarReg(canal, ID, ma);
        return 0;
    }

    @Override
    public void ordenar(RandomAccessFile canal) {
        try {
            Materia ma2 = new Materia();
            for (int pas = 1; pas < (int) (canal.length()) / tr; pas++) {
                for (int co = 1; co <= ((int) (canal.length() / tr) - pas); co++) {
                    leerReg(canal, co - 1, ma);
                    leerReg(canal, co, ma2);
                    if (ma.cve.compareTo(ma2.cve) > 0) {
                        grabarReg(canal, co - 1, ma2);
                        grabarReg(canal, co, ma);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
    }

    public void leerReg(RandomAccessFile canal, int nReg, Materia x) {
        try {
            canal.seek(nReg * tr);
            x.cve = canal.readUTF();
            x.nom = canal.readUTF();
            x.cred = canal.readByte();
        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
    }

    public void grabarReg(RandomAccessFile canal, int nReg, Materia x) {
        try {
            canal.seek(nReg * tr);
            canal.writeUTF(String.format("%4s", x.cve.length() > 4 ? x.cve.substring(0, 4) : x.cve));
            canal.writeUTF(String.format("%-28s", x.nom.length() > 28 ? x.nom.substring(0, 28) : x.nom));
            canal.writeByte(x.cred);
        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
    }
}
