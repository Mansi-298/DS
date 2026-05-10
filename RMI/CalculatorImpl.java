package RMI_Assignment;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    protected CalculatorImpl() throws RemoteException {
        super();
    }

    public double add(double x, double y) throws RemoteException {
        return x + y;
    }

    public double subtract(double x, double y) throws RemoteException {
        return x - y;
    }

    public double multiply(double x, double y) throws RemoteException {
        return x * y;
    }

    public double divide(double x, double y) throws RemoteException {
        if (y == 0) {
            return 0;
        }
        return x / y;
    }
}
