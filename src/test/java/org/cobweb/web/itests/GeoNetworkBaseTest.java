package org.cobweb.web.itests;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.log4j.Logger;

/**
 * Base class for GeoNetwork integration tests.
 *
 * @author Jose García
 */
public abstract class GeoNetworkBaseTest {
    private final static Logger LOGGER = Logger.getLogger(GeoNetworkBaseTest.class);

    protected static final String gnServiceURL = "https://dyfi.cobwebproject.eu/geonetwork/";
    protected static final String gnUsername = "admin";
    protected static final String gnPassword = "admin";

    protected File loadFile(String name) {
        try {
            URL url = this.getClass().getClassLoader().getResource(name);
            if(url == null)
                throw new IllegalArgumentException("Cant get file '"+name+"'");
            File file = new File(url.toURI());
            return file;
        } catch (URISyntaxException e) {
            LOGGER.error("Can't load file " + name + ": " + e.getMessage(), e);
            return null;
        }
    }

}
