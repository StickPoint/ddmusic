import com.stickpoint.ddmusic.common.config.DdmusicSpiMonitor;
import com.stickpoint.ddmusic.common.config.DdMusicHttpConfig;
open module com.stickpoint.ddmusic {
    uses DdmusicSpiMonitor;
    // 第三方依赖
    requires animatefx;
    requires rxcontrols;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;
    requires com.google.gson;
    requires org.kordamp.ikonli.antdesignicons;
    requires org.kordamp.ikonli.javafx;
    // 下面的依赖全是javafx依赖
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.base;
    requires java.sql;
    // 配置SPI机制下的HttpConfig封装
    provides DdmusicSpiMonitor  with DdMusicHttpConfig;
    exports com.stickpoint.ddmusic;
    exports com.stickpoint.ddmusic.common.config;
}
