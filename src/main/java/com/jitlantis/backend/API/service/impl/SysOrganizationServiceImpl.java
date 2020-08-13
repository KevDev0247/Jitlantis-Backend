package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.SysOrganizationDao;
import com.jitlantis.backend.API.dto.OrgDto;
import com.jitlantis.backend.API.model.SysOrganization;
import com.jitlantis.backend.API.service.SysOrganizationService;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysOrganizationServiceImpl extends ServiceImpl<SysOrganizationDao, SysOrganization> implements SysOrganizationService {

    @Autowired
    private SysOrganizationDao sysOrganizationDao;

    @Override
    public List<OrgDto> selectOrgList(Integer orgId) {
        return sysOrganizationDao.selectOrgListByOrgId(orgId);
    }

    @Override
    public boolean createOrg(Integer orgParentNo, String orgName, String orgAbr, Integer orgStatus, String orgNo,
                             Integer sort, Date orgFoundDate, Date orgDissolveDate) {
        SysOrganization sysOrg = new SysOrganization();
        sysOrg.setOrgName(orgName);
        sysOrg.setOrgParentNo(orgParentNo.toString());
        sysOrg.setOrgAbr(orgAbr);
        sysOrg.setOrgNo(orgNo);
        sysOrg.setSort(sort.toString());

        if (StringUtils.isNotBlank(orgFoundDate.toString())) {
            sysOrg.setOrgFoundDate(orgFoundDate);
        }

        if (StringUtils.isNotBlank(orgDissolveDate.toString())) {
            sysOrg.setOrgDissolveDate(orgDissolveDate);
        }

        if (StringUtils.isNotBlank(orgStatus.toString())) {
            sysOrg.setOrgStatus(orgStatus.toString());
        }

        boolean response = this.insert(sysOrg);
        return response;
    }

    @Override
    public List<SysOrganization> selectOrgQueryList(String orgName, String orgAbr) {
        return sysOrganizationDao.selectOrgQueryList(orgName, orgAbr);
    }
}
