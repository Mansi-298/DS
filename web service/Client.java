package web_service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // User input
        System.out.print("Enter first number: ");
        int a = sc.nextInt();

        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        // Dynamic URL
        String address =
                "http://localhost:8000/add?a=" + a + "&b=" + b;

        URL url = new URL(address);

        // Read server response
        BufferedReader br = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String line;

        while ((line = br.readLine()) != null) {

            System.out.println(line);
        }

        br.close();

        sc.close();
    }
}