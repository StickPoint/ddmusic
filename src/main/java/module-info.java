open module com.stickpoint.ddmusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires AnimateFX;
    requires javafx.graphics;
    requires javafx.media;
    requires rxcontrols;
	requires javafx.base;
	requires java.sql;
	requires org.apache.derby.commons;
	requires org.apache.derby.engine;
    exports com.stickpoint.ddmusic;
    exports com.stickpoint.ddmusic.page;
    exports com.stickpoint.ddmusic.page.controller;
    exports com.stickpoint.ddmusic.page.component;
}