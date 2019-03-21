package org.jing.core.lang;

import org.jing.core.lang.annotation.ServiceCode;
import org.jing.core.lang.itf.JInit;
import org.jing.core.lang.itf.JService;
import org.jing.core.util.CarrierUtil;
import org.jing.core.util.ClassUtil;
import org.jing.core.util.FileUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-03-21 <br>
 */
@SuppressWarnings("unchecked")
public class PathMapping implements JInit {
    @Override
    public void init() throws JingException {
        Carrier carrier =
            CarrierUtil
                .string2Carrier(FileUtil.readResource(Configuration.getInstance().getProperty("PathMapping"), false));
        int count = carrier.getCount("service");
        Set<Class<?>> set = new HashSet<>();
        for (int i$ = 0; i$ < count; i$++) {
            String path$ = carrier.getString(i$, "service");
            set.addAll(ClassUtil.getClassByPackage(path$));
        }
        for (Class<?> clazz$ : set) {
            // JService.
            dealService(clazz$);
        }
    }

    private void dealService(Class<?> clazz) throws JingException {
        ServiceCode serviceCode = clazz.getAnnotation(ServiceCode.class);
        if (null != serviceCode) {
            String[] serviceCodes = serviceCode.value();
            for (String serviceCode$ : serviceCodes) {
                if (null != serviceCode$ && serviceCode$.length() != 0) {
                    if (serviceMap.containsKey(serviceCode$)) {
                        ExceptionHandler.publish("PATH-0000", "Duplicate Path Mapping Configuration: " + serviceCode$);
                    }
                    else {
                        try {
                            serviceMap.put(serviceCode$, (Class<? super JService>) clazz);
                        }
                        catch (Exception e) {
                            ExceptionHandler.publish("PATH-0001", "Invalid Implements: " + clazz.getName());
                        }
                    }
                }
            }
        }
    }

    private static HashMap<String, Class<? super JService>> serviceMap = new HashMap<>();

    public static Class<? super JService> mappingService(String serviceCode) {
        return serviceMap.get(serviceCode);
    }
}
