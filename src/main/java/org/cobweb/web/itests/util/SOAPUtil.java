package org.cobweb.web.itests.util;

import org.jdom.Element;
import org.jdom.Namespace;


public final class SOAPUtil {
    public static final Namespace NAMESPACE_ENV = Namespace.getNamespace("env", "http://www.w3.org/2003/05/soap-envelope");

    /**
     * Default constructor.
     * Builds a SOAPUtil.
     */
   private SOAPUtil() {}

    public static Element embed(Element response) {
        Element envl = new Element("Envelope", NAMESPACE_ENV);
        Element body = new Element("Body",     NAMESPACE_ENV);

        envl.addContent(body);
        body.addContent(response);

        return envl;
    }

    public static Element embedExc(Element error, boolean sender, String errorCode, String message) {
        Namespace ns = NAMESPACE_ENV;

        Element fault = new Element("Fault", ns);

        //--- setup code

        Element code = new Element("Code", ns);
        fault.addContent(code);

        String  type  = sender ? "env:Sender" : "env:Receiver";
        Element value = new Element("Value", ns);
        value.setText(type);

        code.addContent(value);

        //--- setup subcode

        Element subCode = new Element("Subcode", ns);
        code.addContent(subCode);

        value = new Element("Value", ns);
        value.setText(errorCode);

        subCode.addContent(value);

        //--- setup reason

        Element reason = new Element("Reason", ns);
        fault.addContent(reason);

        Element text = new Element("Text", ns);
        reason.addContent(text);

        text.setText(message);
        text.setAttribute("lang", "en", Namespace.XML_NAMESPACE);

        //--- setup detail

        Element detail = new Element("Detail", ns);
        detail.addContent(error);
        fault.addContent(detail);

        return embed(fault);
    }


    public static boolean isEnvelope(Element elem) {
        if (!elem.getName().equals("Envelope"))
            return false;

        Namespace ns = elem.getNamespace();

        return (ns.getURI().equals(NAMESPACE_ENV.getURI()));
    }
}
