package com.stickpoint.ddmusic.common.factory;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 单例工厂
 * @author fntp
 * @since 2023/6/27
 */
@SuppressWarnings("unused")
public class SingletonFactory {
    /**
     * 私有化构造单例
     */
    private SingletonFactory() {
        throw new IllegalStateException("Utility class");
    }

    @SuppressWarnings("rawtypes")
    private static final Map<Class,Object> INSTANCE = new ConcurrentHashMap<>();

    @SuppressWarnings("rawtypes")
    private static final Map<Class,WeakReference<Object>> WEAK_REFERENCE_INSTANCE = new ConcurrentHashMap<>();

    /**
     * 创建可不被回收的单例模式,当没有对象引用，单例对象将被gc掉
     * @param className 类名称
     * @return 返回一个弱引用对象
     * InstantiationException 实例化一个对象时出现了问题
     * IllegalAccessException 访问一个类或者其成员时出现了权限问题
     */
    @SuppressWarnings("unchecked")
    public static <E> E getInstace(Class<E> className){
        Object instance = INSTANCE.get(className);
        if(instance==null){
            synchronized (SingletonFactory.class) {
                instance = INSTANCE.get(className);
                if(instance==null){
                    try {
                        instance = className.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    INSTANCE.put(className,instance);
                }
            }
        }
        return (E)instance;
    }

    /**
     * 创建可回收的单例模式,当没有对象引用，单例对象将被gc掉
     *
     * @param className the class name
     * @return
     *  InstantiationException
     *  IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    public static <E> E getWeakInstace(Class<E> className) {
        WeakReference<Object> reference = WEAK_REFERENCE_INSTANCE.get(className);
        Object instance =reference==null?null:reference.get();
        if(instance==null){
            synchronized (SingletonFactory.class) {
                reference = WEAK_REFERENCE_INSTANCE.get(className);
                instance =reference==null?null:reference.get();
                if(instance==null){
                    try {
                        instance = className.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    WEAK_REFERENCE_INSTANCE.put(className,new WeakReference<>(instance));
                }
            }
        }
        return (E)instance;
    }
}
