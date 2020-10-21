package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.SysAttachments;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysAttachmentsDao extends BaseMapper<SysAttachments> {

    int createAttachments(SysAttachments sysAttachments);
}
