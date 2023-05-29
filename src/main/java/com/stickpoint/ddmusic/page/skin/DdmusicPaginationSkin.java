package com.stickpoint.ddmusic.page.skin;
import com.stickpoint.ddmusic.page.component.DdmusicPagination;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;
import java.util.concurrent.Callable;

/**
 * @author 组件作者 LiChenFei
 * Gitee <a href="https://gitee.com/lichenfei_fei/chenfei-fxui">组件作者主页</a>
 * 本项目只对原项目提出一些修改以及编码规范，组件版权归原作者所有，学习参考使用，已注明出处
 */
@SuppressWarnings("unused")
public class DdmusicPaginationSkin extends SkinBase<DdmusicPagination> {
    /**
     * 第一页
     */
    private final Label first;
    /**
     * 最后一页
     */
    private final Label last;
    /**
     * 上一个
     */
    private final Label prev;
    /**
     * 下一个
     */
    private final Label next;
    /**
     * 快速上一页 五页
     */
    private final Label quickPrev;
    /**
     * 快速下一页
     */
    private final Label quickNext;
    /**
     * 分页Box
     */
    private final HBox pagerBox;

    public DdmusicPaginationSkin(DdmusicPagination control) {
        super(control);
        prev = getPaginationLabel(0);
        prev.setGraphic(FontIcon.of(AntDesignIconsOutlined.LEFT));
        next = getPaginationLabel(0);
        next.setGraphic(FontIcon.of(AntDesignIconsOutlined.RIGHT));
        quickPrev = getPaginationLabel(0);
        quickNext = getPaginationLabel(0);
        pagerBox = new HBox();
        first = getPaginationLabel(1);
        last = getPaginationLabel(1);
        last.textProperty().bind(control.pageCountProperty().asString());
        HBox container = new HBox(prev, first, quickPrev, pagerBox, quickNext, last, next);
        //显示隐藏
        last.managedProperty().bind(control.pageCountProperty().greaterThan(1));
        last.visibleProperty().bind(last.managedProperty());
        pagerBox.managedProperty().bind(control.pageCountProperty().greaterThan(2));
        pagerBox.visibleProperty().bind(pagerBox.managedProperty());
        // styleClass
        container.getStyleClass().add("container");
        pagerBox.getStyleClass().add("pager-box");
        getChildren().add(container);
        initEvent(control);
    }

    private void initEvent(DdmusicPagination control) {
        quickPrev.graphicProperty().bind(Bindings.
                createObjectBinding((Callable<Node>) () ->
                                FontIcon.of(quickPrev.isHover() ? AntDesignIconsOutlined.DOUBLE_LEFT : AntDesignIconsOutlined.ELLIPSIS),
                        quickPrev.hoverProperty()));
        quickNext.graphicProperty().bind(Bindings.
                createObjectBinding((Callable<Node>) () ->
                                FontIcon.of(quickNext.isHover() ? AntDesignIconsOutlined.DOUBLE_RIGHT : AntDesignIconsOutlined.ELLIPSIS),
                        quickNext.hoverProperty()));
        // 是否显示pres，nexts按钮
        quickPrev.managedProperty().bind(control.pageIndexProperty().greaterThan(control.pagerCountProperty().divide(2).add(1)).and(control.pageCountProperty().greaterThan(control.pagerCountProperty())));
        quickPrev.visibleProperty().bind(quickPrev.managedProperty());
        quickNext.managedProperty().bind(control.pageCountProperty().subtract(control.pageIndexProperty()).greaterThan(control.pagerCountProperty().divide(2)).and(control.pageCountProperty().greaterThan(control.pagerCountProperty())));
        quickNext.visibleProperty().bind(quickNext.managedProperty());
        // 按钮数量发生变化
        pagerChanged(control);
        // 更新数字按钮
        control.pageCountProperty().addListener((observable, oldValue, newValue) -> pagerChanged(control));
        // 监听当前页码发生变化
        activeChanged(control.getPageIndex());
        // 更新伪类
        control.pageIndexProperty().addListener((observable, oldValue, newValue) -> activeChanged(newValue.intValue()));
        // 首页，末页点击事件
        first.setOnMouseClicked(event -> control.setPageIndex(1));
        last.setOnMouseClicked(event -> control.setPageIndex(control.getPageCount()));
        // 上一页，下一页，快速上一页，快速下一页
        prev.setOnMouseClicked(event -> control.setPageIndex(control.getPageIndex() > 1 ? control.getPageIndex() - 1 : control.getPageIndex()));
        next.setOnMouseClicked(event -> control.setPageIndex(control.getPageIndex() < control.getPageCount() ? control.getPageIndex() + 1 : control.getPageIndex()));
        quickPrev.setOnMouseClicked(event -> control.setPageIndex(Math.max(control.getPageIndex() - 5, 1)));
        quickNext.setOnMouseClicked(event -> control.setPageIndex(Math.min(control.getPageIndex() + 5, control.getPageCount())));
    }

