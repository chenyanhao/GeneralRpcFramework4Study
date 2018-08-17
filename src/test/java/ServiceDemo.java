import framework.processor.ServiceProcessor;
import framework.remote.ServerRemoter;

public class ServiceDemo {
    public static void main(String[] args) throws Exception {
        ServiceProcessor.PROCESSOR.publish(RpcService.class, new RpcServiceImpl());
        ServerRemoter remoter = new ServerRemoter();
        remoter.startServer(8888);
    }

}