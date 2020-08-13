package com.jitlantis.backend.API.controller;

import com.jitlantis.backend.API.dto.OrgDto;
import com.jitlantis.backend.API.model.Product;
import com.jitlantis.backend.API.model.SysOrganization;
import com.jitlantis.backend.API.service.SysOrganizationService;
import com.jitlantis.backend.API.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The controller for SysOrganization that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see SysOrganization
 * created on 2020/08/13
 */
@Api(tags = {"Organization"})
@RestController
@RequestMapping("/org")
public class OrgController {

    @Autowired
    private SysOrganizationService orgService;

    @ApiOperation(value = "get organization list")
    @GetMapping(value = "/getOrgList")
    public ResponseEntity<Map<String, Object>> getOrgList(@RequestParam("orgId") Integer orgId) {
        Map<String, Object> map = new HashMap<>();
        List<OrgDto> orgList = orgService.selectOrgList(orgId);

        for (OrgDto orgDtoFirst: orgList) {
            List<OrgDto> secondOrgDtoList = orgService.selectOrgList(orgDtoFirst.getId());
            orgDtoFirst.setChildren(secondOrgDtoList);
            orgDtoFirst.setExpanded(true);

            for (OrgDto orgDtoSecond: secondOrgDtoList) {
                List<OrgDto> thirdOrgDtoList = orgService.selectOrgList(orgDtoSecond.getId());
                orgDtoSecond.setChildren(thirdOrgDtoList);
                orgDtoSecond.setExpanded(true);
            }
        }
        map.put("data", orgList);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "create organization")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orgParentNo", value = "Parent Organization", dataType = "int"),
            @ApiImplicitParam(required = true, paramType = "query", name = "orgName", value = "Full Name"),
            @ApiImplicitParam(required = true, paramType = "query", name = "orgAbr", value = "Abbreviation"),
            @ApiImplicitParam(paramType = "query", name = "orgStatus", value = "Status", dataType = "int"),
            @ApiImplicitParam(required = true, paramType = "query", name = "orgNo", value = "Number"),
            @ApiImplicitParam(required = true, paramType = "query", name = "sort", value = "Sorted Number", dataType = "int"),
            @ApiImplicitParam(required = true, paramType = "query", name = "orgFoundDate", value = "Found Date", dataType = "date"),
            @ApiImplicitParam(required = true, paramType = "query", name = "orgDissolveDate", value = "Dissolve Date", dataType = "date"),
    })
    public ResponseEntity<Map<String, Object>> createOrganization(
            @RequestParam(value = "orgParentNo", required = false) Integer orgParentNo, @RequestParam(value = "orgAbr") String orgAbr,
            @RequestParam(value = "orgName") String orgName, @RequestParam(value = "orgStatus", required = false) Integer orgStatus,
            @RequestParam(value = "orgNo") String orgNo, @RequestParam(value = "sort", required = false) Integer sort,
            @RequestParam(value = "orgFoundDate") @DateTimeFormat Date orgFoundDate,
            @RequestParam(value = "orgDissolveDate") @DateTimeFormat Date orgDissolveDate) {
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isNotBlank(orgParentNo.toString())) {
            orgParentNo = 0;
        }

        boolean response = orgService
                .createOrg(orgParentNo, orgName, orgAbr, orgStatus, orgNo, sort, orgFoundDate, orgDissolveDate);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "get organization query list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orgName", value = "Name"),
            @ApiImplicitParam(paramType = "query", name = "orgAbr", value = "Abbreviation"),
    })
    @GetMapping(value = "/queryOrgList")
    public ResponseEntity<Map<String, Object>> getOrgQueryList(
            @RequestParam(value = "orgName", required = false) String orgName,
            @RequestParam(value = "orgAbr", required = false) String orgAbr) {
        Map<String, Object> map = new HashMap<>();
        List<SysOrganization> orgList = orgService.selectOrgQueryList(orgName, orgAbr);
        map.put("data", orgList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
