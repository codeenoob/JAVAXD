package org.jing.core.util;

import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jing.core.lang.JingException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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

    public static JSONObject getRequestParameters(HttpServletRequest request) throws Exception {
        int counter = 0;
        StringBuilder stbr = new StringBuilder();
        InputStream stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = reader.readLine();
        while (null != line) {
            if(counter > 0){
                stbr.append("\r\n");
            }
            stbr.append(line);
            line = reader.readLine();
            counter++;
        }
        stream.close();
        JSONObject json = JSONObject.fromObject(stbr.toString());
        return json;
    }
}
