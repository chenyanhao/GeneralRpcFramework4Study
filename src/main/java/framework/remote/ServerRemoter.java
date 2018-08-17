package framework.remote;


import consts.MyConstans;
import framework.encodedecode.ServiceProtocol;
import framework.processor.ServiceProcessor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerRemoter {

    private static final ExecutorService EXECUTOR =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void startServer(int port) throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        System.out.println("--------- start server ---------");
        while (true) {
            Socket socket = server.accept();
            EXECUTOR.execute(new MyRunnable(socket));
        }
    }

    private class MyRunnable implements Runnable {

        private Socket socket;

        public MyRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream is = socket.getInputStream(); OutputStream os = socket.getOutputStream()) {
                byte[] data = new byte[MyConstans.KB * 10];
                int len = is.read(data);
                ServiceProtocol.ProtocolModel model =
                        ServiceProtocol.PROTOCOL.decode(Arrays.copyOfRange(data, 0, len), ServiceProtocol.ProtocolModel.class);
                Object object = ServiceProcessor.PROCESSOR.process(model);
                os.write(ServiceProtocol.PROTOCOL.encode(object));
                os.flush();
            } catch (Exception e) {
                // 略
            } finally {
                // close socket
            }
        }
    }

}
