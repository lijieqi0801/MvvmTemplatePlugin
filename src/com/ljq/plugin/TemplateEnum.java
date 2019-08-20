package com.ljq.plugin;

public enum TemplateEnum {
    Act("Activity"), Fragment("Fragment"),Dialog("dialog");

    public String value;

    TemplateEnum(String value) {
        this.value = value;
    }
}