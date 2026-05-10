import CalcApp.*;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class CalculatorServer {

    public static void main(String args[]) {

        try {

            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(
                    orb.resolve_initial_references("RootPOA"));

            rootpoa.the_POAManager().activate();

            CalculatorImpl calcImpl = new CalculatorImpl();

            org.omg.CORBA.Object ref =
                    rootpoa.servant_to_reference(calcImpl);

            Calculator href =
                    CalculatorHelper.narrow(ref);

            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(objRef);

            String name = "Calculator";

            NameComponent path[] =
                    ncRef.to_name(name);

            ncRef.rebind(path, href);

            System.out.println("Calculator Server Started...");

            orb.run();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}