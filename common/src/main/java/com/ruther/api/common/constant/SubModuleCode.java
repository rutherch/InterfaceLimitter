package com.ruther.api.common.constant;


import com.ruther.api.common.client.ISubModuleCode;

/**
 * @describe: 子模块编码
 * @author: ruther
 * @date: 2018/8/2 10:36
 **/
public enum  SubModuleCode implements ISubModuleCode {
    NO_SUBMODULE(0L),
    COMMON(1L),
    OAUTH_SERVER(2L),
    API_CLIENT(3L),
    RESOURCE_SERVER(4L),
    GATEWAY(5L),
    ;

    private long subModuleCode;

    SubModuleCode(long subModuleCode) {
        this.subModuleCode = subModuleCode;
    }

    @Override
    public long getSubModuleCode() {
        return subModuleCode;
    }
}
