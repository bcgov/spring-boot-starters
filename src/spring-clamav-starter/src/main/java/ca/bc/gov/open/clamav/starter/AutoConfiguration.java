package ca.bc.gov.open.clamav.starter;

import fi.solita.clamav.ClamAVClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(ClamAvProperties.class)
public class AutoConfiguration {

    private final ClamAvProperties clamAvProperties;

    Logger logger = LoggerFactory.getLogger(AutoConfiguration.class);

    public AutoConfiguration(ClamAvProperties clamAvProperties) {
        this.clamAvProperties = clamAvProperties;
    }

    @Bean
    @ConditionalOnMissingBean(ClamAVClient.class)
    public ClamAVClient clamAVClient() {

        logger.debug("Configuring ClamAv Client");
        logger.debug("ClamAv host: [{}]", clamAvProperties.getHost());
        logger.debug("ClamAv port: [{}]", clamAvProperties.getPort());
        logger.debug("ClamAv timeout: [{}]", clamAvProperties.getTimeout());

        return new ClamAVClient(clamAvProperties.getHost(), clamAvProperties.getPort(), clamAvProperties.getTimeout());

    }

    @Bean
    @ConditionalOnMissingBean(ClamAvService.class)
    public ClamAvService clamAvService(ClamAVClient clamAVClient) {
        return new ClamAvServiceImpl(clamAVClient);
    }

}
