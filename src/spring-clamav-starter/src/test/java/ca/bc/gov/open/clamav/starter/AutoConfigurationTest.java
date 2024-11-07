package ca.bc.gov.open.clamav.starter;

import fi.solita.clamav.ClamAVClient;
import org.junit.jupiter.api.Assertions;
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

        context = new ApplicationContextRunner();

    }

    @Test
    void clamAVClient() {
        context.run(it -> {
            Assertions.assertNotNull(assertThat(it).getBean(ClamAVClient.class));
        });
    }
    @Test
    void clamAvService() {
        context.run(it -> {
            Assertions.assertNotNull(assertThat(it).getBean(ClamAvService.class));
        });
    }}
