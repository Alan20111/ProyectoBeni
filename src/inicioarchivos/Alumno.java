package inicioarchivos;
import java.util.Scanner;
class Alumno
{
    String nroCtrl,nom;
    byte sem;
    void capturar()
    {
        boolean flag=true;
        do
        {
            try
            {
                Scanner tec=new Scanner(System.in);
                System.out.println("Tecle numero de control ");
                nroCtrl=tec.nextLine();
                nroCtrl=String.format("%8s",nroCtrl);
                System.out.println("Tecle nombre ");
                nom=tec.nextLine();
                nom=String.format("%-40s",nom);
                System.out.println("Tecle semestre ");
                sem=tec.nextByte();
                flag=false;
            }
            catch(java.util.InputMismatchException e)
            {
                System.out.println("Dato no válido");
            }
        }while(flag);
    }
    String mostrar()
    {
        return nroCtrl+"     "+nom+"     "+sem+"\n";
    }
}
