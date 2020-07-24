package com.phiotonia.kniotcloud.backend.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.phiotonia.kniotcloud.backend.model.Project;
import com.phiotonia.kniotcloud.backend.service.ProjectService;
import com.phiotonia.kniotcloud.backend.utils.DeletedEnum;
import com.phiotonia.kniotcloud.backend.utils.StringUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"project"})
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

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
}
