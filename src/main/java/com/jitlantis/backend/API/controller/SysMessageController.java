package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.model.SysMessage;
import com.jitlantis.backend.API.model.SysRole;
import com.jitlantis.backend.API.model.SysUser;
import com.jitlantis.backend.API.service.SysMessageService;
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
 * The controller for SysMessage (Messages sent to system users) that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see SysMessage
 * created on 2020/10/13
 */
@Api(tags = {"System Messages"})
@RestController
@RequestMapping(value = "/sysMessage")
public class SysMessageController {

    @Autowired
    private SysMessageService sysMessageService;

    @ApiOperation(value = "create system message")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysMessage", value = "SysMessage Entity", required = true, dataType = "SysMessage")
    })
    @RequestMapping(value = "/createSysMessage", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createSysMessage(@RequestBody SysMessage sysMessage) {
        Map<String, Object> map = new HashMap<>();
        boolean response = sysMessageService.insert(sysMessage);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update system message")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysMessage", value = "SysMessage Entity", required = true, dataType = "SysMessage")
    })
    @RequestMapping(value = "/updateSysMessage", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateSysMessage(@RequestBody SysMessage sysMessage) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysMessage sysMessageRetrieved = sysMessageService.selectById(sysMessage.getId());
        if (sysMessageRetrieved == null) {
            response = false;
        } else {
            response = sysMessageService.updateById(sysMessage);
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete system message")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "sysMessageId", value = "sysMessageId")
    })
    @RequestMapping(value = "/deleteSysMessage", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteSysMessage(Integer sysMessageId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysMessage sysMessageRetrieved = sysMessageService.selectById(sysMessageId);
        if (sysMessageRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "system message deletion successful");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        sysMessageRetrieved.setIsDelete(DeletedEnum.Y.value());
        response =sysMessageService.updateById(sysMessageRetrieved);
        if (response) {
            map.put("message", "system message deletion successful");
        } else {
            map.put("message", "system message deletion failed");
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query system message list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "type", value = "type"),
            @ApiImplicitParam(paramType = "query", name = "content", value = "content"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "userId"),
            @ApiImplicitParam(paramType = "query", name = "isRead", value = "isRead"),
    })
    @RequestMapping(value = "/querySysMessageList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> querySysMessageList(
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "isRead", required = false) Integer isRead) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<SysMessage> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(type + "")&&type>0) {
            wrapper.eq("type", type);
        }
        if (StringUtils.isNotBlank(content)) {
            wrapper.like("content", content);
        }
        if (StringUtils.isNotBlank(userId + "")&&userId>0) {
            wrapper.eq("user_id", userId);
        }
        if (StringUtils.isNotBlank(isRead + "")&&isRead!=-1) {
            wrapper.eq("is_read", isRead);
        }

        wrapper.eq("is_delete",DeletedEnum.N.value() );
        wrapper.orderBy("id");
        map.put("list", sysMessageService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "system message details")
    @RequestMapping(value="/detail", method= RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getSysMessage(Integer id) {
        Map<String,Object> map = new HashMap<>();
        SysMessage sysMessage = sysMessageService.selectById(id);
        map.put("data", sysMessage);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
