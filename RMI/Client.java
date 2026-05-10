package RMI_Assignment;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            // Connect to registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Lookup remote object
            Calculator calc = (Calculator) registry.lookup("CalculatorService");

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Add\n2. Subtract\n3. Multiply\n4. Divide\n5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                if (choice == 5) break;

                System.out.print("Enter first number: ");
                double x = sc.nextDouble();

                System.out.print("Enter second number: ");
                double y = sc.nextDouble();

                switch (choice) {
                    case 1:
                        System.out.println("Result: " + calc.add(x, y));
                        break;
                    case 2:
                        System.out.println("Result: " + calc.subtract(x, y));
                        break;
                    case 3:
                        System.out.println("Result: " + calc.multiply(x, y));
                        break;
                    case 4:
                        System.out.println("Result: " + calc.divide(x, y));
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Client error: " + e);
        }
    }
}
