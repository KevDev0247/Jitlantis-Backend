package com.phiotonia.kniotcloud.backend.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.phiotonia.kniotcloud.backend.model.Staff;
import com.phiotonia.kniotcloud.backend.service.StaffService;
import com.phiotonia.kniotcloud.backend.utils.DeletedEnum;
import com.phiotonia.kniotcloud.backend.utils.StringUtils;
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
 * @version 2020.0715
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
            @ApiImplicitParam(paramType = "query", name = "s_code", value = "s_code"),
    })
    @RequestMapping(value = "/queryStaffList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryStaffList(
            @RequestParam(value = "dept", required = false) String dept,
            @RequestParam(value = "company", required = false) String company,
            @RequestParam(value = "s_code", required = false) String s_code) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<Staff> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(dept)) {
            wrapper.eq("dept", dept);
        }
        if (StringUtils.isNotBlank(company)) {
            wrapper.eq("company", company);
        }
        if (StringUtils.isNotBlank(s_code)) {
            wrapper.eq("s_code", s_code);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", staffService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
