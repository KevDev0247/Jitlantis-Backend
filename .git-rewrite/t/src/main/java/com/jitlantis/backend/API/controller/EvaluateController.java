package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.model.Evaluate;
import com.jitlantis.backend.API.service.EvaluateService;
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
 * Evaluate's controller to deal with HTTP requests and responses (MVC).
 *
 * @author Yonggang Su
 * @see Evaluate
 * created on 2020/09/16
 */
@Api(tags = {"Evaluate"})
@RestController
@RequestMapping(value = "/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;

    @ApiOperation(value = "create evaluate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evaluate", value = "Evaluate Entity", required = true, dataType = "Evaluate")
    })
    @RequestMapping(value = "/createEvaluate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createEvaluate(@RequestBody Evaluate evaluate) {
        Map<String, Object> map = new HashMap<String,Object>();
        boolean response = evaluateService.insert(evaluate);
        map.put("data", response);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update evaluate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evaluate", value = "Evaluate Entity", required = true, dataType = "Evaluate")
    })
    @RequestMapping(value = "/updateEvaluate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateEvaluate(@RequestBody Evaluate evaluate) {
        Map<String, Object> map = new HashMap<String,Object>();
        boolean response;
        Evaluate evaluateRetrieved = evaluateService.selectById(evaluate.getId());
        if (evaluateRetrieved == null) {
            response = false;
        } else {
            response = evaluateService.updateById(evaluate);
        }
        map.put("data", response);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete evaluate")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "evaluateId", value = "Evaluate Id")
    })
    @RequestMapping(value = "/deleteEvaluate", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteEvaluate(Integer evaluateId) {
        Map<String, Object> map = new HashMap<String,Object>();
        boolean response;

        Evaluate f_evaluate = evaluateService.selectById(evaluateId);
        if (f_evaluate == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the evaluate does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        response = evaluateService.deleteById(evaluateId);
        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query evaluate list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "search", value = "content"),
    })
    @RequestMapping(value = "/queryEvaluateList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryEvaluateList(
            @RequestParam(value = "search", required = false) String search) {
        Map<String, Object> map = new HashMap<String,Object>();
        EntityWrapper<Evaluate> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(search)) {
            wrapper.like("comments", search);
        }
        wrapper.orderBy("id");
        map.put("list", evaluateService.selectList(wrapper));
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "view evaluate details",notes = "")
    @RequestMapping(value="/detail", method= RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getEvaluate(Integer id) {
        Map<String,Object> map = new HashMap<String,Object>();
        Evaluate evaluate = evaluateService.selectById(id);
        map.put("data", evaluate);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
}
