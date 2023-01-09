package personal.ivan.sender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class Config {
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private int port;

    public Config(@Value("${server.port}") String port) {
        this.port = Integer.parseInt(port);
    }

    @Override
    public String toString() {
        return "{" + this.getPort() + "}";
    }
}
