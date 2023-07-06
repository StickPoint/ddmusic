package com.stickpoint.ddmusic.page.controller;

import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;
import com.stickpoint.ddmusic.common.factory.SingletonFactory;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.service.impl.NetEasyMusicServiceImpl;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author puye(0303)
 * @since 2023/6/29
 */
public class PlayListDetailController {

    /**
     * 日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(PlayListDetailController.class);

    @FXML
    public TableView<AbstractDdMusicEntity> myTable;
    /**
     * 后续使用的过程中，将会entity的基本数据类型全部换成支持双向绑定的Properties类型
     */
    @FXML
    public TableColumn<AbstractDdMusicEntity,String> ddNumber;
    @FXML
    public TableColumn<AbstractDdMusicEntity,String> ddTitle;
    @FXML
    public TableColumn<AbstractDdMusicEntity,String> ddArtists;
    @FXML
    public TableColumn<AbstractDdMusicEntity,String> ddTimes;
    @FXML
    public TableColumn<AbstractDdMusicEntity,String> ddAlbum;
    @FXML
    public TableColumn<AbstractDdMusicEntity, AnchorPane> options;
    @FXML
    public ImageView background;
    /**
     * 背景图片
     */
    public Image image;

    public GaussianBlur gaussianBlur;

    public Rectangle2D rectangle2D;

    public FXMLLoader musicOptionsFxmlLoader;

    /**
     * 初始化数据
     * @param listData 传入一个list集合数据
     */
    public void initTableData (List<? extends AbstractDdMusicEntity> listData) {
        myTable.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0;");
        myTable.lookup(".scroll-bar:vertical").setVisible(false);
        myTable.lookup(".scroll-bar:horizontal").setVisible(false);
        myTable.getItems().clear();
        initBackground();
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
        options.setCellFactory(column-> new TableCell<>() {
            /**
             * 按钮设置监听
             * @param controller 控制器
             */
            private void setOnListeners(MusicOptionsController controller) {
                // 监听下载事件
                controller.download.setOnMouseClicked(event -> {
                    AbstractDdMusicEntity abstractDdMusicEntity = getTableView().getItems().get(getIndex());
                    // 获取文件下载地址
                    String musicPlayUrl = NetEasyMusicServiceImpl.getInstance().getMusicPlayUrl(abstractDdMusicEntity.getDdId());
                    log.info(musicPlayUrl);
                    // 执行文件下载

                });
                // 监听收藏事件
                controller.favorite.setOnMouseClicked(event -> {
                    AbstractDdMusicEntity abstractDdMusicEntity = getTableView().getItems().get(getIndex());
                    log.info(abstractDdMusicEntity.toString());
                });
                // 监听添加到歌单事件
                controller.addToList.setOnMouseClicked(event -> {
                    AbstractDdMusicEntity abstractDdMusicEntity = getTableView().getItems().get(getIndex());
                    log.info(abstractDdMusicEntity.toString());
                });
                // 监听播放事件
                controller.play.setOnMouseClicked(event -> {
                    AbstractDdMusicEntity abstractDdMusicEntity = getTableView().getItems().get(getIndex());
                    log.info("当前播放的音乐对象是：{}",abstractDdMusicEntity);
                    FXMLLoader playerComponentLoader = SystemCache.PAGE_MAP.get(PageEnums.PLAYER_COMPONENT.getRouterId());
                    PlayerComponentController componentController = playerComponentLoader.getController();
                    // 先获得播放链接
                    String musicPlayUrl = NetEasyMusicServiceImpl.getInstance().getMusicPlayUrl(abstractDdMusicEntity.getDdId());
                    // 再获得歌词内容
                    String musicLrcContent = NetEasyMusicServiceImpl.getInstance().getMusicLrcContent(abstractDdMusicEntity.getDdId());
                    // 然后准备播放
                    componentController.prepareMusic(abstractDdMusicEntity, musicPlayUrl, musicLrcContent);
                    // 调用播放
                    FXMLLoader musicControlLoader = SystemCache.PAGE_MAP.get(PageEnums.MUSIC_CONTROL.getRouterId());
                    MusicControlController musicControlController = musicControlLoader.getController();
                    CompletableFuture.runAsync(musicControlController::startOrPausePlay);
                });
            }

            @Override
            protected void updateItem(AnchorPane item, boolean empty) {
                super.updateItem(item, empty);
                WeakReference<MusicOptionsController> weakController = new WeakReference<>(createAnchorPane());
                if (!empty || item == null) {
                    MusicOptionsController controller = weakController.get();
                    if (Objects.nonNull(controller)) {
                        AnchorPane anchorPane = controller.optionPad;
                        setOnListeners(controller);
                        setGraphic(anchorPane);
                    }
                } else {
                    setGraphic(null);
                }
            }
        });
        options.setPrefWidth(140);
        items.addAll(listData);
    }

    /**
     * 构建AnchorPane
     * @return 返回一个构建完成的AnchorPane
     */
    private MusicOptionsController createAnchorPane() {
        try {
            musicOptionsFxmlLoader = null;
            musicOptionsFxmlLoader = new FXMLLoader(PageEnums.MUSIC_SEARCH_RESULT_OPTIONS.getPageSource());
            musicOptionsFxmlLoader.load();
            WeakReference<MusicOptionsController> weakRef = new WeakReference<>(musicOptionsFxmlLoader.getController());
            return weakRef.get();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new DdmusicException(DdMusicExceptionEnums.FAILED);
        }
    }

    /**
     * 初始化歌单背景图片效果
     * TODO 这个后续需要更改
     */
    private void initBackground() {
        // 先GC
        image = null;
        // 先去设置毛玻璃效果
        gaussianBlur = null;
        gaussianBlur = SingletonFactory.getWeakInstace(GaussianBlur.class);
        image = new Image(SystemCache.CURRENT_PLAY_LIST_COVER_URL.get(0));
        background.setImage(image);
        background.setFitWidth(750);
        background.setFitHeight(150);
        background.setPreserveRatio(false);
        background.setEffect(gaussianBlur);
        // 裁剪图片以适应ImageView的长宽比
        double imageRatio = image.getWidth() / image.getHeight();
        double viewRatio = background.getFitWidth() / background.getFitHeight();
        if (imageRatio > viewRatio) {
            double newHeight = background.getFitWidth() / imageRatio;
            double offsetY = (background.getFitHeight() - newHeight) / 2;
            rectangle2D = null;
            rectangle2D = new Rectangle2D(0, offsetY, background.getFitWidth(), newHeight);
            background.setViewport(rectangle2D);
        } else {
            double newWidth = background.getFitHeight() * imageRatio;
            double offsetX = (background.getFitWidth() - newWidth) / 2;
            rectangle2D = null;
            rectangle2D = new Rectangle2D(offsetX, 0, newWidth, background.getFitHeight());
            background.setViewport(rectangle2D);
        }
    }

}
