package framework.processor;

import framework.encodedecode.ServiceProtocol;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceProcessor {

    public static final ServiceProcessor PROCESSOR = new ServiceProcessor();

    private static final ConcurrentHashMap<String, Object> PROCESSOR_INSTANCE_MAP = new ConcurrentHashMap<>();

    public boolean publish(Class<?> clazz, Object obj) {
        return PROCESSOR_INSTANCE_MAP.putIfAbsent(clazz.getName(), obj) != null;
    }

    public Object process(ServiceProtocol.ProtocolModel model) {

        Object obj = PROCESSOR_INSTANCE_MAP.get(model.getClazz());
        if (obj == null) {
            return null;
        }

        try {
            Class<?> clazz = Class.forName(model.getClazz());

            Class[] types = new Class[model.getArgTypes().length];
            for (int i = 0; i < types.length; ++i) {
                types[i] = Class.forName(model.getArgTypes()[i]);
            }

            Method method = clazz.getMethod(model.getMethod(), types);

            return method.invoke(obj, model.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
