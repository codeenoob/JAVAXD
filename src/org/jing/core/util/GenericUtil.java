package org.jing.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.jing.core.lang.ExceptionHandler;
import org.jing.core.lang.JingException;

public class GenericUtil {

    /**
     * shouldn't be empty. <br>
     */
    public static final int COMPARE_STRING_NOT_NULL = 2;

    /**
     * Description: If String Is Empty, Get Default String <br>
     * 
     * @author bks <br>
     * @param string <br>
     * @param defaultString <br>
     * @return <br>
     */
    public static String ifEmpty(String string, String defaultString) {
        if (null == string || string.length() == 0) {
            return defaultString;
        }
        else {
            return string;
        }
    }

    /**
     * Description: If String Is Empty, Get Empty String <br>
     * 
     * @author bks <br>
     * @param string <br>
     * @return <br>
     */
    public static String ifEmpty(String string) {
        if (null == string) {
            return "";
        }
        else {
            return string;
        }
    }

    /**
     * Description: If String Is Not Empty, Get Default String. <br> 
     *  
     * @author bks <br>
     * @param string <br>
     * @param defaultString <br>
     * @return <br>
     */ 
    public static String ifNotEmpty(String string, String defaultString) {
        if (null != string && string.length() != 0) {
            return defaultString;
        }
        else {
            return string;
        }
    }
    
    /**
     * Description: If String Is Not Empty, Get Default String. <br> 
     * If String Is Empty, Get EmptyString. <br>
     *  
     * @author bks <br>
     * @param string <br>
     * @param defaultString <br>
     * @param emptyString <br>
     * @return <br>
     */ 
    public static String ifNotEmpty(String string, String defaultString, String emptyString) {
        if (null != string && string.length() != 0) {
            return defaultString;
        }
        else {
            return emptyString;
        }
    }
    
    /**
     * Description: String Mapping. <br> 
     * If key doen't exist in map, return default String. <br>
     * If need case sensitive, set ignoreCase as false. <br>
     * It may return a null String, plz be careful. <br>
     * 
     * @author bks <br>
     * @param map <br>
     * @param key <br>
     * @param defaultString <br>
     * @param ignoreCase <br>
     * @return <br>
     */ 
    public static String stringMapping(HashMap<String, String> map, String key, String defaultString, boolean ignoreCase) {
        String retString = null;
        boolean findFlag = false;
        if (null == map || null == key) {
        }
        else {
            if (!ignoreCase) {
                if (map.containsKey(key)) {
                    retString = map.get(key);
                    findFlag = true;
                }
            }
            else {
                for (Entry<String, String> entry : map.entrySet()) {
                    if (key.equalsIgnoreCase(entry.getKey())) {
                        retString = entry.getValue();
                        findFlag = true;
                        break;
                    }
                }
            }
        }
        if (!findFlag) {
            retString = defaultString;
        }
        return retString;
    }

    /**
     * Description: Initialize A HashMap With Object Array. <br> 
     * Two of array must be the same length. If not, return a empty HashMap. <br>
     * The return HashMap won't be null, at least return a empty HashMap. <br>
     *  
     * @author bks <br>
     * @param <A> <br>
     * @param <B> <br>
     * @param key <br>
     * @param value <br>
     * @return <br>
     */ 
    public static <A, B> HashMap<A, B> initHashMap(A[] key, B[] value) {
        HashMap<A, B> retMap = new HashMap<>();
        if (null != key && null != value) {
            if (key.length == value.length) {
                int size = key.length;
                for (int i = 0; i < size; i++) {
                    retMap.put(key[i], value[i]);
                }
            }
        }
        return retMap;
    }

    /**
     * Description: Initialize A HashMap With One Pair. <br> 
     * The return HashMap won't be null, at least return a empty HashMap. <br>
     *  
     * @author bks <br>
     * @param <A> <br>
     * @param <B> <br>
     * @param key <br>
     * @param value <br>
     * @return <br>
     */ 
    public static <A, B> HashMap<A, B> initHashMap(A key, B value) {
        HashMap<A, B> retMap = new HashMap<>();
        retMap.put(key, value);
        return retMap;
    }
    
    /**
     * Description: Initialize An List With Array. <br> 
     *  
     * @author bks <br>
     * @param <E> <br>
     * @param elements <br>
     * @return <br>
     */ 
    public static <E> List<E> initList(E[] elements) {
        ArrayList<E> retList = new ArrayList<>();
        if (null != elements) {
            int size = elements.length;
            for (int i = 0; i < size; i++) {
                retList.add(elements[i]);
            }
        }
        return retList;
    }
    
    /**
     * Description: Get The First Value By Key. <br> 
     * It may return a null String, plz be careful. <br>
     * 
     * @author bks <br>
     * @param list <br>
     * @param key <br>
     * @return <br>
     */ 
    public static String getFirst(List<HashMap<String, String>> list, String key) {
        String retStr = null;
        int count = null == list ? 0 : list.size();
        for (int i = 0; i < count; i++) {
            HashMap<String, String> tempMap = list.get(i);
            if (tempMap.containsKey(key)) {
                retStr = tempMap.get(key);
                break;
            }
        }
        return retStr;
    }

