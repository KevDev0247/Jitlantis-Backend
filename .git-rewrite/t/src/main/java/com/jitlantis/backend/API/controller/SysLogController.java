package com.jitlantis.backend.API.controller;

import com.jitlantis.backend.API.model.SysLog;
import com.jitlantis.backend.API.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"Logs"})
@RestController
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @ApiOperation(value = "query logs list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "fromTime", value = "Start Time"),
            @ApiImplicitParam(paramType = "query", name = "toTime", value = "End Time"),
    })
    @RequestMapping(value = "/queryLogList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryLogList(
            @RequestParam(value = "fromTime", required = false) String fromTime,
            @RequestParam(value = "toTime", required = false) String toTime) {
        Map<String, Object> map = new HashMap<>();
        List<SysLog> sysLogList = sysLogService.selectLogList(fromTime, toTime);
        map.put("list", sysLogList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
