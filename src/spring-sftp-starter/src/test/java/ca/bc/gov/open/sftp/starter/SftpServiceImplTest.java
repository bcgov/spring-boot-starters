package ca.bc.gov.open.sftp.starter;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SftpServiceImplTest {

    public static final String CASE_1 = "case1";
    public static final String FAKE_INPUT_STREAM = "fake input stream";
    public static final String CASE_2 = "case2";
    public static final String CASE_3 = "case3";
    public static final String CASE_4 = "case4";

    public static final String FILE_1 = "file1";
    public static final String FILE_2 = "file2";

    @Mock
    private JschSessionProvider jschSessionProviderMock;

    @Mock
    private Session sessionMock;

    @Mock
    private ChannelSftp channelSftpMock;

    private SftpServiceImpl sut;

    private InputStream fakeInputStream() {

        String result = FAKE_INPUT_STREAM;

        return new ByteArrayInputStream(result.getBytes());
    }

    @BeforeEach
    public void setUp() throws JSchException, SftpException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(sessionMock.openChannel(Mockito.eq("sftp"))).thenReturn(channelSftpMock);
        Mockito.when(jschSessionProviderMock.getSession()).thenReturn(sessionMock);

        Mockito.when(channelSftpMock.get(CASE_1)).thenReturn(fakeInputStream());
        Mockito.when(channelSftpMock.get(CASE_3)).thenThrow(SftpException.class);
        Mockito.when(channelSftpMock.get(CASE_4)).thenReturn(null);
        Mockito.when(channelSftpMock.isConnected()).thenReturn(true);

        SftpProperties sftpProperties = new SftpProperties();
        sut = new SftpServiceImpl(jschSessionProviderMock, sftpProperties);
    }

    @Test
    public void withValidContentShouldReturnContent() throws IOException {

        InputStream result = sut.getContent(CASE_1);

        String text =  IOUtils.toString(result, StandardCharsets.UTF_8.name());

        Assertions.assertEquals(FAKE_INPUT_STREAM, text);

    }

    @Test
    public void withJSchExceptionShouldThrowDpsSftpException() throws JSchException {
        Mockito.when(sessionMock.openChannel(Mockito.eq("sftp"))).thenThrow(JSchException.class);
        Assertions.assertThrows(StarterSftpException.class, () -> {
            InputStream result = sut.getContent(CASE_2);
        });
    }

    @Test
    public void withSftpExceptionShouldThrowDpsSftpException() {
        Assertions.assertThrows(StarterSftpException.class, () -> {
            InputStream result = sut.getContent(CASE_3);
        });
    }

    @Test
    public void withValidFileShouldMove() {
        Assertions.assertDoesNotThrow( () -> {
            sut.moveFile(FILE_1, FILE_2);
        });
    }

    @Test
    public void withMoveFileJSchExceptionShouldThrowDpsSftpException() throws JSchException {
        Mockito.when(sessionMock.openChannel(Mockito.eq("sftp"))).thenThrow(JSchException.class);
        Assertions.assertThrows(StarterSftpException.class, () -> {
            sut.moveFile(FILE_1, FILE_2);
        });
    }

    @Test
    public void withMoveFileSftpExceptionShouldThrowDpsSftpException() throws SftpException {
        Mockito.doThrow(SftpException.class).when(channelSftpMock).rename(Mockito.anyString(), Mockito.anyString());
        Assertions.assertThrows(StarterSftpException.class, () -> {
            sut.moveFile(FILE_1, FILE_2);
        });
    }

    @Test
    public void forPutWithInputstreamShouldPutFile() {
        Assertions.assertDoesNotThrow(() -> sut.put(new ByteArrayInputStream(FAKE_INPUT_STREAM.getBytes()), CASE_1));
    }

    @Test
    public void forPutWithSftpExceptionShouldTrhowDpsSftpException() throws SftpException {
        String value= "some text";
        Mockito.doThrow(SftpException.class).when(channelSftpMock).put(Mockito.any(InputStream.class), Mockito.eq(CASE_2));
        Assertions.assertThrows(StarterSftpException.class, () -> {
            sut.put(new ByteArrayInputStream(FAKE_INPUT_STREAM.getBytes()), CASE_2);
        });
    }

}
