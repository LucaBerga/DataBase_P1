import java.util.Scanner;

public class Main {
    
    public static void main(String[] args){

        try{

            DataBase db = new DataBase("localhost", 3306, "database_test");
            db.open();
            
            System.out.println("Quale operazione?\n1)Login\n2)Registrazione");
            int opz = new Scanner(System.in).nextInt();
            switch(opz) {
            case 1:
                System.out.println("Inserisci username:");
                String username = new Scanner(System.in).nextLine();
                System.out.println("Inserisci password:");
                String password = new Scanner(System.in).nextLine();

                //login
                if(db.login(username, password)){
                    System.out.println("Benvenuto " + db.getName(username));
                }else{
                    System.out.println("Errore");
                }

                break;
        
            case 2:
                System.out.println("Inserisci il nome:");
                String name = new Scanner(System.in).nextLine();
                System.out.println("Inserisci il cognome:");
                String surname = new Scanner(System.in).nextLine();
                System.out.println("Inserisci username:");
                String user = new Scanner(System.in).nextLine();
                System.out.println("Inserisci la password:");
                String pass = new Scanner(System.in).nextLine();

                //registrazione
                db.register(name, surname, user, pass);
                System.out.println("Sei stato registrato con successo");

                break;

            default:
                System.out.println("Operazione non consentita");
                break;
            }
            System.exit(0);
            db.close();

        }catch(Exception e){
            System.out.println(e);
        }

    }
}
