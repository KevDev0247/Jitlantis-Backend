package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.model.RepairRecord;
import com.jitlantis.backend.API.service.RepairRecordService;
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
 * RepairRecord's controller to deal with HTTP requests and responses (MVC).
 *
 * @author Yonggang Su
 * @see RepairRecord
 * created on 2020/09/14
 */
@Api(tags = {"Repair Record"})
@RestController
@RequestMapping(value = "/repairRecord")
public class RepairRecordController {

    @Autowired
    private RepairRecordService repairrecordService;

    @ApiOperation(value = "create repair record")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repairrecord", value = "RepairRecord Entity", required = true, dataType = "RepairRecord")
    })
    @RequestMapping(value = "/createRepairRecord", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createRepairRecord(@RequestBody RepairRecord repairrecord) {
        Map<String, Object> map = new HashMap<>();
        boolean response = repairrecordService.insert(repairrecord);
        map.put("data", response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update repair record")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "repairrecord", value = "RepairRecord Entity", required = true, dataType = "RepairRecord")
    })
    @RequestMapping(value = "/updateRepairRecord", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateRepairRecord(@RequestBody RepairRecord repairrecord) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        RepairRecord repairrecordSelected = repairrecordService.selectById(repairrecord.getId());
        if (repairrecordSelected == null) {
            response = false;
        } else {
            response = repairrecordService.updateById(repairrecordSelected);
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete repair record")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "repairrecordId", value = "Repair Record Id")
    })
    @RequestMapping(value = "/deleteRepairRecord", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteRepairRecord(Integer repairrecordId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        RepairRecord repairrecordRetrieved = repairrecordService.selectById(repairrecordId);
        if (repairrecordRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the repair record does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        repairrecordRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = repairrecordService.updateById(repairrecordRetrieved);
        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query repair record list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dcode", value = "Error Code"),
            @ApiImplicitParam(paramType = "query", name = "ccode", value = "Fix Code"),
            @ApiImplicitParam(paramType = "query", name = "content", value = "Content"),
    })
    @RequestMapping(value = "/queryRepairRecordList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryRepairRecordList(
            @RequestParam(value = "dcode", required = false) String dcode,
            @RequestParam(value = "ccode", required = false) String ccode,
            @RequestParam(value = "content", required = false) String content) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<RepairRecord> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(dcode)) {
            wrapper.eq("dcode", dcode);
        }
        if (StringUtils.isNotBlank(ccode)) {
            wrapper.like("repairrecord_unit", ccode);
        }
        if (StringUtils.isNotBlank(content)) {
            wrapper.like("content", content);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", repairrecordService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "view repair record",notes = "")
    @RequestMapping(value="/detail", method= RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getRepairRecord(Integer id) {
        Map<String,Object> map = new HashMap<String,Object>();
        RepairRecord repairrecord = repairrecordService.selectById(id);
        map.put("data", repairrecord);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
}
