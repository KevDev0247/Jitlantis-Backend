package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.dto.MainMenuDto;
import com.jitlantis.backend.API.model.SysRoleMenu;
import com.jitlantis.backend.API.model.SysUserMenu;
import com.jitlantis.backend.API.service.SysUserMenuService;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import com.jitlantis.backend.API.model.SysMenu;
import com.jitlantis.backend.API.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The controller for SysMenu (System Menu) that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see SysMenu
 * @see SysRoleMenu
 * created on 2020/07/20
 */
@Api(tags = {"menu"})
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysUserMenuService userMenuService;

    @ApiOperation("create menu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysMenu", value = "SysMenu entity", required = true, dataType = "SysMenu")
    })
    @RequestMapping(value = "/createMenu", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createMenu(@RequestBody SysMenu sysMenu) {
        Map<String, Object> map = new HashMap<>();
        boolean response = menuService.insert(sysMenu);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation("delete menu")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "menuId", value = "menu id")
    })
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteMenu(Integer menuId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysMenu sysMenuRetrieved = menuService.selectById(menuId);
        if (sysMenuRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the menu does not exist!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        sysMenuRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = menuService.updateById(sysMenuRetrieved);

        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update menu", notes = "id cannot be changed!")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysMenu", value = "SysMenu entity", required = true, dataType = "SysMenu")
    })
    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateMenu(@RequestBody SysMenu sysMenu) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysMenu sysMenuRetrieved = menuService.selectById(sysMenu.getId());
        if (sysMenuRetrieved == null) {
            response = false;
            map.put("data", response);

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        response = menuService.updateById(sysMenu);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query menus list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "parentId", value = "parent id of the menu"),
            @ApiImplicitParam(paramType = "query", name = "text", value = "text of the menu"),
    })
    @RequestMapping(value = "/queryMenusList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryMenusList(
            @RequestParam(required = false, value = "parentId") Integer parentId,
            @RequestParam(required = false, value = "text") String text) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(parentId.toString())) {
            wrapper.eq("parent_id", parentId);
        }
        if (StringUtils.isNotBlank(text)) {
            wrapper.like("text", text);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id", true);
        map.put("list", menuService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "create batch menus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysMenuList", value = "SysMenu entity list", required = true, dataType = "List<SysMenu>")
    })
    @RequestMapping(value = "/createBatchMenu", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createBatchMenus(@RequestBody List<SysMenu> sysMenuList) {
        Map<String, Object> map = new HashMap<>();
        boolean response = menuService.insertBatch(sysMenuList);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "get role menu list")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "roleId", value = "Role Id")
    })
    @GetMapping(value = "/getRoleMenuList")
    public ResponseEntity<Map<String, Object>> getRoleMenuList(@RequestParam("roleId") Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        List<SysMenu> menuList = menuService.selectMenuListByRoleId(roleId);
        map.put("data", menuList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "get main menus")
    @RequestMapping(value = "/getMainMenus", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getMainMenus(Integer userId, Integer isShow) {
        Map<String, Object> map = new HashMap<>();
        List<MainMenuDto> mainMenuList = userMenuService.getMainMenus(userId, isShow);
        map.put("data", mainMenuList);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "add main menu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuIds", value = "Menu Ids", required = true, dataType = "List<String>")
    })
    @RequestMapping(value = "/addMainMenu", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateMainMenu(@RequestBody List<String> menuIds) {
        Map<String, Object> map = new HashMap<>();
        boolean response = false;
        List<SysUserMenu> menuList = new ArrayList<>();

        if (menuIds != null && menuIds.size() > 0) {
            for (String menuId: menuIds) {
                SysUserMenu userMenu = userMenuService.selectById(Integer.parseInt(menuId));
                userMenu.setIsShow(1);
                menuList.add(userMenu);
            }

            response = userMenuService.updateAllColumnBatchById(menuList);
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
