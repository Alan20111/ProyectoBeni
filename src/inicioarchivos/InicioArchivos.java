package inicioarchivos;
import java.util.*;
public class InicioArchivos
{
    public static  Archivos obArch;
    public static void main(String[] args)
    {
        obArch=new ArchivoAlumnos();
        obArch.inicioMen();
        obArch=new ArchivoMaterias();
        obArch.inicioMen();
        obArch=new ArchivoInscripciones();
        obArch.inicioMen();
        
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

