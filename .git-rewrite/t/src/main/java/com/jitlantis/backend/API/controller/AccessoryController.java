package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.model.Accessory;
import com.jitlantis.backend.API.model.SysRole;
import com.jitlantis.backend.API.model.SysUser;
import com.jitlantis.backend.API.service.AccessoryService;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The controller for Accessory (Appliance manufacturing accessories) that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see Accessory
 * created on 2020/09/10
 */
@Api(tags = {"Accessory"})
@RestController
@RequestMapping(value = "/accessory")
public class AccessoryController {

    @Autowired
    private AccessoryService accessoryService;

    @ApiOperation(value = "create accessory")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessory", value = "Accessory Entity", required = true, dataType = "Accessory")
    })
    @RequestMapping(value = "/createAccessory", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createAccessory(@RequestBody Accessory accessory) {
        Map<String, Object> map = new HashMap<>();
        boolean response = accessoryService.insert(accessory);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update accessory")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessory", value = "Accessory Entity", required = true, dataType = "Accessory")
    })
    @RequestMapping(value = "/updateAccessory", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateAccessory(@RequestBody Accessory accessory) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Accessory accessoryRetrieved = accessoryService.selectById(accessory.getId());
        if (accessoryRetrieved == null) {
            response = false;
        } else {
            response = accessoryService.updateById(accessory);
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete accessory")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "accessoryId", value = "accessory id")
    })
    @RequestMapping(value = "/deleteAccessory", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteAccessory(Integer accessoryId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Accessory accessoryRetrieved = accessoryService.selectById(accessoryId);
        if (accessoryRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the accessory does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        accessoryRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = accessoryService.updateById(accessoryRetrieved);
        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "accessory details")
    @RequestMapping(value = "/detail")
    public ResponseEntity<Map<String, Object>> getAccessory(Integer accessoryId) {
        Map<String, Object> map = new HashMap<>();
        Accessory accessory = accessoryService.selectById(accessoryId);
        map.put("data", accessory);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query accessory list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "type", value = "Type"),
            @ApiImplicitParam(paramType = "query", name = "code", value = "Code"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "Name"),
    })
    @RequestMapping(value = "/queryAccessoryList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryRepairList(
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<Accessory> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(type + "") && type > 0) {
            wrapper.eq("type", type);
        }
        if (StringUtils.isNotBlank(code)) {
            wrapper.like("code", code);
        }
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", accessoryService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
