package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.model.Contract;
import com.jitlantis.backend.API.service.ContractService;
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
 * The controller for Contract that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang, Yonggang Su
 * @see Contract
 * created on 2020/08/31
 */
@Api(tags = {"Contract"})
@RestController
@RequestMapping(value = "/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @ApiOperation(value = "create contract")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contract", value = "Contract entity", required = true, dataType = "Contract")
    })
    @RequestMapping(value = "/createContract", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createContract(@RequestBody Contract contract) {
        Map<String, Object> map = new HashMap<>();
        boolean response = contractService.insert(contract);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update contract")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contract", value = "Contract entity", required = true, dataType = "Contract")
    })
    @RequestMapping(value = "/updateContract", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateContract(@RequestBody Contract contract) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Contract contractRetrieved = contractService.selectById(contract.getId());
        if (contractRetrieved == null) {
            response = false;
        } else {
            response = contractService.updateById(contract);
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete contract")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "contractId", value = "contract id")
    })
    @RequestMapping(value = "/deleteContract", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteContract(Integer contractId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Contract contractRetrieved = contractService.selectById(contractId);
        if (contractRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the contract does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        contractRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = contractService.updateById(contractRetrieved);

        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "view contract")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getContact(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Contract contract = contractService.selectById(id);
        map.put("data", contract);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query contract list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "status", value = "status"),
            @ApiImplicitParam(paramType = "query", name = "content", value = "content"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "name"),
    })
    @RequestMapping(value = "/queryContractList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryContractList(
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "name", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<Contract> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(status+"")) {
            wrapper.eq("status", status);
        }
        if (StringUtils.isNotBlank(content)) {
            wrapper.like("content", content);
        }
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", contractService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
