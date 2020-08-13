package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.dto.OrgDto;
import com.jitlantis.backend.API.model.SysOrganization;
import org.mapstruct.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysOrganizationDao extends BaseMapper<SysOrganization> {

    List<OrgDto> selectOrgListByOrgId(@Param("orgId") Integer orgId);

    List<SysOrganization> selectOrgQueryList(@Param("orgName") String orgName, @Param("orgAbr") String orgAbr);
}
