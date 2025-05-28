package inicioarchivos;
import java.io.*;
import java.util.Scanner;
public class ArchivoAlumnos extends Archivos
{
    //   ATRIBUTOS DEL ARCHIVO DE ALUMNOS  
    Alumno al=new Alumno();
    Scanner sc=new Scanner (System.in);
    final int tr=53;   //TAMAÑO DEL REGISTRO (VERIFICAR LOS CAMPOS Y BYTES QUE OCUPARÁ CADA REGISTR4O
    @Override
    public void inicioMen()
    {
         canal=null;
         try
         {
             canal=new RandomAccessFile("alumnos.dat","rw");
             menu();
             canal.close();
         }
         catch(IOException e)
         {
              System.out.println("Error en el archivo");
         }
    }   
    @Override
    public void altas(RandomAccessFile canal)
    {
        System.out.println("Seguimiento de altas");        
         try{
            al.capturar();
            int reg=(int)canal.length()/tr;
            grabarReg(canal,reg,al);
         }
         catch(IOException e)
         {
             System.out.println("Error en el archivo");
         }    
    }
    
    @Override
    public void reporte(RandomAccessFile canal)
    {
        try
        {
            int n=(int)canal.length()/tr;
            for(int i=0; i<n;i++)
            {
                 leerReg(canal,i,al);
                 System.out.println("Alumno "+al.mostrar());
                 
            }
        }
        catch(IOException e)
        {
            System.out.println("Error en el archivo");
        }
    }
  
    @Override
    public int busqueda(RandomAccessFile canal, String bus)
 {
        if (bus.equals(""))
        {
            System.out.println("Ingresa busqueda");
            bus=sc.nextLine();
        }
                
        int li=0;
        int pm;
        try
        {
            int ls=(int)(canal.length()/tr)-1;
             do
             {
                pm=(li+ls)/2;
                leerReg(canal,pm,al);
                if(al.nroCtrl.compareTo(bus)<0)
                    li=pm+1;
                else
                    ls=pm-1;
             }while(!bus.equals(al.nroCtrl) && li<=ls);
            if(bus.equals(al.nroCtrl))
            {
                System.out.println("Si encontrado"+al.mostrar());
                return pm;
            }
            else
            {
                System.out.println(bus+" no existe");
                return -1;
            }
        }
        catch(IOException e)
        {
            System.out.println("Error en el archivo");
            return 0;
        }
    }
    
    @Override
    public int modificaciones(RandomAccessFile canal)
    {
        System.out.println("Ingrese el numero de control del alumno a modificar");
        String rem=sc.nextLine();
        int n=busqueda(canal,rem);
        if (n==-1)
        {return -1;}
        al.capturar();
        grabarReg(canal,n,al);
        return 0;
    }
   
    @Override
    public void ordenar(RandomAccessFile canal)
    {
        try
        {
            Alumno al2=new Alumno();
            for(int pas=1;pas< (int)(canal.length())/tr;pas++)
            {
               for(int co=1;co<=((int)(canal.length()/tr)-pas);co++)
               {
                   System.out.println("Sigue "+co);
                  leerReg(canal,co-1,al);
                  leerReg(canal,co,al2);
                  if(al.nroCtrl.compareTo(al2.nroCtrl)>0)
                  {
                      // Se graban en los registros cambiados
                      grabarReg(canal,co-1,al2);
                      grabarReg(canal,co,al);
                  }
               }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error en el archivo");
        }
    }
    public void leerReg(RandomAccessFile canal,int nReg, Alumno x)
    {
        try
        {
            canal.seek(nReg*tr);
            x.nroCtrl=canal.readUTF();
            x.nom=canal.readUTF();
            x.sem=canal.readByte();
        }
        catch(IOException e)
        {
            System.out.println("Error en el archivo");
        }
    }
    public void grabarReg(RandomAccessFile canal,int nReg, Alumno x)
    {
        try
        {
            canal.seek(nReg*tr);
            canal.writeUTF(String.format("%-8s",x.nroCtrl));
            canal.writeUTF(String.format("%-40s",x.nom));
            canal.write(x.sem);
        }
        catch(IOException e)
        {
            System.out.println("|Error en el archivo");
        }
    }
}
