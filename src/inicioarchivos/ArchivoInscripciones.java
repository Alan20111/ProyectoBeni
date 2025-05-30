package inicioarchivos;
import java.util.Scanner;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class ArchivoInscripciones extends Archivos {
    Alumno al;
    Materia ma;
    Inscripcion in=new Inscripcion();
    ArchivoAlumnos archAl;
    ArchivoMaterias archMa;
    final int tr = 16;
    private Scanner sc=new Scanner(System.in);

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

    public void menu(String noCtrl, String nom, byte sem) {
        byte opc = 0;
        do {
            System.out.println("Menú de opciones");
            System.out.println("1) Inscribir a una clase");
            System.out.println("2) Ver materias inscritas");
            System.out.println("3) Salida");
            System.out.println("Tecle la opcion");
            opc = sc.nextByte();
            switch (opc) {
                case 1:
                    altas(canal, noCtrl, nom, sem);
                    break;
                case 2:
                    reporte(canal);
                    break;
            }
        } while (opc != 3);
    }

    public boolean altas(RandomAccessFile canal, String a, String b, byte c) {
        try {
            int n;
            char opc = 0, sel = 0;
            String bus;
            archAl = new ArchivoAlumnos();
            do {
                System.out.println("Número de control del alumno a inscribir");
                bus = sc.nextLine();
                n = archAl.busqueda(canal1, bus);
                if (n == -1) {
                    System.out.println("El alumno no existe, ¿Desea volver a intentar? (y/n)");
                    opc = sc.next().charAt(0);
                }
            } while (n == -1 && opc == 'y');
            if (opc == 'n') {
                return false;
            }
            al = new Alumno();
            archAl.leerReg(canal1, n, al);
            archMa = new ArchivoMaterias();
            do {
                opc='n';
                System.out.println("Escriba la clave de la materia que desea inscribir");
                bus = sc.next();
                n = archMa.busqueda(canal2, bus);
                if (n == -1) {
                    System.out.println("La materia no existe, ¿Desea volver a intentar? (y/n)");
                    opc = sc.next().charAt(0);
                } else {
                    int  reg = (int) canal.length() / tr;
                    ma = new Materia();
                    archMa.leerReg(canal2, n, ma);
                    in = new Inscripcion();
                    in.nroCtrl = al.nroCtrl;
                    in.cve = ma.cve;
                    grabarReg(canal, reg, in);
                    System.out.println("¿Desea registrar otra materia al mismo alumno? (y/n)");
                    sel = sc.next().charAt(0);
                }
            } while (opc == 'y' || sel == 'y');
            return true;
        }
        catch (IOException e) {
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
            canal.writeUTF(String.format("%8s", x.nroCtrl));
            canal.writeUTF(String.format("%4s", x.cve));
        } catch (IOException e) {
            System.out.println("|Error en el archivo");
        }
    }

    public int busqueda(RandomAccessFile canal, String s) {
        return 0;
    }
    public void reporte(RandomAccessFile canal)
    {
            String fichero,aux;
            PrintWriter salida=null;
            try
            {
               archAl=new ArchivoAlumnos();
               archMa=new ArchivoMaterias();
               fichero="C:\\Users\\monro\\Desktop\\ITCelaya\\Semestre 2\\Programacion Orientada a Objetos\\reporte.rep.txt";
               salida=new PrintWriter(new FileWriter(fichero,true));
               String res="";
               salida.println("\t\tReporte alumnos");
               salida.println("");
               salida.println("Número de control\t\t\tNombre\t\t\tSemestre");         
               for (int i=0; i<(int)canal1.length()/archAl.tr;i++)
               {
                   Alumno al=new Alumno();
                   archAl.leerReg(canal1, i, al);
                   salida.println(String.format("%8s   %-40s   %5d",al.nroCtrl,al.nom,al.sem));
               }
               salida.println("\n\n");
               salida.println("\t\tReporte alumnos");
               salida.println("");
               salida.println("Clave de la materia\t\t\tNombre\t\t\tCréditos");            
               for (int i=0; i<(int)canal2.length()/archMa.tr;i++)
               {
                   Materia ma=new Materia();
                   archMa.leerReg(canal2, i, ma);
                   salida.println(String.format("%4s   %-28s   %5d",ma.cve,ma.nom,ma.cred));
               } 
               salida.println("\n\n");
               salida.println("\t\tReporte inscripciones");
               salida.println("");
               salida.println("Número de control\t\t\tMAteria");            
               for (int i=0; i<(int)canal.length()/tr;i++)
               {
                   leerReg(canal, i, in);
                   salida.println(String.format("%8s   %4s",in.nroCtrl,in.cve));
               }            
                salida.close();
            }
            catch (IOException e)
            {
                System.out.println(" No se abrio bien el fichero \n"+e.toString());
            }
    }

    public int modificaciones(RandomAccessFile canal,int ID, String A, String B, byte C) {
        return 1;
    }

    public void ordenar(RandomAccessFile canal) {
        try {
            Inscripcion in2 = new Inscripcion();
            System.out.println("Seleccione tipo de ordenamiento\n1) Numero de control\n2) Clave de materia");
            byte opc=sc.nextByte();
            if (opc==1)
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
            else
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
        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }
    }
}