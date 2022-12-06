open module com.stickpoint.ddmusic {
    // 第三方依赖
    requires AnimateFX;
    requires rxcontrols;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;
    requires org.apache.derby.commons;
    requires org.apache.derby.engine;
    requires com.google.gson;
    requires okhttps;
    requires okhttps.gson;
    requires data.core;
    // 下面的依赖全是javafx依赖
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.base;
    requires java.sql;
    // 配置SPI机制下的HttpConfig封装
    provides com.ejlchina.okhttps.Config with com.stickpoint.ddmusic.common.config.DdMusicHttpConfig;
    exports com.stickpoint.ddmusic;
}