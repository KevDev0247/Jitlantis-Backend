package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.model.SysBasecode;

import java.util.List;

public interface SysBasecodeService extends IService<SysBasecode> {
    List<SysBasecode> optionList(String typeId, String info);
}
