import model.Client;
import model.EntityClient;
import model.IndividualClient;
import model.PersonClient;

public class Main  {
    public static void main(String[] args) {
        Client[] clients = new Client[3];
        clients[0] = new PersonClient(0);
        clients[1] = new EntityClient(0);
        clients[2] = new IndividualClient(0);

        System.out.println("Положить 1000:");
        for (int i = 0; i < clients.length; i++) {
            clients[i].deposit(1000);
            System.out.println(clients[i].getBalance());
        }


        System.out.println("Снять 500");
        for (int i = 0; i < clients.length; i++) {
            clients[i].withdraw(500);
            System.out.println(clients[i].getBalance());
        }
    }
}
