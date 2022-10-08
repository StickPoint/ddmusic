package com.stickpoint.ddmusic.router;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fntp
 * @version v0.0.1
 * @date 2022/9/11
 * @description
 */
public class ApplicationPageLoader {

    private static int loadingStatus;

    static {
        // 内部初始化
        initApplicationPageLoader();
    }

    /**
     * 页面缓存器
     */
    public static Map<Class<?>, URL> pageLoader;

    /**
     * JavaFX-Application应用页面加载器
     */
    private static void initApplicationPageLoader(){
        // 开始加载缓存
        loadingStatus = 0;
        // 初始化页面路由装载器
        pageLoader = new ConcurrentHashMap<>(200);
        // 装载页面路由
        pageLoader.put(PageEnums.HOMEPAGE.getPageType(), PageEnums.HOMEPAGE.getPageSource());
        // 初始化完成后设置当前缓存状态
        loadingStatus = 1;
    }

    /**
     * 根据页面UI的实现类获得页面的URI
     * @param pageType 页面实现类类型
     * @return 返回一个对应页面的全路径
     */
    public static URL getPage(Class<? extends Stage> pageType){
        if (Objects.isNull(pageType)) {
            // 传递的pageName是null
//            log.error("传递的pageType是null，存在空指针~");
            return null;
        }
//        log.info("传递了一个pageType参数~");
        return pageLoader.get(pageType);
    }

    /**
     * 返回当前页面路由装载器初始化的最终结果
     * @return 返回一个初始化的最终结果
     */
    public static boolean loaderInitFinished(){
        return loadingStatus == 1;
    }
}
