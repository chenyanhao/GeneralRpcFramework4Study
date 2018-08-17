package framework.serverclient;

import framework.encodedecode.ServiceProtocol;
import framework.remote.ClientRemoter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceProxyClient {

    public static <T> T getInstance(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz}, new ServiceProxy((clazz)));
    }

    private static class ServiceProxy implements InvocationHandler {

        private Class clazz;
        public ServiceProxy(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ServiceProtocol.ProtocolModel model = new ServiceProtocol.ProtocolModel();
            model.setClazz(clazz.getName());
            model.setMethod(method.getName());
            model.setArgs(args);

            String[] argType = new String[method.getParameterTypes().length];
            for (int i = 0; i < argType.length; ++i) {
                argType[i] = method.getParameterTypes()[i].getName();
            }
            model.setArgTypes(argType);

            byte[] request = ServiceProtocol.PROTOCOL.encode(model);
            byte[] response = ClientRemoter.CLIENT.getDataRemote(request);
            return ServiceProtocol.PROTOCOL.decode(response, method.getReturnType());
        }
    }

}
