package org.jing.core.util;

import org.apache.log4j.Logger;
import org.jing.core.lang.ExceptionHandler;
import org.jing.core.lang.JingException;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static java.lang.Thread.currentThread;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-03-21 <br>
 */
@SuppressWarnings({ "WeakerAccess", "unused" })
public class ClassUtil {
    private static Logger logger = Logger.getLogger(ClassUtil.class);

    private static ClassLoader defaultClassLoader;

    private static ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

    public static ClassLoader[] getClassLoader(Class<?> clazz) {
        return new ClassLoader[] { null, defaultClassLoader,
            currentThread().getContextClassLoader(), ClassUtil.class.getClassLoader(), systemClassLoader
        };
    }

    public static Class<?> loadClass(String fullClassName, ClassLoader loader) throws JingException {
        try {
            if (null == loader || null == fullClassName || fullClassName.length() == 0) {
                return null;
            }
            else {
                return loader.loadClass(fullClassName);
            }
        }
        catch (ClassNotFoundException e) {
            ExceptionHandler.publish("UTIL-CLASS-0000",
                new StringBuilder("Failed to load class [fullClassName: ").append(fullClassName).append("][loader: ")
                    .append(loader.toString()).append("]").toString(), e);
        }
        return null;
    }

    public static Class<?> loadClass(String fullClassName, ClassLoader[] loaders) throws JingException {
        if (null == loaders || null == fullClassName || fullClassName.length() == 0) {
            return null;
        }
        else {
            int count = loaders.length;
            for (ClassLoader loader$ : loaders) {
                if (null != loader$) {
                    Class<?> clazz = loadClass(fullClassName, loader$);
                    if (null != clazz) {
                        return clazz;
                    }
                }
            }
            return null;
        }
    }

    public static Class<?> loadClass(String fullClassName) throws JingException {
        return loadClass(fullClassName, getClassLoader(ClassUtil.class));
    }

    public static void findClassesByFile(String pkgName, String pkgPath, Set<Class<?>> classes) throws JingException {
        // 获取此包的目录 建立一个File
        File dir = new File(pkgPath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        //        File[] dirfiles = dir.listFiles(new FileFilter() {
        //            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
        //            public boolean accept(File file) {
        //                return file.isDirectory()|| file.getName().endsWith(".class");
        //            }
        //        });
        File[] files = dir.listFiles(pathName -> pathName.isDirectory() || pathName.getName().endsWith("class"));

        if (null == files || files.length == 0) {
            return;
        }

        String className;
        Class clz;
        // 循环所有文件
        for (File f : files) {
            // 如果是目录 则继续扫描
            if (f.isDirectory()) {
                findClassesByFile(pkgName + "." + f.getName(), pkgPath + "/" + f.getName(), classes);
                continue;
            }
            // 如果是java类文件 去掉后面的.class 只留下类名
            className = f.getName();
            className = className.substring(0, className.length() - 6);

            //加载类
            clz = loadClass(pkgName + "." + className);
            // 添加到集合中去
            if (clz != null) {
                classes.add(clz);
            }
        }
    }

    public static void findClassesByJar(String pkgName, JarFile jar, Set<Class<?>> classes) throws JingException {
        String pkgDir = pkgName.replace(".", "/");
        // 从此jar包 得到一个枚举类
        Enumeration<JarEntry> entry = jar.entries();

        JarEntry jarEntry;
        String name, className;
        Class<?> clazz;
        // 同样的进行循环迭代
        while (entry.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文
            jarEntry = entry.nextElement();

            name = jarEntry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }

            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
                continue;
            }
            //如果是一个.class文件 而且不是目录
            // 去掉后面的".class" 获取真正的类名
            className = name.substring(0, name.length() - 6);
            //加载类
            clazz = loadClass(className.replace("/", "."));
            // 添加到集合中去
            if (clazz != null) {
                classes.add(clazz);
            }
        }
    }

    public static Set<Class<?>> getClassByPackage(String packagePath) throws JingException {
        //第一个class类的集合
        Set<Class<?>> classes = new HashSet<>();
        // 获取包的名字 并进行替换
        String pkgDirName = packagePath.replace('.', '/');
        ClassLoader[] loaders = getClassLoader(ClassUtil.class);
        try {
            for (ClassLoader loader$ : loaders) {
                if (null == loader$) {
                    continue;
                }
                Enumeration<URL> urls = loader$.getResources(pkgDirName);
                if (null == urls) {
                    continue;
                }
                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    // 得到协议的名称
                    String protocol = url.getProtocol();
                    // 如果是以文件的形式保存在服务器上
                    if ("file".equals(protocol)) {
                        // 获取包的物理路径
                        String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                        // 以文件的方式扫描整个包下的文件 并添加到集合中
                        findClassesByFile(packagePath, filePath, classes);
                    } else if ("jar".equals(protocol)) {
                        // 如果是jar包文件
                        // 获取jar
                        JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        //扫描jar包文件 并添加到集合中
                        findClassesByJar(packagePath, jar, classes);
                    }
                }
            }
        } catch (IOException e) {
            ExceptionHandler.publish("UTIL-CLASS-0001",
                new StringBuilder("Failed to get class [packagePath: ").append(packagePath).toString(), e);
        }

        return classes;
    }
}