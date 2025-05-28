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
