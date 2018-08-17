import framework.serverclient.ServiceProxyClient;

public class ClientDemo {
    public static void main(String[] args) {
        System.out.println("------------- start invoke ----------");

        for (int i = 0; i < 5; ++i) {
            RpcService rpcService = ServiceProxyClient.getInstance(RpcService.class);
            System.out.println(rpcService.hello(String.format("xixi haha - %s", i)));
        }

        System.out.println("------------- end invoke ----------");
    }
}