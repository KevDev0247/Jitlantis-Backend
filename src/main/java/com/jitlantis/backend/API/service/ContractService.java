package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.model.Contract;

import java.util.List;

public interface ContractService extends IService<Contract> {

    List<BaseItemDto> optionList(String name);
}
