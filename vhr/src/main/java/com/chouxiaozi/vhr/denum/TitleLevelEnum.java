package com.chouxiaozi.vhr.denum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Administrator
 * @Date 2020/5/1115:12
 * @Version 1.0
 */
public enum TitleLevelEnum {
    PositiveAdvanced("正高级"),
    DeputyAdvanced("副高级"),
    Middle("中级"),
    Junior("初级"),
    Staff("员级");

    private String value;

    TitleLevelEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static List<String> getTitleLevels(){
        return Arrays.stream(TitleLevelEnum.values()).map(x -> x.getValue()).collect(Collectors.toList());
    }
}
