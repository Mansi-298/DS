package Token_ring_mutual_excllusion_algo;

import java.util.*;

public class TokenRing {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        boolean[] request = new boolean[n];

        // Input requests
        for (int i = 0; i < n; i++) {
            System.out.print("Does Process " + i + " want to enter CS? (1/0): ");
            request[i] = sc.nextInt() == 1;
        }

        int token = 0;

        System.out.println("\n--- Token Ring Execution ---\n");

        // One complete cycle
        for (int i = 0; i < n; i++) {

            System.out.println("Token at Process " + token);

            if (request[token]) {

                System.out.println("Process " + token + " ENTERING Critical Section...");

                // Simulate execution
                Thread.sleep(2000);

                System.out.println("Process " + token + " EXITING Critical Section\n");

                request[token] = false;

            } else {

                System.out.println("Process " + token + " does not need CS\n");
            }

            // Pass token
            token = (token + 1) % n;

            Thread.sleep(1000);
        }

        System.out.println("\n--- End of Execution ---");

        sc.close();
    }
}