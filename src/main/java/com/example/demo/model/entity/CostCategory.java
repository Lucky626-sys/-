package com.example.demo.model.entity;

public enum CostCategory {
	INGREDIENTS("原料進貨"),
	UTILITIES("水電瓦斯"),
    SALARY("員工薪資"),
    TAX("稅"),
    MISC("雜項");

	//用來儲存每個 enum 常數的「顯示名稱」
    private final String displayName;

    //enum 的建構子，每個常數宣告時會呼叫這個建構子，並把對應的中文名稱傳進來
    CostCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}