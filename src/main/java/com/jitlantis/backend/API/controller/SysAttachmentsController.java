package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.model.SysAttachments;
import com.jitlantis.backend.API.service.SysAttachmentsService;
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
 * The controller for SysAttachments (System Attachments) that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see SysAttachments
 * created on 2020/10/24
 */
@Api(tags = {"file"})
@RestController
@RequestMapping(value = "/sysAttachments")
public class SysAttachmentsController {

    @Autowired
    private SysAttachmentsService sysAttachmentsService;

    @ApiOperation(value = "create attachment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysAttachments", value = "SysAttachment Entity", required = true, dataType = "SysAttachments")
    })
    @RequestMapping(value = "/createSysAttachments", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createSysAttachments(@RequestBody SysAttachments sysAttachments) {
        Map<String, Object> map = new HashMap<>();
        boolean response = sysAttachmentsService.insert(sysAttachments);
        map.put("data", response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update attachments")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysAttachments", value = "SysAttachment Entity", required = true, dataType = "SysAttachments")
    })
    @RequestMapping(value = "/updateSysAttachments", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateSysAttachments(@RequestBody SysAttachments sysAttachment) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysAttachments sysAttachmentsRetrieved = sysAttachmentsService.selectById(sysAttachment.getFileid());
        if (sysAttachmentsRetrieved == null) {
            response = false;
        } else {
            response = sysAttachmentsService.updateById(sysAttachment);
        }
        map.put("data", response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete attachments")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "sysAttachmentsId", value = "File Id")
    })
    @RequestMapping(value = "/deleteSysAttachments", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteSysAttachments(Integer sysAttachmentsId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysAttachments sysAttachmentRetrieved = sysAttachmentsService.selectById(sysAttachmentsId);
        if (sysAttachmentRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the file does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        sysAttachmentRetrieved.setStatus("0");
        response = sysAttachmentsService.updateById(sysAttachmentRetrieved);

        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query attachments list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "File Name"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "File Format"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "User"),
    })
    @RequestMapping(value = "/queryStsAttachmentsList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryStsAttachmentsList(
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "name", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<SysAttachments> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(userId + "") && userId > 0) {
            wrapper.eq("user_id", userId);
        }
        if (StringUtils.isNotBlank(type)) {
            wrapper.like("extension", type);
        }
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("filename", name);
        }

        wrapper.eq("status", "1");
        wrapper.orderBy("fileid");
        map.put("list", sysAttachmentsService.selectList(wrapper));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "view attachment details")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getSysAttachments(Integer id) {
        Map<String, Object> map = new HashMap<>();
        SysAttachments sysAttachments = sysAttachmentsService.selectById(id);
        map.put("data", sysAttachments);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "Add Download Count")
    @RequestMapping(value = "/addDownloadCount", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> addDownloadCount(Integer id) {
        Map<String, Object> map = new HashMap<>();
        SysAttachments attachments = sysAttachmentsService.selectById(id);

        attachments.setDownCount(attachments.getDownCount() + 1);
        boolean response = sysAttachmentsService.updateById(attachments);
        map.put("data", response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
