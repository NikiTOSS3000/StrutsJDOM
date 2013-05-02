package com.epam.struts.xsl;

import java.io.StringWriter;
import java.util.Map;
import java.util.Set;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

public final class XSLTransformer {

    private final ProductsTransformerFactory factory;

    public String transform(String file, String xsl, Map<String, Object> param) throws TransformerException {
        Transformer transformer = factory.getTransformer(xsl);
        StringWriter writer = new StringWriter();
        if (param != null) {
            Set<String> keySet = param.keySet();
            for (String key : keySet) {
                transformer.setParameter(key, param.get(key));
            }
        }
        transformer.transform(factory.getSource(file), new StreamResult(writer));
        return writer.toString();
    }

    public XSLTransformer() {
        factory = ProductsTransformerFactory.getInstance();
    }
}