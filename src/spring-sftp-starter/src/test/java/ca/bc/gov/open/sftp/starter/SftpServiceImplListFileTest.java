package ca.bc.gov.open.sftp.starter;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Vector;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SftpServiceImplListFileTest {


    private static final String CASE_2 = "case2";
    private static final String CASE_3 = "case2";
    private static final String CASE_1 = "case1";

    private static final String FILENAME_1 = "filename1";
    private static final String FILENAME_2 = "filename2";
    private static final String FILENAME_3 = "filename3";

    private static final String REMOTE = "remote";
    private static final String TEST_WINDOWS = "test\\windows";

    @Mock
    private JschSessionProvider jschSessionProviderMock;

    @Mock
    private Session sessionMock;

    @Mock
    private ChannelSftp channelSftpMock;

    @Mock
    private ChannelSftp.LsEntry lsEntry1Mock;

    @Mock
    private ChannelSftp.LsEntry lsEntry2Mock;

    @Mock
    private ChannelSftp.LsEntry lsEntry3Mock;

    @Mock
    private SftpProperties sftpPropertiesMock;

    private SftpServiceImpl sut;

    @BeforeEach
    public void setUp() throws JSchException, SftpException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(sessionMock.openChannel(Mockito.eq("sftp"))).thenReturn(channelSftpMock);
        Mockito.when(jschSessionProviderMock.getSession()).thenReturn(sessionMock);

        Mockito.when(lsEntry1Mock.getFilename()).thenReturn(FILENAME_1);
        Mockito.when(lsEntry2Mock.getFilename()).thenReturn(FILENAME_2);
        Mockito.when(lsEntry3Mock.getFilename()).thenReturn(FILENAME_3);

        Vector<ChannelSftp.LsEntry> fakeList = new Vector<>();
        fakeList.add(lsEntry1Mock);
        fakeList.add(lsEntry2Mock);

        Mockito.when(channelSftpMock.ls(CASE_1)).thenReturn(fakeList);

        Vector<ChannelSftp.LsEntry> fakeList2 = new Vector<>();
        fakeList2.add(lsEntry1Mock);
        fakeList2.add(lsEntry2Mock);
        fakeList2.add(lsEntry3Mock);

        Mockito.when(channelSftpMock.ls(REMOTE + "/" +CASE_2)).thenReturn(fakeList2);

        Mockito.when(channelSftpMock.ls(  "test/windows/" +CASE_3)).thenReturn(fakeList2);

        Mockito.when(channelSftpMock.isConnected()).thenReturn(true);

        sut = new SftpServiceImpl(jschSessionProviderMock, sftpPropertiesMock);
    }

    @Test
    @DisplayName("Success - Test with valid remote file name list")
    public void withReturnedListShouldReturnAListOfFileName() {

        List<String> actual =  sut.listFiles(CASE_1);

        Assertions.assertEquals(2, actual.size());
        Assertions.assertTrue(actual.contains(FILENAME_1));
        Assertions.assertTrue(actual.contains(FILENAME_2));

    }

    @Test
    @DisplayName("Success - Test with valid remote file name list and remote location set")
    public void withRemoteLocationSetListShouldReturnAListOfFileName() {

        Mockito.when(sftpPropertiesMock.getRemoteLocation()).thenReturn(REMOTE);

        List<String> actual =  sut.listFiles(CASE_2);

        Assertions.assertEquals(3, actual.size());
        Assertions.assertTrue(actual.contains(FILENAME_1));
        Assertions.assertTrue(actual.contains(FILENAME_2));

    }

    @Test
    @DisplayName("Success - Test with valid remote file name windows like list and remote location set")
    public void withRemoteLocationSetAsWindowsListShouldReturnAListOfFileName() {

        Mockito.when(sftpPropertiesMock.getRemoteLocation()).thenReturn(TEST_WINDOWS);

        List<String> actual =  sut.listFiles(CASE_3);

        Assertions.assertEquals(3, actual.size());
        Assertions.assertTrue(actual.contains(FILENAME_1));
        Assertions.assertTrue(actual.contains(FILENAME_2));

    }


}
