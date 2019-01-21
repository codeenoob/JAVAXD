package org.jing.core.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jing.core.lang.JingException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    public static HashMap<Object, Object> properties2HashMap(Properties properties) {
        HashMap<Object, Object> retMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry: properties.entrySet()) {
            retMap.put(entry.getKey(), entry.getValue());
        }
        return retMap;
    }
}
