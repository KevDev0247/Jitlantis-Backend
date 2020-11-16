package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.dto.OrgDto;
import com.jitlantis.backend.API.model.SysOrganization;

import java.util.Date;
import java.util.List;

public interface SysOrganizationService extends IService<SysOrganization> {

    List<OrgDto> selectOrgList(Integer orgId);

    boolean createOrg(Integer orgParentNo, String orgName, String orgAbr,
                      Integer orgStatus, String orgNo, Integer sort,
                      Date orgFoundDate, Date orgDissolveDate);

    List<SysOrganization> selectOrgQueryList(String orgName, String orgAbr);
}
