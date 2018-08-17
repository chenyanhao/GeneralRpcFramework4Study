

public class RpcServiceImpl implements RpcService {
    @Override
    public String hello(String name) {
        return String.format("Hello, %s", name);
    }
}