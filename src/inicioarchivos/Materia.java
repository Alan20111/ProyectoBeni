package inicioarchivos;
import java.util.Scanner;
public class Materia
{
    String cve,nom;
    byte cred;
    void capturar(String cve, String nom, byte cred)
    {
        boolean flag=true;
        do
        {
            try
            {
                this.cve = cve;
                this.nom = nom;
                this.cred = cred;
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
        return cve+"     "+nom+"     "+cred+"\n";
    }
}
