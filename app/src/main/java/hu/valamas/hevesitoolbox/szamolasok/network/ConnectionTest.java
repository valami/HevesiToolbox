package hu.valamas.hevesitoolbox.szamolasok.network;

import java.net.InetAddress;

public class ConnectionTest {
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");

            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
