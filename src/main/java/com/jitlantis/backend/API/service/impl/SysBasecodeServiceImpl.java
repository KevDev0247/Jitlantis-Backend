package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.SysBasecodeDao;
import com.jitlantis.backend.API.model.SysBasecode;
import com.jitlantis.backend.API.service.SysBasecodeService;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysBasecodeServiceImpl extends ServiceImpl<SysBasecodeDao, SysBasecode> implements SysBasecodeService {

    @Override
    public List<SysBasecode> optionList(String typeId, String info) {
        EntityWrapper<SysBasecode> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(typeId)) {
            wrapper.eq("typeid", typeId);
        }
        if (StringUtils.isNotBlank(info)) {
            wrapper.like("basecodename", info);
        }
        wrapper.orderBy("basecode");
        List<SysBasecode> basecodeList = this.selectList(wrapper);

        if (basecodeList == null || basecodeList.size() == 0) {
            basecodeList = new ArrayList<>();
        }

        return basecodeList;
    }
}
