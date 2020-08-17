package ca.bc.gov.open.bceid.starter;

import ca.bc.gov.open.bceid.starter.account.BCeIDAccountService;
import ca.bc.gov.open.bceid.starter.account.BCeIDAccountServiceImpl;
import ca.bc.gov.open.bceid.starter.account.mappers.AccountDetailRequestMapper;
import ca.bc.gov.open.bceid.starter.account.mappers.AccountDetailRequestMapperImpl;
import ca.bc.gov.open.bceid.starter.account.mappers.IndividualIdentityMapper;
import ca.bc.gov.open.bceid.starter.account.mappers.IndividualIdentityMapperImpl;
import ca.bceid.webservices.client.v9.BCeIDServiceSoap;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BCeIdProperties.class)
public class AutoConfiguration {

    private final BCeIdProperties bCeIdProperties;

    public AutoConfiguration(BCeIdProperties bCeIdProperties) {
        this.bCeIdProperties = bCeIdProperties;
    }

    @Bean
    public AccountDetailRequestMapper accountDetailRequestMapper() {
        return new AccountDetailRequestMapperImpl();
    }

    @Bean
    public IndividualIdentityMapper individualIdentityMapper() {
        return new IndividualIdentityMapperImpl();
    }

    @Bean
    public BCeIDServiceSoap bCeIDServiceSoap() {

        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(BCeIDServiceSoap.class);
        jaxWsProxyFactoryBean.setAddress(bCeIdProperties.getUri());
        if(StringUtils.isNotBlank(bCeIdProperties.getUsername()))
            jaxWsProxyFactoryBean.setUsername(bCeIdProperties.getUsername());
        if(StringUtils.isNotBlank(bCeIdProperties.getPassword()))
            jaxWsProxyFactoryBean.setPassword(bCeIdProperties.getPassword());
        return (BCeIDServiceSoap) jaxWsProxyFactoryBean.create();

    }

    @Bean
    public BCeIDAccountService bCeIDUserService(BCeIDServiceSoap bCeIDServiceSoap, AccountDetailRequestMapper accountDetailRequestMapper, IndividualIdentityMapper individualIdentityMapper) {
        return new BCeIDAccountServiceImpl(bCeIDServiceSoap, bCeIdProperties, accountDetailRequestMapper, individualIdentityMapper);
    }
}
