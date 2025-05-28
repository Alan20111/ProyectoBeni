package inicioarchivos;
import java.util.Scanner;
public class Materia
{
    String cve,nom;
    byte cred;
    void capturar()
    {
        boolean flag=true;
        do
        {
        try
        {
        Scanner sc=new Scanner(System.in);
        System.out.println("Tecle la clave de la materia");
        cve=sc.nextLine();
        cve=String.format("%4s", cve);
        System.out.println("Tecle el nombre de la materia");
        nom=sc.nextLine();
        nom=String.format("%28s",nom);
        System.out.println("Tecle el numero de creditos");
        cred=sc.nextByte();
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
