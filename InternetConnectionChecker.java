

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetConnectionChecker {
    public static boolean isConnected() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(3000); // Vérifier la connexion dans les 3 secondes
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}