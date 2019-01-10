package org.jing.core.lang;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jing.core.util.CarrierUtil;
import org.jing.core.util.GenericUtil;

public class Carrier {
    HashMap<String, Object> valueMap = null;
    
    Document document = null;
    
    public Carrier() {
        // TODO Auto-generated constructor stub
        valueMap = new HashMap<>();
    }
    
    private void checkFeasibility() throws JingException {
        CarrierUtil.checkFeasibility(valueMap);
    }

    public int getCount(String key) throws JingException {
        checkFeasibility();
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
    
    public void setValueMap(HashMap<String, Object> valueMap) {
        this.valueMap = valueMap;
    }

    public void addValueByKey(String key, Object value) throws JingException {
        CarrierUtil.addValueByKey(valueMap, key, value);
    }
    
    public void setValueByKey(int seq, String key, Object value) throws JingException {
        CarrierUtil.setValueByKey(valueMap, key, value, seq);
    }
    
    public void setValueByKey(String key, Object value) throws JingException {
        CarrierUtil.setValueByKey(valueMap, key, value, 0);
    }
    
    public Object getValueByKey(int seq, String key, Object defaultValue) throws JingException {
        return CarrierUtil.getValueByKey(valueMap, key, defaultValue, seq);
    }
    
    public Object getValueByKey(String key, Object defaultValue) throws JingException {
        return CarrierUtil.getValueByKey(valueMap, key, defaultValue, 0);
    }
    
    public Object getValueByKey(int seq, String key) throws JingException {
        return CarrierUtil.getValueByKey(valueMap, key, null, seq);
    }
    
    public Object getValueByKey(String key) throws JingException {
        return CarrierUtil.getValueByKey(valueMap, key, null, 0);
    }
    
    public String getString(int seq, String key, String deaultString) throws JingException {
        Object retObject = getValueByKey(seq, key, deaultString);
        return GenericUtil.parseString(retObject);
    }
    
    public String getString(int seq, String key) throws JingException {
        Object retObject = getValueByKey(seq, key);
        return GenericUtil.parseString(retObject);
    }
    
    public String getString(String key, String deaultString) throws JingException {
        Object retObject = getValueByKey(key, deaultString);
        return GenericUtil.parseString(retObject);
    }
    
    public String getString(String key) throws JingException {
        Object retObject = getValueByKey(key);
        return GenericUtil.parseString(retObject);
    }
    
    @SuppressWarnings("unchecked")
    public Carrier getCarrier(String key, int seq) throws JingException {
        Object temp = getValueByKey(seq, key, null);
        if (null == temp) {
            throw new JingException("JING-LANG-CARR-0003", 
                new StringBuilder("Key ").append(key).append(" Doesn't Exist In Carrier").toString());
        }
        if (temp instanceof Carrier) {
            return (Carrier) temp;
        }
        else if (temp instanceof HashMap) {
            Carrier tempC = new Carrier();
            try {
                tempC.valueMap = (HashMap<String, Object>) temp;
            }
            catch (Exception e) {
                tempC.valueMap = new HashMap<String, Object>((Map<? extends String, ? extends Object>)temp);
            }
            return tempC;
        }
        else {
            throw new JingException("JING-LANG-CARR-0003", 
                new StringBuilder("Invalid Key ").append(key).append(" In Carrier").toString());
        }
    }
    
    public Carrier getCarrier(String key) throws JingException {
        return getCarrier(key, 0);
    }
    
    public HashMap<String, Object> getValueMap() {
        return valueMap;
    }
    
    public Document getDocument() {
        return document;
    }
    
    public void setDocument(Document document) {
        this.document = document;
    }
    
    public void putAll(HashMap<String, Object> valueMap) {
        this.valueMap.putAll(valueMap);
    }
    
    public String asXML() {
        Document document = null != this.document ? this.document : DocumentHelper.createDocument();
        document.setRootElement(DocumentHelper.createElement(Const.CARRIER_ROOT_NODE));
        // document.setName(Const.CARRIER_ROOT_NODE);
        CarrierUtil.object2Element(valueMap, Const.CARRIER_ROOT_NODE, document.getRootElement());
        try {
            OutputFormat xmlFormat = new OutputFormat();
            StringWriter sw = new StringWriter();
            XMLWriter xmlWriter = new XMLWriter(sw, xmlFormat);
            xmlFormat.setNewlines(true);
            xmlFormat.setIndent(true);
            xmlFormat.setIndent("    ");
            xmlWriter.write(document);
            return sw.toString();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        return null == valueMap ? null : valueMap.toString();
    }
}
