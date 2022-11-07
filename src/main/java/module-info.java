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
    // 下面的依赖全是javafx依赖
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.base;
    requires java.sql;
    exports com.stickpoint.ddmusic;
}