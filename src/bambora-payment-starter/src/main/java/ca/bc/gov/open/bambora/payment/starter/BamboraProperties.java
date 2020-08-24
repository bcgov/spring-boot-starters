package ca.bc.gov.open.bambora.payment.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BamboraProperties.class)
@ConfigurationProperties(prefix = "bambora")
public class BamboraProperties {
    private String merchantId;
    private String hostedProfileUrl;
    private String hostedProfileServiceVersion;
    private String key;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getHostedProfileUrl() {
        return hostedProfileUrl;
    }

    public void setHostedProfileUrl(String hostedProfileUrl) {
        this.hostedProfileUrl = hostedProfileUrl;
    }
}
