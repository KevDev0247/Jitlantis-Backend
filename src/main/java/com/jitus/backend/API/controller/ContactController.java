package com.jitus.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitus.backend.API.model.Contact;
import com.jitus.backend.API.utils.StringUtils;
import com.jitus.backend.API.service.ContactService;
import com.jitus.backend.API.utils.DeletedEnum;
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
 * The controller for Contact that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @version 2020.0725
 */
@Api(tags = {"contact"})
@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @ApiOperation(value = "create contact")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contact", value = "Contact entity", required = true, dataType = "Contact")
    })
    @RequestMapping(value = "/createContact", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createContact(@RequestBody Contact contact) {
        Map<String, Object> map = new HashMap<>();
        boolean response = contactService.insert(contact);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update contact")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contact", value = "Contact entity", required = true, dataType = "Contact")
    })
    @RequestMapping(value = "/updateContact", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateContact(@RequestBody Contact contact) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Contact contactRetrieved = contactService.selectById(contact.getId());
        if (contactRetrieved == null) {
            response = false;
        } else {
            response = contactService.updateById(contact);
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete contact")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "contactId", value = "contact id")
    })
    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteContact(Integer contactId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Contact contactRetrieved = contactService.selectById(contactId);
        if (contactRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the contact does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        contactRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = contactService.updateById(contactRetrieved);

        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query contact list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dept", value = "department"),
            @ApiImplicitParam(paramType = "query", name = "profession", value = "profession"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "name"),
    })
    @RequestMapping(value = "/queryContactList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryContactList(
            @RequestParam(value = "dept", required = false) String dept,
            @RequestParam(value = "profession", required = false) String profession,
            @RequestParam(value = "name", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<Contact> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(dept)) {
            wrapper.eq("dept", dept);
        }
        if (StringUtils.isNotBlank(profession)) {
            wrapper.eq("profession", profession);
        }
        if (StringUtils.isNotBlank(name)) {
            wrapper.eq("name", name);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", contactService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
