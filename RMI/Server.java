package RMI_Assignment;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            // Create object
            CalculatorImpl obj = new CalculatorImpl();

            // Start registry (on port 1099)
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind object
            registry.rebind("CalculatorService", obj);

            System.out.println("Server is ready...");

        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }
}
