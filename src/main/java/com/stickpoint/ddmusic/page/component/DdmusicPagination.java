package com.stickpoint.ddmusic.page.component;
import com.stickpoint.ddmusic.common.utils.DdMusicFxUtils;
import com.stickpoint.ddmusic.page.skin.DdmusicPaginationSkin;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Control;
import java.util.Arrays;
import java.util.List;

/**
 * @author 组件作者 LiChenFei
 * Gitee <a href="https://gitee.com/lichenfei_fei/chenfei-fxui">组件作者主页</a>
 * 本项目只对原项目提出一些修改以及编码规范，组件版权归原作者所有，学习参考使用，已注明出处
 */
@SuppressWarnings("unused")
public class DdmusicPagination extends Control {

    private static final String STYLE_SHEET = DdMusicFxUtils.getResourceExternalForm("/css/ddmusic-pagination.css");
    private static final String STYLE_CLASS = "ddmusic-pagination";
    /**
     * 统计页数
     */
    private final IntegerProperty pageCount = new SimpleIntegerProperty(0);
    /**
     * 总页数
     */
    private final IntegerProperty pageIndex = new SimpleIntegerProperty(1);
    /**
     * 当前页
     */
    private final IntegerProperty pagerCount = new SimpleIntegerProperty(7);
    /**
     * 最大按钮数
     */
    public DdmusicPagination() {
        initialize();
    }

    public DdmusicPagination(int pages, int pageIndex) {
        setPageCount(pages);
        setPageIndex(pageIndex);
        initialize();
    }

    public int getPageCount() {
        return pageCount.get();
    }

    public void setPageCount(int pageCount) {
        this.pageCount.set(pageCount);
    }

    public IntegerProperty pageCountProperty() {
        return pageCount;
    }

    public int getPageIndex() {
        return pageIndex.get();
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex.set(pageIndex);
    }

    public IntegerProperty pageIndexProperty() {
        return pageIndex;
    }

    public int getPagerCount() {
        return pagerCount.get();
    }

    private static final List<Integer> PAGER_COUNT_LIST = Arrays.asList(5, 7, 9, 11);

    /**
     * @param pagerCount ： 可用值：[5,7,9,11]。默认:7
     */
    public void setPagerCount(int pagerCount) {
        if (PAGER_COUNT_LIST.contains(pagerCount)) {
            this.pagerCount.set(pagerCount);
        } else {
            this.pagerCount.set(7);
        }
    }

    public IntegerProperty pagerCountProperty() {
        return pagerCount;
    }

    private void initialize() {
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        getStyleClass().add(STYLE_CLASS);
    }

    @Override
    protected DdmusicPaginationSkin createDefaultSkin() {
        return new DdmusicPaginationSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLE_SHEET;
    }

}
