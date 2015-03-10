package org.cobweb.web.itests.util;

import org.cobweb.web.itests.util.nio.NioPathAwareEntityResolver;
import org.cobweb.web.itests.util.nio.NioPathHolder;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import javax.xml.XMLConstants;
import java.io.*;
import java.nio.file.Path;

//=============================================================================

/**
 *  General class of useful static methods.
 */
public final class Xml {

    public static final Namespace xsiNS = Namespace.getNamespace("xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
    public static final NioPathAwareEntityResolver PATH_RESOLVER = new NioPathAwareEntityResolver();

    /**
     * Loads xml from a string and returns its root node
     * (validates the xml if required).
     *
     * @param data
     * @param validate
     * @return
     * @throws java.io.IOException
     * @throws org.jdom.JDOMException
     */
    public static Element loadString(String data, boolean validate)
                                                throws IOException, JDOMException {
        //SAXBuilder builder = new SAXBuilder(validate);
        SAXBuilder builder = getSAXBuilderWithPathXMLResolver(validate, null); // oasis catalogs are used
        Document jdoc    = builder.build(new StringReader(data));

        return (Element) jdoc.getRootElement().detach();
    }

    /**
     * Loads xml from an input stream and returns its root node.
     *
     * @param input
     * @return
     * @throws java.io.IOException
     * @throws org.jdom.JDOMException
     */
    public static Element loadStream(InputStream input) throws IOException, JDOMException {
        SAXBuilder builder = getSAXBuilderWithPathXMLResolver(false, null); //new SAXBuilder();
        builder.setFeature("http://apache.org/xml/features/validation/schema",false);
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
        Document jdoc    = builder.build(input);

        return (Element) jdoc.getRootElement().detach();
    }

    private static SAXBuilder getSAXBuilderWithPathXMLResolver(boolean validate, Path base) {
        SAXBuilder builder = new SAXBuilder(validate);
        builder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        NioPathHolder.setBase(base);
        builder.setEntityResolver(Xml.PATH_RESOLVER);
        return builder;
    }

    public static String getString(Element data) {
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

        return outputter.outputString(data);
    }

    public static String getString(Document data) {
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

        return outputter.outputString(data);
    }

}