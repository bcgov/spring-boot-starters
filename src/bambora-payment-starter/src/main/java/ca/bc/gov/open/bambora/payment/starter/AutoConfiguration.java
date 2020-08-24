package ca.bc.gov.open.bambora.payment.starter;

import ca.bc.gov.open.bambora.payment.starter.managment.BamboraCardService;
import ca.bc.gov.open.bambora.payment.starter.managment.BamboraCardServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class AutoConfiguration {

    private final BamboraProperties bamboraProperties;

    public AutoConfiguration(BamboraProperties bamboraProperties) {
        this.bamboraProperties = bamboraProperties;
    }

    @Bean
    @ConditionalOnMissingBean(BamboraCardService.class)
    public BamboraCardService bamboraCardService() {
        return new BamboraCardServiceImpl(bamboraProperties);
    }
}
