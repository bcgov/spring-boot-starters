package ca.bc.gov.open.bceid.starter;

import ca.bc.gov.open.bceid.starter.account.BCeIDAccountService;
import ca.bceid.webservices.client.v9.BCeIDServiceSoap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoConfigurationTest {
    ApplicationContextRunner context = new ApplicationContextRunner();

    @Test
    public void testConfigure() {

        context.run(it -> {
            Assertions.assertNotNull(assertThat(it).getBean(BCeIDServiceSoap.class));
            Assertions.assertNotNull(assertThat(it).getBean(BCeIDAccountService.class));
        });

    }
}
