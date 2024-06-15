package com.stickpoint.ddmusic.page.component;

import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * @author fntp
 * @since 2024/3/22
 */
public class DdMusicTableFactory {

    private Callback<TableColumn<AbstractDdMusicEntity, AnchorPane>, TableCell<AbstractDdMusicEntity,AnchorPane>> cell;

    public Callback<TableColumn<AbstractDdMusicEntity, AnchorPane>, TableCell<AbstractDdMusicEntity,AnchorPane>> getCell() {
        return null;
    }

}
