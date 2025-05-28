package inicioarchivos;
import java.util.Scanner;
class Alumno
{
    String nroCtrl,nom;
    byte sem;
    void capturar(String nroCtrl, String nom, byte sem)
    {
        boolean flag=true;
        do
        {
            try
            {
                this.nroCtrl=nroCtrl;
                this.nom=nom;
                this.sem=sem;
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
