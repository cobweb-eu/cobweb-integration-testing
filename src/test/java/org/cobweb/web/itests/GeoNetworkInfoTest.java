package org.cobweb.web.itests;

import org.jdom.Element;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

/**
 * Test for Geonetwork info request.
 *
 * See http://geonetwork-opensource.org/manuals/trunk/eng/developer/xml_services/services_site_info_forwarding.html#site-information-xml-info
 *
 * @author Jose García
 */
public class GeoNetworkInfoTest extends GeoNetworkBaseTest {
    @Test
    public void testInfoGet() {
        try {
            String requestPath = "/public/eng/xml.info?type=site";
            XmlRequest request = new GeonetHttpRequestFactory().createXmlRequest(new URL(gnServiceURL + requestPath));
            Element response = request.execute();
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getName(), "info");

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void testInfoPost() {
        try {
            String requestPath = "/public/eng/xml.info";
            Element requestEl = new Element("request");
            requestEl.addContent(new Element("type").setText("site"));
            XmlRequest request = new GeonetHttpRequestFactory().createXmlRequest(new URL(gnServiceURL + requestPath));
            Element response = request.execute(requestEl);
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getName(), "info");

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
