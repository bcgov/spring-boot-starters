package ca.bc.gov.open.clamav.starter;

import fi.solita.clamav.ClamAVClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ClamAvServiceImpl implements ClamAvService {

    private Logger logger = LoggerFactory.getLogger(AutoConfiguration.class);

    private final ClamAVClient clamAVClient;

    public ClamAvServiceImpl(ClamAVClient clamAVClient) {
        this.clamAVClient = clamAVClient;
    }

    @Override
    public void scan(InputStream inputStream) throws VirusDetectedException {

        byte[] reply;
        try {
            reply = clamAVClient.scan(inputStream);
        } catch (IOException e) {
            logger.error("ClamAv Service could not scan the input");
            throw new ClamAvException("Could not scan the input", e);
        }
        if (!ClamAVClient.isCleanReply(reply)) {
            logger.error("Virus Detected using ClamAv server");
            throw new VirusDetectedException("ClamAv has detected a virus in the input");
        }

    }

    @Override
    public boolean ping() throws IOException {
        return clamAVClient.ping();
    }
}
