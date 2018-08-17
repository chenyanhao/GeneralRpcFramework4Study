package framework.remote;

import consts.MyConstans;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

public class ClientRemoter {

    public static final ClientRemoter CLIENT = new ClientRemoter();

    public byte[] getDataRemote(byte[] requestData) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(MyConstans.LOCALHOST, MyConstans.RPC_SERVICE_PORT));
            socket.getOutputStream().write(requestData);
            socket.getOutputStream().flush();

            byte[] buffer = new byte[10 * MyConstans.KB];
            int len = socket.getInputStream().read(buffer);

            return Arrays.copyOfRange(buffer, 0, len);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
