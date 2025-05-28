package inicioarchivos;
import java.io.*;
import java.util.Scanner;
abstract class Archivos
{
    RandomAccessFile canal=null;
     public void menu()
     {
         Scanner tec=new Scanner(System.in);
        int opc=0;
        do
        {
            System.out.println("Menú de opciones");
            System.out.println("1) Altas");
            System.out.println("2) Modificaciones");
            System.out.println("3) Reporte");
            System.out.println("4) Ordena");
            System.out.println("5) Consulta");
            System.out.println("6) Salida");
            System.out.println("Tecle la opcion");
            opc=tec.nextInt();
            switch(opc)
            {
                case 1: altas(canal);
                        break;
                case 2: modificaciones(canal);
                        break;
                case 3: reporte(canal);
                        break;
                case 4: ordenar(canal);
                        break;
                case 5: busqueda(canal,"");
                        break;
            }
          
            
        }while(opc!=6);    
     }
     public abstract void altas(RandomAccessFile canal);
     public abstract int busqueda(RandomAccessFile canal,String s);
     public abstract void reporte(RandomAccessFile canal);
     public abstract int modificaciones(RandomAccessFile canal);
     public abstract void ordenar(RandomAccessFile canal);
     public abstract void inicioMen();
}

