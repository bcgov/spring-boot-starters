package ca.bc.gov.open.clamav.starter;

import fi.solita.clamav.ClamAVClient;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClamAvServiceImplTest {

    private ClamAvServiceImpl sut;

    @Mock
    private ClamAVClient clamAvClient;

    @BeforeEach
    void setUp() throws IOException {

        MockitoAnnotations.initMocks(this);
        sut = new ClamAvServiceImpl(clamAvClient);

    }

    @Test
    @DisplayName("OK: file without virus")
    public void withFileNoVirusShouldWork() throws VirusDetectedException, IOException {

        Mockito.when(clamAvClient.scan(Mockito.any(InputStream.class))).thenReturn("OK".getBytes());
        Assertions.assertDoesNotThrow(() -> sut.scan(new ByteArrayInputStream("goodfile".getBytes())));

    }

    @Test
    @DisplayName("EXCEPTION: virus found")
    public void withFileVirusShouldThrowException() throws IOException {

        Mockito.when(clamAvClient.scan(Mockito.any(InputStream.class))).thenReturn("FOUND".getBytes());
        Assertions.assertThrows(VirusDetectedException.class, () -> sut.scan(new ByteArrayInputStream("goodfile".getBytes())));

    }

    @Test
    @DisplayName("EXCEPTION: io exception")
    public void withIOExceptionShouldThrowException() throws IOException {

        Mockito.when(clamAvClient.scan(Mockito.any(InputStream.class))).thenThrow(IOException.class);
        Assertions.assertThrows(ClamAvException.class, () -> sut.scan(new ByteArrayInputStream("goodfile".getBytes())));

    }

    @Test
    @DisplayName("OK: test ping")
    public void testPing() throws IOException {

        Mockito.when(clamAvClient.ping()).thenReturn(true);

        Assertions.assertTrue(sut.ping());
    }

}
