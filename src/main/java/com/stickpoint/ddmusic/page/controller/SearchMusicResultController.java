package com.stickpoint.ddmusic.page.controller;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.page.component.MusicOptionsCell;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * description： TestMusicSearchListController
 *
 * @ClassName ： TestMusicSearchListController
 * @Date 2022/12/12 17：00
 * @Author puye(0303)
 * @PackageName test
 */
public class SearchMusicResultController {
    /**
     * 日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(SearchMusicResultController.class);
    /**
     * 后续使用的过程中，将会entity的基本数据类型全部换成支持双向绑定的Properties类型
     */
    @FXML
    public TableColumn ddNumber;
    @FXML
    public TableColumn ddTitle;
    @FXML
    public TableColumn ddArtists;
    @FXML
    public TableColumn ddTimes;
    @FXML
    public TableColumn ddAlbum;
    @FXML
    public TableColumn options;
    @FXML
    public Label searchedMusicName;
    /**
     * 根容器
     */
    public VBox rootPane;

    @FXML
    private TableView<AbstractDdMusicEntity> myTable;

    @FXML
    public void initialize() {
    }

    /**
     * 初始化数据
     * @param listData 传入一个list集合数据
     */
    public void initTableData (List<? extends AbstractDdMusicEntity> listData) {
        myTable.getItems().clear();
        ObservableList<AbstractDdMusicEntity> items = myTable.getItems();
        // 专辑
        ddAlbum.setCellValueFactory(new PropertyValueFactory<>("ddAlbum"));
        // 艺术家
        ddArtists.setCellValueFactory(new PropertyValueFactory<>("ddArtists"));
        // 顶点编号
        ddNumber.setCellValueFactory(new PropertyValueFactory<>("ddNumber"));
        // 音乐时间时长
        ddTimes.setCellValueFactory(new PropertyValueFactory<>("ddTimes"));
        // 音乐标题
        ddTitle.setCellValueFactory(new PropertyValueFactory<>("ddTitle"));
        // 操作

        items.addAll(listData);
    }
}
