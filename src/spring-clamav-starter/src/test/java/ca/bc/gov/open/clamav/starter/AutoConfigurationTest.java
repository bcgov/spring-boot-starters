package ca.bc.gov.open.clamav.starter;

import fi.solita.clamav.ClamAVClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AutoConfigurationTest {


    ApplicationContextRunner context;

    ClamAvProperties clamAvProperties;

    @BeforeEach
    public void setUp() {

        context = new ApplicationContextRunner()
                .withUserConfiguration(AutoConfiguration.class)
                .withPropertyValues("bcgov.clamav.host=testhost")
                .withPropertyValues("bcgov.clamav.port=1234")
                .withPropertyValues("bcgov.clamav.timeout=500")
                .withUserConfiguration(ClamAvProperties.class);

    }

    @Test
    void clamAVClient() {
        context.run(it -> { assertThat(it).hasSingleBean(ClamAVClient.class); });
    }

    @Test
    void clamAvService() {
        context.run(it -> { assertThat(it).hasSingleBean(ClamAvService.class); });
    }
}