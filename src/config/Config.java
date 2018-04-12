package config;

/**
 * Created by xaviamorcastillo on 12/4/18.
 */
public class Config {

    private String ip;
    private int port;

    public Config(){
    }

    public Config(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
