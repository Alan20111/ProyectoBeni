package inicioarchivos;

import java.util.*;

public class InicioArchivos {

    public static Archivos obAlumnos = new ArchivoAlumnos();
    public static Archivos obMaterias = new ArchivoMaterias();
    public static Archivos obInscripciones = new ArchivoInscripciones();

    public static void main(String[] args) {
        obAlumnos.inicioMen();
        obMaterias.inicioMen();
        obInscripciones.inicioMen();

        Ventana ven = new Ventana();
        ven.setVisible(true);
        /*
        Scanner sc=new Scanner(System.in);
        
        byte opc=0;
        do
        {
            System.out.println("Tipo de archivo");
            System.out.println("1) Alumnos");
            System.out.println("2) Materias");
            System.out.println("3) Inscripciones");
            System.out.println("4) Salir");
            opc=sc.nextByte();
            switch (opc)
            {
                case 1: obArch=new ArchivoAlumnos();
                    obArch.inicioMen();
                    break;
                case 2: obArch=new ArchivoMaterias();
                    obArch.inicioMen();
                    break;
                case 3: obArch=new ArchivoInscripciones();
                    obArch.inicioMen();
                    break;
            }
            System.out.println();
        }while(opc!=4);
         */
    }
}
