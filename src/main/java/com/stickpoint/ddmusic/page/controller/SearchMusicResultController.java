package com.stickpoint.ddmusic.page.controller;
import com.stickpoint.ddmusic.common.model.entity.DdMusicEntity;
import com.stickpoint.ddmusic.common.model.neteasy.NetEasyMusicEntity;
import com.stickpoint.ddmusic.common.model.vo.RequestBaseInfoVO;
import com.stickpoint.ddmusic.common.service.IMusicService;
import com.stickpoint.ddmusic.common.service.impl.NetEasyMusicServiceImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.Objects;

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
    public TableColumn searchIndex;

    private List<NetEasyMusicEntity> musicList;

    private static final IMusicService musicService = new NetEasyMusicServiceImpl();

    @FXML
    private TableView<DdMusicEntity> myTable;

    @FXML
    public void initialize() {
        initData();
        initTableData();
    }

    public void initData() {
        // 获得数据
        if (Objects.isNull(musicList)) {
            RequestBaseInfoVO requestBaseInfoVO = new RequestBaseInfoVO();
            requestBaseInfoVO.setSearchKey("夜间巡航");
            // 获得搜索后的音乐列表
            List<? extends DdMusicEntity> searchMusicList = musicService.searchMusicList(requestBaseInfoVO);
            // 启动新的UI线程 刷新音乐列表UI

        }
    }

    /** wangyiyunyinyuebofang
     * 时间算法 时间分割算法 将网易云的隐藏歌曲时间进行提取然后计算时分秒
     * 最大单位是时 其次是分 其次是秒
     * @param sourceData
     * @return
     */
    private static String getTimes(String sourceData) {
        long playTime = Long.parseLong(sourceData);
        long second = playTime / 1000;
        long hour=second/3600;
        long minite=second%3600/60;
        long sec=second%60;
        StringBuilder timeResult = new StringBuilder();
        if (hour>0){
            timeResult.append(hour).append("：").append(minite).append("：").append(sec);
        }else if (minite>0){
            if (minite>=10){
                if (sec>=10){
                    timeResult.append(minite).append("：").append(sec);
                }else {
                    timeResult.append(minite).append("：0").append(sec);
                }
            }else {
                if (sec>=10){
                    timeResult.append("0").append(minite).append("：").append(sec);
                }else {
                    timeResult.append("0").append(minite).append("：0").append(sec);
                }
            }
        }else if (sec>0){
            if (sec>=10) {
                timeResult.append("00：").append(sec);
            }else {
                timeResult.append("00：0").append(sec);
            }
        }
        return timeResult.toString();
    }

    public void initTableData () {
        ObservableList<DdMusicEntity> items = myTable.getItems();
        ddAlbum.setCellValueFactory(new PropertyValueFactory<>("ddAlbum"));
        ddArtists.setCellValueFactory(new PropertyValueFactory<>("ddArtists"));
        ddNumber.setCellValueFactory(new PropertyValueFactory<>("ddNumber"));
        ddTimes.setCellValueFactory(new PropertyValueFactory<>("ddTimes"));
        ddTitle.setCellValueFactory(new PropertyValueFactory<>("ddTitle"));
        items.addAll(musicList);
    }
}
