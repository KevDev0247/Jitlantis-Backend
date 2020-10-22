package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jitlantis.backend.API.annotation.MyLog;
import com.jitlantis.backend.API.base.JitConverter;
import com.jitlantis.backend.API.base.JitEntityGroup;
import com.jitlantis.backend.API.base.JitEntityStringGroup;
import com.jitlantis.backend.API.base.PageRequest;
import com.jitlantis.backend.API.dao.RepairDao;
import com.jitlantis.backend.API.dto.RepairDto;
import com.jitlantis.backend.API.model.Contact;
import com.jitlantis.backend.API.model.Product;
import com.jitlantis.backend.API.model.Project;
import com.jitlantis.backend.API.model.Repair;
import com.jitlantis.backend.API.service.*;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The controller for Repair (Work Order entity) that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see Repair
 * created on 2020/09/08
 */
@Api(tags = {"Work Order"})
@RestController
@RequestMapping(value = "/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private JitConverter jitConverter;

    @ApiOperation(value = "create work order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repair", value = "Repair Entity", required = true, dataType = "Repair")
    })
    @RequestMapping(value = "/createRepair", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createRepair(@RequestBody Repair repair) {
        Map<String, Object> map = new HashMap<>();
        boolean response = repairService.insert(repair);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update work order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repair", value = "Repair Entity", required = true, dataType = "Repair")
    })
    @RequestMapping(value = "/updateRepair", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateRepair(@RequestBody Repair repair) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Repair repairRetrieved = repairService.selectById(repair.getId());
        if (repairRetrieved == null) {
            response = false;
        } else {
            response = repairService.updateById(repair);
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete work order")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "repairId", value = "work order id")
    })
    @RequestMapping(value = "/deleteRepair", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteRepair(Integer repairId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Repair repairRetrieved = repairService.selectById(repairId);
        if (repairRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the order does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        repairRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = repairService.updateById(repairRetrieved);
        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "work order list", notes = "pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "status", value = "Status"),
            @ApiImplicitParam(paramType = "query", name = "search", value = "Search"),
    })
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> selectPageList(
            @RequestBody PageRequest pageQuery,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "search", required = false) String search) {
        Map<String, Object> map = new HashMap<>();
        Page<Repair> page = repairService.selectPageList(pageQuery.getPageNum(), pageQuery.getPageSize(), status, search);
        map.put("data", page);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "work order details")
    @RequestMapping(value = "/detail")
    public ResponseEntity<Map<String, Object>> getRepair(Integer repairId) {
        Map<String, Object> map = new HashMap<>();
        Repair repair = repairService.selectById(repairId);
        map.put("data", repair);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query work order list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "info", value = "Info (product, client, project)"),
            @ApiImplicitParam(paramType = "query", name = "code", value = "Repair company"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "Work Order Name"),
            @ApiImplicitParam(paramType = "query", name = "status", value = "Work Order Status"),
    })
    @RequestMapping(value = "/queryRepairList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryRepairList(
            @RequestParam(value = "info", required = false) String info,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "status", required = false) Integer status) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<Repair> wrapper = new EntityWrapper<>();

        List<Repair> repairList = repairService.getSearchList(info);
        if (repairList == null || repairList.size() == 0) {
            map.put("list", repairList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        List<Long> projectIds = jitConverter.getLongListFromEntityList(repairList, "project_id");
        List<Project> projectList = projectService.findAllByIds(projectIds);

        List<Long> productIds = jitConverter.getLongListFromEntityList(repairList, "product_id");
        List<Product> productList = productService.findAllByIds(productIds);

        List<Long> contactIds = jitConverter.getLongListFromEntityList(repairList, "contact_id");
        List<Contact> contactList = contactService.findAllByIds(contactIds);

        List<Long> repairIds = jitConverter.getLongListFromEntityList(repairList, "repair_id");
        wrapper.in("id", repairIds);
        if (StringUtils.isNotBlank(code)) {
            wrapper.like("code", code);
        }
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(status + "") && status > 0) {
            wrapper.eq("status", status);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");

        repairList = repairService.selectList(wrapper);
        List<RepairDto> repairDtos = jitConverter.mergeListByAny(RepairDto.class, repairList, null, null);

        Map<String, String> fileRule = new HashMap<>();
        Map<String, JitEntityGroup<?>> fileMap = new HashMap<>();

        if (projectList != null && projectList.size() > 0) {
            fileRule.put("projectName", "projectId");
            JitEntityStringGroup<String> fileGroup = new JitEntityStringGroup<>(projectList, "id", "name");
            fileMap.put("projectName", fileGroup);
        }

        if (productList != null && productList.size() > 0) {
            fileRule.put("productName", "productId");
            JitEntityStringGroup<String> fileGroup = new JitEntityStringGroup<>(productList, "id", "name");
            fileMap.put("productName", fileGroup);
        }

        if (contactList != null && contactList.size() > 0) {
            fileRule.put("contactName", "contactId");
            JitEntityStringGroup<String> fileGroup = new JitEntityStringGroup<>(contactList, "id", "name");
            fileMap.put("contactName", fileGroup);
        }

        repairDtos = jitConverter.mergeListByAny(RepairDto.class, repairDtos, fileRule, fileMap);
        map.put("list", repairDtos);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query repairman list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "Repairman Name"),
            @ApiImplicitParam(paramType = "query", name = "company", value = "Company"),
    })
    @RequestMapping(value = "/queryRepairmanList", method = RequestMethod.GET)
    @MyLog(value = "query repairman list")
    public ResponseEntity<Map<String, Object>> queryRepairmanList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "company", required = false) String company) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userService.selectRepairmanQueryList(name, company));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
