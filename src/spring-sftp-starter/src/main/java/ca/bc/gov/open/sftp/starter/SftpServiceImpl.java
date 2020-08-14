package ca.bc.gov.open.sftp.starter;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SftpServiceImpl implements SftpService {

    interface SftpFunction {
        void exec(ChannelSftp channelSftp) throws SftpException;
    }

    public static final int BUFFER_SIZE = 8000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JschSessionProvider jschSessionProvider;

    private final SftpProperties sftpProperties;

    public SftpServiceImpl(JschSessionProvider jschSessionProvider, SftpProperties sftpProperties) {
        this.jschSessionProvider = jschSessionProvider;
        this.sftpProperties = sftpProperties;
    }

    public ByteArrayInputStream getContent(String remoteFilename) {

        String sftpRemoteFilename = getFilePath(remoteFilename);

        ByteArrayInputStream result = null;
        byte[] buff = new byte[BUFFER_SIZE];

        Session session = null;

        try (ByteArrayOutputStream bao = new ByteArrayOutputStream()) {

            executeSftpFunction(channelSftp -> {
                try {

                    int bytesRead;

                    logger.debug("Attempting to get remote file [{}]", sftpRemoteFilename);
                    InputStream inputStream = channelSftp.get(sftpRemoteFilename);
                    logger.debug("Successfully get remote file [{}]", sftpRemoteFilename);

                    while ((bytesRead = inputStream.read(buff)) != -1) {
                        bao.write(buff, 0, bytesRead);
                    }

                } catch (IOException e) {
                    throw new StarterSftpException(e.getMessage(), e.getCause());
                }
            });

            byte[] data = bao.toByteArray();

            try (ByteArrayInputStream resultBao = new ByteArrayInputStream(data)) {
                result = resultBao;
            }

        } catch (IOException e) {
            throw new StarterSftpException(e.getMessage(), e.getCause());
        }

        return result;
    }

    /**
     * Move the file to a destination
     *
     * @param remoteFileName
     * @param destinationFilename
     * @throws StarterSftpException
     */
    public void moveFile(String remoteFileName, String destinationFilename) {

        String sftpRemoteFilename = getFilePath(remoteFileName);
        String sftpDestinationFilename = getFilePath(destinationFilename);

        executeSftpFunction(channelSftp -> {
                channelSftp.rename(sftpRemoteFilename, sftpDestinationFilename);
                logger.debug("Successfully renamed files on the sftp server from {} to {}", sftpRemoteFilename,
                        sftpDestinationFilename);
        });

    }

    @Override
    public void put(InputStream inputStream, String remoteFileName) {

        String sftpRemoteFilename = getFilePath(remoteFileName);

        executeSftpFunction(channelSftp -> {
                channelSftp.put(inputStream, sftpRemoteFilename);
                logger.debug("Successfully uploaded file [{}]", remoteFileName);
        });

    }

    /**
     * Returns a list of file
     * @param remoteDirectory
     * @return
     */
    @Override
    public List<String> listFiles(String remoteDirectory) {

        String sftpRemoteDirectory = getFilePath(remoteDirectory);

        List<String> result = new ArrayList<>();

        executeSftpFunction(channelSftp -> {
            Vector fileList = channelSftp.ls(sftpRemoteDirectory);

            for(int i = 0; i < fileList.size(); i++) {
                logger.debug("Attempting to list files in [{}]", remoteDirectory);
                ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry)fileList.get(i);
                logger.debug("Successfully to list files in [{}]", remoteDirectory);
                result.add(lsEntry.getFilename());
            }

        });

        return result;
    }

    private void executeSftpFunction(SftpFunction sftpFunction) {

        ChannelSftp channelSftp = null;
        Session session = null;

        try {

            session = jschSessionProvider.getSession();

            logger.debug("Attempting to open sftp channel");
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            logger.debug("Successfully connected to sftp server");

            sftpFunction.exec(channelSftp);

        } catch (JSchException | SftpException e) {
            throw new StarterSftpException(e.getMessage(), e.getCause());
        } finally {
            if (channelSftp != null && channelSftp.isConnected())
                channelSftp.disconnect();

            jschSessionProvider.closeSession(session);
        }
    }

    private String getFilePath(String remoteFilename) {
        return
                StringUtils.isNotBlank(sftpProperties.getRemoteLocation()) ?
                        new File(sftpProperties.getRemoteLocation(), remoteFilename).getPath() :
                        new File(remoteFilename).getPath();
    }

}
