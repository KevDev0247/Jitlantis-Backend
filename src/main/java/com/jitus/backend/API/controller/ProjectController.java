package com.jitus.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitus.backend.API.model.ProFollow;
import com.jitus.backend.API.model.Project;
import com.jitus.backend.API.service.ProFollowService;
import com.jitus.backend.API.service.ProjectService;
import com.jitus.backend.API.utils.StringUtils;
import com.jitus.backend.API.utils.DeletedEnum;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The controller for Project that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @version 2020.0723
 */
@Api(tags = {"project"})
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProFollowService proFollowService;

    @ApiOperation("create project")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "project", value = "Project entity", required = true, dataType = "Project")
    })
    @RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createProject(@RequestBody Project project) {
        Map<String, Object> map = new HashMap<>();
        boolean response = projectService.insert(project);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation("delete project")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "projectId", value = "project id")
    })
    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteProject(Integer projectId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Project projectRetrieved = projectService.selectById(projectId);
        if (projectRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the project does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        projectRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = projectService.updateById(projectRetrieved);
        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation("update project")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "project", value = "Project entity", required = true, dataType = "Project")
    })
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateProject(@RequestBody Project project) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Project projectRetrieved = projectService.selectById(project.getId());
        if (projectRetrieved == null) {
            response = false;
            map.put("data", response);

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        response = projectService.updateById(project);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query projects list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "contractId", value = "contract id"),
            @ApiImplicitParam(paramType = "query", name = "clientId", value = "client id"),
            @ApiImplicitParam(paramType = "query", name = "name", value = "name"),
    })
    @RequestMapping(value = "/queryProjectsList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryProjectsList(
            @RequestParam(required = false, value = "contractId") Integer contractId,
            @RequestParam(required = false, value = "clientId") Integer clientId,
            @RequestParam(required = false, value = "name") String name) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<Project> wrapper = new EntityWrapper<>();

        if (contractId != null) {
            wrapper.eq("contract_id", contractId);
        }
        if (clientId != null) {
            wrapper.eq("client_id", clientId);
        }
        if (StringUtils.isNotBlank(name)) {
            wrapper.eq("name", name);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id", true);
        map.put("list", projectService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation("create batch projects")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectsList", value = "Project entity list", dataType = "List<Project>")
    })
    @RequestMapping(value = "/createBatchProjects", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createBatchProjects(@RequestBody List<Project> projectList) {
        Map<String, Object> map = new HashMap<>();
        boolean response = projectService.insertBatch(projectList);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation("create project followup")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "proFollow", value = "ProFollow entity", required = true, dataType = "ProFollow")
    })
    @RequestMapping(value = "/createProFollow", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createProFollow(@RequestBody ProFollow proFollow) {
        Map<String, Object> map = new HashMap<>();
        boolean response = proFollowService.insert(proFollow);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation("query project followup list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "type", value = "type"),
            @ApiImplicitParam(paramType = "query", name = "contact_date", value = "contact date"),
            @ApiImplicitParam(paramType = "query", name = "staff_id", value = "staff id"),
            @ApiImplicitParam(paramType = "query", name = "operation", value = "operation"),
    })
    @RequestMapping(value = "/queryProFollowList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryProFollowList(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "contact_date", required = false) Date contact_date,
            @RequestParam(value = "staff_id", required = false) String staff_id,
            @RequestParam(value = "operation", required = false) String operation) {

        Map<String, Object> map = new HashMap<>();
        EntityWrapper<ProFollow> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(type)) {
            wrapper.eq("type", type);
        }
        if (contact_date != null) {
            wrapper.eq("date", contact_date);
        }
        if (StringUtils.isNotBlank(staff_id)) {
            wrapper.eq("type", staff_id);
        }
        if (StringUtils.isNotBlank(operation)) {
            wrapper.eq("type", operation);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", proFollowService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
