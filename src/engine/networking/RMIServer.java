package engine.networking;

import javax.naming.NamingException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * <h1>RMIServer</h1>
 * This class represents an RMI registry server.
 * <p>
 * From this class, you can create a registry for clients to connect to.
 * </p>
 *
 * @author  Raitoning
 * @version 2018.12.05
 * @since   2018.12.05
 */

@Deprecated
public class RMIServer {

    public RMIServer(String registryName, RMIRegistry rmiRegistry) throws RemoteException,
            NamingException, AlreadyBoundException {

        System.out.println("Constructing server implementation...");

        System.out.println("Binding server implementation to registry...");
        Registry registry = LocateRegistry.createRegistry(7777);

        registry.bind(registryName, rmiRegistry);

        System.out.println("Waiting for invocations from clients...");
    }
}
