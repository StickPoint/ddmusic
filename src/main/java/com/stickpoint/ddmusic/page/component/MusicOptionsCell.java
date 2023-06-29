package com.stickpoint.ddmusic.page.component;
import com.stickpoint.ddmusic.common.cache.SystemCache;
import com.stickpoint.ddmusic.common.enums.DdMusicExceptionEnums;
import com.stickpoint.ddmusic.common.exception.DdmusicException;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;
import com.stickpoint.ddmusic.page.enums.PageEnums;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * @author puye(0303)
 * @since 2023/6/20
 */
public class MusicOptionsCell extends TableCell<AbstractDdMusicEntity, AnchorPane>  {

    /**
     * 顶点音乐日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(MusicOptionsCell.class);

    @Override
    protected void updateItem(AnchorPane item, boolean empty) {
        super.updateItem(item, empty);
        if (empty){
            log.info("bbb");
            setGraphic(createAnchorPane());
        }else{
            log.info("aaaa");
            setGraphic(createAnchorPane());
        }
    }

    private AnchorPane createAnchorPane() {
        FXMLLoader musicOptionsFxmlLoader = new FXMLLoader(PageEnums.MUSIC_SEARCH_RESULT_OPTIONS.getPageSource());
        try {
            musicOptionsFxmlLoader.load();
            AnchorPane root = SystemCache.PAGE_MAP.get(PageEnums.MUSIC_SEARCH_RESULT_OPTIONS.getRouterId()).getRoot();
            if (Objects.isNull(root)) {
                log.info("root is null");
            }
            log.info("列表按钮数据已转载");
            return root;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new DdmusicException(DdMusicExceptionEnums.FAILED);
        }
    }

}
