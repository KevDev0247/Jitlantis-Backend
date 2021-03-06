package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.model.Staff;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import com.jitlantis.backend.API.service.StaffService;
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
 * The controller for Staff that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see Staff
 * created on 2020/07/24
 */
@Api(tags = {"staff"})
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @ApiOperation(value = "create staff")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staff", value = "Staff entity", required = true, dataType = "Staff")
    })
    @RequestMapping(value = "/createStaff", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createStaff(@RequestBody Staff staff) {
        Map<String, Object> map = new HashMap<>();
        boolean response = staffService.insert(staff);
        map.put("data", response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update staff")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staff", value = "Staff entity", required = true, dataType = "Staff")
    })
    @RequestMapping(value = "/updateStaff", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateStaff(@RequestBody Staff staff) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Staff staffRetrieved = staffService.selectById(staff.getId());
        if (staffRetrieved == null) {
            response = false;
        } else {
            response = staffService.updateById(staff);
        }
        map.put("data", response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete staff")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "staffId", value = "staff id")
    })
    @RequestMapping(value = "/deleteStaff", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteStaff(Integer staffId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Staff staffRetrieved = staffService.selectById(staffId);
        if (staffRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the staff does not exist!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        staffRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = staffService.updateById(staffRetrieved);

        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query staff list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dept", value = "department"),
            @ApiImplicitParam(paramType = "query", name = "company", value = "company"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "name"),
    })
    @RequestMapping(value = "/queryStaffList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryStaffList(
            @RequestParam(value = "dept", required = false) String dept,
            @RequestParam(value = "company", required = false) String company,
            @RequestParam(value = "name", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<Staff> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(dept)) {
            wrapper.eq("dept", dept);
        }
        if (StringUtils.isNotBlank(company)) {
            wrapper.eq("company", company);
        }
        if (StringUtils.isNotBlank(name)) {
            wrapper.eq("name", name);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", staffService.selectList(wrapper));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
