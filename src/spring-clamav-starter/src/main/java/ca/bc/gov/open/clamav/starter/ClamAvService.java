package ca.bc.gov.open.clamav.starter;

import java.io.IOException;
import java.io.InputStream;

public interface ClamAvService {

    void scan(InputStream inputStream) throws VirusDetectedException;

    boolean ping() throws IOException;

}
