package com.stickpoint.ddmusic.common.model.table;

import javafx.scene.control.Button;

/**
 * @author fntp
 * @since 2023/5/26
 */
public class MusicOperationCellData {

    private Button collectButton;

    private Button downloadButton;

    private Button shareButton;

    private Button addButton;

    public MusicOperationCellData(Button collectButton, Button downloadButton, Button shareButton, Button addButton) {
        this.collectButton = collectButton;
        this.downloadButton = downloadButton;
        this.shareButton = shareButton;
        this.addButton = addButton;
    }

    public Button getCollectButton() {
        return collectButton;
    }

    public void setCollectButton(Button collectButton) {
        this.collectButton = collectButton;
    }

    public Button getDownloadButton() {
        return downloadButton;
    }

    public void setDownloadButton(Button downloadButton) {
        this.downloadButton = downloadButton;
    }

    public Button getShareButton() {
        return shareButton;
    }

    public void setShareButton(Button shareButton) {
        this.shareButton = shareButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }
}
