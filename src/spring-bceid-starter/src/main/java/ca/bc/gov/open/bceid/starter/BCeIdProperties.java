package ca.bc.gov.open.bceid.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bcgov.bceid.service")
public class BCeIdProperties {

    private String uri;
    private String username;
    private String password;
    private String onlineServiceId;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOnlineServiceId() {
        return onlineServiceId;
    }

    public void setOnlineServiceId(String onlineServiceId) {
        this.onlineServiceId = onlineServiceId;
    }
}
