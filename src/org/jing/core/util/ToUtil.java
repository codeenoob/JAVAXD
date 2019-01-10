package org.jing.core.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jing.core.lang.JingException;

public class ToUtil {
    public static Document string2xml(String content) throws JingException {
        Document document = null;
        try {
            document = DocumentHelper.parseText(content);
        }
        catch (DocumentException e) {
            throw new JingException("JING-TO-XML-0001", "Failed To Transfer String To XML Document");
        }
        return document;
    }
}
