package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.base.JitConverter;
import com.jitlantis.backend.API.dao.ContractDao;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.model.Contract;
import com.jitlantis.backend.API.service.ContractService;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the Service interface for Contract
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/24
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractDao, Contract> implements ContractService {

    @Autowired
    private JitConverter jitConverter;

    @Override
    public List<BaseItemDto> optionList(String name) {
        EntityWrapper<Contract> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        List<Contract> contractList = this.selectList(wrapper);

        List<BaseItemDto> baseItemList = jitConverter.mergeListByAny(BaseItemDto.class, contractList, null, null);
        if (baseItemList == null || baseItemList.size() == 0) {
            baseItemList = new ArrayList<>();
        }

        return baseItemList;
    }
}
