package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jitlantis.backend.API.base.PageRequest;
import com.jitlantis.backend.API.model.Repair;
import com.jitlantis.backend.API.service.RepairService;
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

import java.util.HashMap;
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
            @ApiImplicitParam(paramType = "query", name = "status", value = "Status"),
            @ApiImplicitParam(paramType = "query", name = "repairUnit", value = "Repair Unit"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "Client"),
    })
    @RequestMapping(value = "/queryRepairList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryRepairList(
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "repairUnit", required = false) String repairUnit,
            @RequestParam(value = "name", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<Repair> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(status + "") && status != null) {
            wrapper.eq("status", status);
        }
        if (StringUtils.isNotBlank(repairUnit)) {
            wrapper.like("repair_unit", repairUnit);
        }
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", repairService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
