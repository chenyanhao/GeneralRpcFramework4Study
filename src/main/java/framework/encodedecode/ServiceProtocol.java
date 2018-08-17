package framework.encodedecode;

import utils.JsonUtils;

public class ServiceProtocol {

    public static final ServiceProtocol PROTOCOL = new ServiceProtocol();

    public byte[] encode(Object o) {
        return JsonUtils.encode(o).getBytes();
    }

    public <T> T decode(byte[] data, Class<T> clazz) {
        return JsonUtils.decode(new String(data), clazz);
    }

    public static class ProtocolModel {
        private String      clazz;
        private String      method;
        private String[]    argTypes;
        private Object[]    args;

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String[] getArgTypes() {
            return argTypes;
        }

        public void setArgTypes(String[] argTypes) {
            this.argTypes = argTypes;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }
    }

}