    /**
     * Description: Remove All Space[" "], Line["-"] And UnderLine["_"]. <br> 
     *  
     * @author bks <br>
     * @param string <br>
     * @return <br>
     */ 
    public static String removeSpaceAndLine(String string) {
        String retString = ifEmpty(string).replaceAll("[\\s|\\-|_]", "");
        return retString;
    }

    /**
     * Description: Compare String. <br> 
     * For option: <br>
     * 0: ignore Space[" "], Line["-"] And UnderLine["_"]. <br>
     * 1: ignore Space[" "], Line["-"] ,UnderLine["_"] And Case. <br>
     * 2: shouldn't be empty. <br>
     * Else: Common Comparation. <br>
     * 
     * @author bks <br>
     * @param string1 <br>
     * @param string2 <br>
     * @param options <br>
     * @return <br>
     */ 
    public static boolean compareString(String string1, String string2, int options) {
        switch (options) {
            // ignore Space[" "], Line["-"] And UnderLine["_"].
            case 0:
                return removeSpaceAndLine(string1).equals(removeSpaceAndLine(string2));
            // ignore Space[" "], Line["-"] ,UnderLine["_"] And Case.
            case 1:
                return removeSpaceAndLine(string1).equalsIgnoreCase(removeSpaceAndLine(string2));
            // shouldn't be empty.
            case COMPARE_STRING_NOT_NULL:
                return null != string1 && null != string2 && string1.equals(string2);
            // Common.
            default: 
                return ifEmpty(string1).equals(string2);
        }
    }

    /**
     * Description: Transfer String To Integer Without Exception. <br>
     * If failed, it will return defaultValue. If defaultValue was not given, it will return 0. <br>
     *  
     * @author bks <br>
     * @param string <br>
     * @param defaultInteger <br>
     * @return <br>
     */ 
    public static int parseInteger(String string, int defaultInteger) {
        int defaultInt = defaultInteger;
        try {
            defaultInt = Integer.parseInt(string);
            return defaultInt;
        }
        catch (Exception e) {
            return defaultInt;
        }
    }
    
    /**
     * Description: Transfer String To Integer Without Exception. <br>
     * If failed, it will return 0. <br>
     *  
     * @author bks <br>
     * @param string <br>
     * @return <br>
     */ 
    public static int parseInteger(String string) {
        return parseInteger(string, 0);
    }
    
    /**
     * Description: Transfer Object To String Without Exception<br> 
     *  
     * @author bks <br>
     * @param object <br>
     * @return <br>
     */ 
    public static String parseString(Object object) {
        String outPutString = "";
        try {
            outPutString = (String) object;
        }
        catch (Exception e1) {
            try {
                outPutString = String.valueOf(object);
            }
            catch (Exception e2) {
                try {
                    outPutString = object.toString();
                }
                catch (Exception e3) {
                    outPutString = "";
                }
            }
        }
        return outPutString;
    }

    public static boolean isEmpty(String string) {
        return null == string || string.length() == 0;
    }
    
    public static boolean isEmpty(List<?> list) {
        return null == list || list.size() == 0;
    }
    
    public static int countList(List<?> list) {
        return null == list ? 0 : list.size();
    }

    public static <T> int countArray(T[] array) {
        return null == array ? 0 : array.length;
    }
    
    public static void throwNullException(Object object) throws JingException {
        if (null == object) {
            throw new JingException();
        }
    }
    
    public static void throwNullException(Object object, String errorCode, String errorDescription) throws JingException {
        if (null == object) {
            throw new JingException(errorCode, errorDescription);
        }
    }
    
    public static void throwNullException(Object object, String msg) throws JingException {
        if (null == object) {
            throw new JingException(msg);
        }
    }

    public static HashMap<String, String> clone(HashMap<String, String> hashMap) throws JingException {
        throwNullException(hashMap, "JING-UTIL-0001", "Failed To Clone A Null HashMap");
        return (HashMap<String, String>) hashMap.clone();
    }

    public static <K, V> String getString(HashMap<K, V> hashMap, K key) {
        return parseString(hashMap.get(key));
    }

    public static boolean isPrimitive(Object object) {
        try {
            return ((Class<?>) object.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    public static void setByForce(Object object, String name, Object value) throws JingException {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        }
        catch (Exception e) {
            ExceptionHandler.publish("UTIL-00000",
                new StringBuilder("Failed to set the value [").append(name).append("] of ").append(object.getClass().getName()).toString());
        }
    }

    public static Object getByForce(Object object, String name) throws JingException {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        }
        catch (Exception e) {
            ExceptionHandler.publish("UTIL-00001",
                new StringBuilder("Failed to get the value [").append(name).append("] of ").append(object.getClass().getName()).toString());
        }
        return null;
    }


    public static void batchSetByForce(Object object, String[] names, Object[] values) throws JingException {
        try {
            int count = names.length;
            for (int i$ = 0; i$ < count; i$++) {
                Field field$ = object.getClass().getDeclaredField(names[i$]);
                field$.setAccessible(true);
                field$.set(object, values[i$]);
            }
        }
        catch (Exception e) {
            ExceptionHandler.publish("UTIL-00000",
                new StringBuilder("Failed to set the value [").append(Arrays.toString(names)).append("] of ").append(object.getClass().getName()).toString());
        }
    }
}