    /**
     * 初始化按钮
     */
    private void pagerChanged(DdmusicPagination c) {
        int pageCount = c.getPageCount();
        if (pageCount < 1) {
            pageCount = 1;
        }
        int pagerCount = Math.min(pageCount, c.getPagerCount());
        ObservableList<Node> children = pagerBox.getChildren();
        children.clear();
        for (int i = 2; i < pagerCount; i++) {
            children.add(getPaginationLabel(i));
        }
    }

    /**
     * 动态添加 active 伪类，更改页码的css样式
     */

    private static final PseudoClass ACTIVE_CLASS_SELECTED = PseudoClass.getPseudoClass("active");

    private void activeChanged(int pageIndex) {
        DdmusicPagination c = getSkinnable();
        first.pseudoClassStateChanged(ACTIVE_CLASS_SELECTED, pageIndex == 1);
        last.pseudoClassStateChanged(ACTIVE_CLASS_SELECTED, pageIndex == c.getPageCount());
        ObservableList<Node> children = pagerBox.getChildren();
        int size = children.size();
        if (quickPrev.isManaged() && quickNext.isManaged()) {
            int i = pageIndex - c.getPagerCount() / 2 + 1;
            for (Node child : children) {
                Label childLabel = (Label) child;
                childLabel.setText(String.valueOf(i));
                childLabel.pseudoClassStateChanged(ACTIVE_CLASS_SELECTED, pageIndex == Integer.parseInt(childLabel.getText()));
                i++;
            }
            return;
        }
        for (int i = 0; i < size; i++) {
            Label childLabelOfIndex = (Label) children.get(i);
            if (!quickPrev.isManaged() && quickNext.isManaged()) {
                childLabelOfIndex.setText(String.valueOf(i + 2));
            }
            if (quickPrev.isManaged() && !quickNext.isManaged()) {
                childLabelOfIndex.setText(String.valueOf(c.getPageCount() - (size - i)));
            }
            childLabelOfIndex.pseudoClassStateChanged(ACTIVE_CLASS_SELECTED, pageIndex == Integer.parseInt(childLabelOfIndex.getText()));
        }
    }

    private Label getPaginationLabel(int i) {
        DdmusicPagination c = getSkinnable();
        Label paginationLabel = new Label(i == 0 ? null : String.valueOf(i));
        paginationLabel.getStyleClass().add("pagination-label");
        // 数字按钮点击事件
        if (i != 0) {
            paginationLabel.setOnMouseClicked(event -> {
                Label childLabelWithSource = (Label) event.getSource();
                c.setPageIndex(Integer.parseInt(childLabelWithSource.getText()));
            });
        }
        return paginationLabel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(final double x, final double y, final double w, final double h) {
        super.layoutChildren(x, y, w, h);
    }
}
