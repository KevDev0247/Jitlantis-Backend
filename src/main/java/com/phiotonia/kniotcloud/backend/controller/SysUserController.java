package com.phiotonia.kniotcloud.backend.controller;

import com.phiotonia.kniotcloud.backend.model.SysUser;
import com.phiotonia.kniotcloud.backend.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = {"User"})
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @ApiOperation(value = "query users list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "name"),
            @ApiImplicitParam(paramType = "query", name = "email", value = "email"),
    })
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", sysUserService.selectQueryList(name, email));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete a user")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "userId", value = "userId")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteUser(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        boolean response = sysUserService.delete(userId);

        if (response) {
            map.put("message", "user deletion successful");
        } else {
            map.put("message", "user deletion failed");
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "bind roles to users")
    @RequestMapping(value = "/bindRole", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> updateRole(Integer userId, Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        SysUser userRetrieved = sysUserService.selectById(userId);
        boolean response;

        if (userRetrieved != null) {
            userRetrieved.setRoleId(roleId);
            response = sysUserService.updateById(userRetrieved);
        } else {
            response = false;
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update the password")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "userId", value = "userId"),
            @ApiImplicitParam(required = true, paramType = "query", name = "oldPassword", value = "old"),
            @ApiImplicitParam(required = true, paramType = "query", name = "newPassword", value = "new"),
    })
    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> updatePassword(
            @RequestParam Integer userId, @RequestParam String oldPassword, @RequestParam String newPassword) {
        Map<String, Object> map = new HashMap<>();
        SysUser userRetrieved = sysUserService.selectById(userId);
        String message;
        boolean response;

        if (userRetrieved != null) {
            response = encoder.matches(oldPassword, userRetrieved.getPassword());
        } else {
            response = false;
            message = "userRetrieved does not exist";
            map.put("data", response);
            map.put("message", message);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        if (!response) {
            message = "failed to update the password";
        } else {
            message = "successfully update the password";
            userRetrieved.setPassword(encoder.encode(newPassword));
            sysUserService.updateById(userRetrieved);
        }
        map.put("data", response);
        map.put("message", message);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "user sign up")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "user", value = "system user entity", dataType = "SysUser")
    })
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getNewUser(@RequestBody SysUser user) {
        Map<String, Object> map = new HashMap<>();
        SysUser userRetrieved = sysUserService.findUserByName(user.getName());
        boolean response;

        if (userRetrieved != null) {
            response = false;
            map.put("data", response);
            map.put("message", "The user already existed");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreateTime(new Date());
        response = sysUserService.insert(user);
        map.put("data", response);

        if (response) {
            map.put("message", "Sign up successful");
        } else {
            map.put("message", "Sign up failed");
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
