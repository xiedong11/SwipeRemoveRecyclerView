package com.zhuandian.swiperemoveitemrecyclerview;

/**
 * desc :
 * author：xiedong
 * data：2018/7/24
 */
public class ItemEntity {
    private String content;
    private boolean isOpen;

    public ItemEntity(String content, boolean isOpen) {
        this.content = content;
        this.isOpen = isOpen;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
