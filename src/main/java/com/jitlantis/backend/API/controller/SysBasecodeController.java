package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.model.SysBasecode;
import com.jitlantis.backend.API.service.SysBasecodeService;
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

@Api(tags = "Basic Data")
@RestController
@RequestMapping("/basecode")
public class SysBasecodeController {

    @Autowired
    private SysBasecodeService sysBasecodeService;

    @ApiOperation(value = "Create Basic Data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysBasecode", value = "Foundation Data", required = true, dataType = "SysBasecode")
    })
    @RequestMapping(name = "/createBasecode", value = "SysBasecode Entity", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createSysbasecode(@RequestBody SysBasecode sysBasecode) {
        Map<String, Object> map = new HashMap<>();
        boolean response = sysBasecodeService.insert(sysBasecode);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Basic Data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysBasecode", value = "Foundation Data", required = true, dataType = "SysBasecode")
    })
    @RequestMapping(value = "/updateBasecode", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateSysBasecode(@RequestBody SysBasecode sysBasecode) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysBasecode basecodeRetrieved = sysBasecodeService.selectById(sysBasecode.getBasecode());
        if (basecodeRetrieved == null) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        response = sysBasecodeService.updateById(sysBasecode);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Basic Data")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "sysBasecodeId", value = "Foundation Data Id")
    })
    @RequestMapping(value = "/deleteBasecode", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteSysBasecode(Integer sysBasecodeId) {
        Map<String, Object> map = new HashMap<>();
        boolean response = true;

        SysBasecode basecode = sysBasecodeService.selectById(sysBasecodeId);
        if (basecode == null) {
            response = false;
            map.put("data", response);
            map.put("message", "The data does not exist. Deletion Failed!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        response = sysBasecodeService.deleteById(sysBasecodeId);
        if (response) {
            map.put("message", "Deletion successful!");
        } else {
            map.put("message", "Deletion failed!");
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Query Basic Data List")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "typename", value = "Data Type Name"),
            @ApiImplicitParam(paramType = "query", name = "basecodename", value = "Basic Data Name"),
    })
    @RequestMapping(value = "/queryBasecodeList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryBasecodeList(
            @RequestParam(value = "typename", required = false) String typeName,
            @RequestParam(value = "basecodename", required = false) String basecodeName) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        EntityWrapper<SysBasecode> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(typeName)) {
            wrapper.like("typename", typeName);
        }
        if (StringUtils.isNotBlank(basecodeName)) {
            wrapper.eq("basecodename", basecodeName);
        }
        wrapper.orderBy("basecode", true);
        map.put("list", sysBasecodeService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
