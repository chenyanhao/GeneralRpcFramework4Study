package remote;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

public class ClientRemoter {

    public static final ClientRemoter client = new ClientRemoter();

    private static final String LOCALHOST = "127.0.0.1";

    private static int KB = 1024;

    public byte[] getDataRemote(byte[] requestData) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(LOCALHOST, 8888));
            socket.getOutputStream().write(requestData);
            socket.getOutputStream().flush();

            byte[] buffer = new byte[10 * KB];
            int len = socket.getInputStream().read(buffer);

            return Arrays.copyOfRange(buffer, 0, len);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
