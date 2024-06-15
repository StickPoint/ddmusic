package com.stickpoint.ddmusic.page.component;

import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.common.service.impl.NetEasyMusicServiceImpl;
import com.stickpoint.ddmusic.page.controller.MusicControlController;
import com.stickpoint.ddmusic.page.controller.MusicOptionsController;
import com.stickpoint.ddmusic.page.controller.PlayListDetailController;
import com.stickpoint.ddmusic.page.controller.PlayerComponentController;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author fntp
 * @since 2024/3/21
 */
public class DdMusicTableCell extends TableCell<AbstractDdMusicEntity,AnchorPane> {

    /**
     * 日志工具
     */
    private static final Logger log = LoggerFactory.getLogger(DdMusicTableCell.class);

    public void updateIndex(AnchorPane item, boolean empty) {
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
            CompletableFuture.runAsync(() -> musicControlController.startOrPausePlay(SystemCache.INNER_PLAYER_CACHE.get("player")));
        });
    }

    /**
     * 构建AnchorPane
     * @return 返回一个构建完成的AnchorPane
     */
    private MusicOptionsController createAnchorPane() {
        try {
            FXMLLoader musicOptionsFxmlLoader = new FXMLLoader(PageEnums.MUSIC_SEARCH_RESULT_OPTIONS.getPageSource());
            musicOptionsFxmlLoader.load();
            WeakReference<MusicOptionsController> weakRef = new WeakReference<>(musicOptionsFxmlLoader.getController());
            return weakRef.get();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new DdmusicException(DdMusicExceptionEnums.FAILED);
        }
    }

}
