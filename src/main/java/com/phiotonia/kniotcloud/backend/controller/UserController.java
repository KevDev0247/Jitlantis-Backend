package com.phiotonia.kniotcloud.backend.controller;

import com.phiotonia.kniotcloud.backend.service.UserService;
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
import java.util.Map;

@Api(tags = {"User"})
@RestController
@RequestMapping("/sys_user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "query users list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "name"),
            @ApiImplicitParam(paramType = "query", name = "email", value = "email")
    })
    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", userService.selectQueryList(name, email));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete a user")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "userId", value = "user's Id")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteUser(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        boolean res = userService.delete(userId);
        if (res) {
            map.put("msg", "user deletion successful");
        } else {
            map.put("msg", "user deletion failed");
        }

        map.put("response", res);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
