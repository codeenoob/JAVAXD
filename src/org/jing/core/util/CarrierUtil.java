package org.jing.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jing.core.lang.Carrier;
import org.jing.core.lang.Const;
import org.jing.core.lang.JingException;

public class CarrierUtil {
    public static Carrier readFile(String filePath) throws JingException {
        return string2Carrier(FileUtil.readFile(filePath));
    }
    
    public static void checkFeasibility(HashMap<String, Object> valueMap) throws JingException {
        // 检测valueMap是不是为空
        GenericUtil.throwNullException(valueMap, "JING-LANG-CARR-0001", "Value Map Is Null");
    }
    
    public static Carrier string2Carrier(String content) throws JingException {
        return string2Carrier(content, false);
    }
    
    @SuppressWarnings("unchecked")
    public static Carrier string2Carrier(String content, boolean ignoreRootNodeFormat) throws JingException {
        Document document = ToUtil.string2xml(content);
        Carrier carrier = null;
        Element root = document.getRootElement();
        if (!ignoreRootNodeFormat && !Const.CARRIER_ROOT_NODE.equals(root.getName())) {
            // 格式不匹配
            throw new JingException("JING-LANG-CARR-0002", 
                new StringBuilder("Unexpected Root Node Format. [Expected: ").append(Const.CARRIER_ROOT_NODE)
                .append("][Unexpected: ").append(root.getName()).append("]").toString());
        }
        Object object = element2Object(root);
        if (object instanceof HashMap) {
            carrier = new Carrier();
            carrier.setDocument(document);
            carrier.setValueMap((HashMap<String, Object>) object);
        }
        else {
            if (object instanceof String) {
                String tempString = (String) object;
                tempString = GenericUtil.ifEmpty(tempString).trim().replaceAll("\r\n", "").replaceAll("\n", "");
                if (GenericUtil.isEmpty(tempString)) {
                    carrier = new Carrier();
                    return carrier;
                }
            }
            // 格式不匹配
            throw new JingException("JING-LANG-CARR-0002", 
                new StringBuilder("Unexpected Format. [").append(root.getName()).append(": ").append(object).append("]").toString());
        }
        return carrier;
    }
    
    public static Object element2Object(Element element) throws JingException {
        if (element.isTextOnly()) {
            return element.getText();
        }
        HashMap<String, Object> retMap = new HashMap<>();
        
        Iterator<Element> iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element _element = iterator.next();
            String key = _element.getName();
            addValueByKey(retMap, key, element2Object(_element));
        }
        
        return retMap;
    }
    
    public static  int getCount(HashMap<String, Object> valueMap, String key) throws JingException {
        checkFeasibility(valueMap);
        if (valueMap.containsKey(key)) {
            Object object = valueMap.get(key);
            if (object instanceof List) {
                return GenericUtil.countList((List<?>) object);
            }
            else {
                return 1;
            }
        }
        else {
            return 0;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void addValueByKey(HashMap<String, Object> hashMap, String key, Object value) throws JingException {
        checkFeasibility(hashMap);
        if (hashMap.containsKey(key)) {
            Object node = hashMap.get(key);
            if (node instanceof List) {
                ((List<Object>) node).add(value);
            }
            else {
                List<Object> _list = new ArrayList<>();
                hashMap.put(key, _list);
                _list.add(node);
                _list.add(value);
            }
        }
        else {
            hashMap.put(key, value);
        }
    }

    @SuppressWarnings("unchecked")
    public static void setValueByKey(HashMap<String, Object> hashMap, String key, Object value, int seq) throws JingException {
        checkFeasibility(hashMap);
        if (hashMap.containsKey(key)) {
            Object node = hashMap.get(key);
            if (null != node && node instanceof List) {
                int size = GenericUtil.countList((List<Object>) node);
                if (seq + 1 > size) {
                    ((List<Object>) node).add(value);
                }
                else {
                    ((List<Object>) node).set(seq, value);
                }
            }
            else if (0 == seq) {
                hashMap.put(key, value);
            }
            else {
                List<Object> _list = new ArrayList<>();
                hashMap.put(key, _list);
                _list.add(node);
                _list.add(value);
            }
        }
        else {
            hashMap.put(key, value);
        }
    }
    
    public static Object getValueByKey(HashMap<String, Object> hashMap, String key, Object defaultValue, int seq) throws JingException {
        checkFeasibility(hashMap);
        if (!hashMap.containsKey(key)) {
            return defaultValue;
        }
        else {
            Object value = hashMap.get(key);
            if (null == value) {
                return null;
            }
            else {
                if (value instanceof List) {
                    int count = GenericUtil.countList((List<?>) value);
                    if (seq < count) {
                        return ((List<?>) value).get(seq);
                    }
                    else {
                        return ((List<?>) value).get(count - 1);
                    }
                }
                else {
                    return value;
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void object2Element(Object object, String name, Element element) {
        if (null == object) {
            Element retElement = DocumentHelper.createElement(name);
            element.add(retElement);
        }
        else if (object instanceof List) {
            int size = ((List<Object>) object).size();
            for (int i = 0; i < size; i++) {
                Element retElement = DocumentHelper.createElement(name);
                element.getParent().add(retElement);
                object2Element(((List<Object>) object).get(i), name, retElement);
            }
            if (size > 1) {
                element.getParent().remove(element);
            }
        }
        else if (object instanceof HashMap) {
            for (Entry<String, Object> entry: ((HashMap<String, Object>) object).entrySet()) {
                Element retElement = DocumentHelper.createElement(entry.getKey());
                element.add(retElement);
                object2Element(entry.getValue(), entry.getKey(), retElement);
            }
        }
        else {
            element.setText(String.valueOf(object));
        }
    }
}
