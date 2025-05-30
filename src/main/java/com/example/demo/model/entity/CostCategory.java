package com.example.demo.model.entity;

public enum CostCategory {
    SALARY("員工薪資"),
    TAX("稅"),
    UTILITIES("水電瓦斯"),
    INGREDIENTS("原料進貨"),
    MISC("雜項");

    private final String displayName;

    CostCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}