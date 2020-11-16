package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.annotation.MyLog;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.dto.MainMenuDto;
import com.jitlantis.backend.API.model.*;
import com.jitlantis.backend.API.service.ClientProductService;
import com.jitlantis.backend.API.service.CtaFollowService;
import com.jitlantis.backend.API.service.SysUserMenuService;
import com.jitlantis.backend.API.service.SysUserService;
import com.jitlantis.backend.API.utils.StringUtils;
import com.jitlantis.backend.API.utils.DeletedEnum;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * The controller for Client that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang, Yonggang Su
 * @see SysUser
 * @see Client
 * @see ClientProduct
 * @see CtaFollow
 * created on 2020/07/21
 */
@Api(tags = {"client"})
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientProductService clientProductService;

    @Autowired
    private CtaFollowService ctaFollowService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserMenuService userMenuService;

    @ApiOperation(value = "create client")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "client", value = "Client entity", required = true, dataType = "SysUser")
    })
    @RequestMapping(value = "/createClient", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createClient(@RequestBody SysUser client) {
        Map<String, Object> map = new HashMap<>();
        boolean response = userService.insert(client);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update client")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "client", value = "Client entity", required = true, dataType = "SysUser")
    })
    @RequestMapping(value = "/updateClient", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateClient(@RequestBody SysUser client) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysUser clientRetrieved = userService.selectById(client.getId());
        if (clientRetrieved == null) {
            response = false;
        } else {
            response = userService.updateById(client);
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete client")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "clientId", value = "client id")
    })
    @RequestMapping(value = "/deleteClient", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteClient(Integer clientId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        SysUser clientRetrieved = userService.selectById(clientId);
        if (clientRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the client does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        clientRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = userService.updateById(clientRetrieved);

        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "get client details")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getClient(Integer id) {
        Map<String, Object> map = new HashMap<>();
        SysUser client = userService.selectById(id);
        map.put("data", client);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query client list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "Name"),
            @ApiImplicitParam(paramType = "query", name = "area", value = "Area"),
    })
    @RequestMapping(value = "/queryClientList", method = RequestMethod.GET)
    @MyLog(value = "query clients list")
    public ResponseEntity<Map<String, Object>> queryClientList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "area", required = false) String area) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userService.selectClientQueryList(name, area));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation("query client option list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "Client Name"),
    })
    @RequestMapping(value = "/optionList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryClientOptionList (@RequestParam(value = "name", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        List<BaseItemDto> baseItemDtoList = userService.optionList(name);
        map.put("data", baseItemDtoList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "create client product")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientProduct", value = "ClientProduct entity", required = true, dataType = "ClientProduct")
    })
    @RequestMapping(value = "/createClientProduct", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createClientProduct(@RequestBody ClientProduct clientProduct) {
        Map<String, Object> map = new HashMap<>();
        boolean response;
        response = clientProductService.insert(clientProduct);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete client product")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "clientProductId", value = "ClientProduct id")
    })
    @RequestMapping(value = "/deleteClientProduct", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteClientProduct(Integer clientProductId) {
        Map<String, Object> map = new HashMap<>();
        ClientProduct clientProductRetrieved = clientProductService.selectById(clientProductId);
        boolean response;

        if (clientProductRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the client does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        clientProductRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = clientProductService.updateById(clientProductRetrieved);
        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query client product list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "info", value = "key words"),
            @ApiImplicitParam(paramType = "query", name = "brand", value = "brand"),
    })
    @RequestMapping(value = "/queryClientProductList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryClientProductList(
            @RequestParam(value = "info", required = false) String info,
            @RequestParam(value = "brand", required = false) String brand) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<ClientProduct> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(info)) {
            wrapper.eq("place", info);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", clientProductService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "create Cta follow")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ctaFollow", value = "ctaFollow entity", required = true, dataType = "CtaFollow")
    })
    @RequestMapping(value = "/createCtaFollow", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createCtaFollow(@RequestBody CtaFollow ctaFollow) {
        Map<String, Object> map = new HashMap<>();
        boolean response = ctaFollowService.insert(ctaFollow);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query Cta Follow list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "type", value = "type"),
            @ApiImplicitParam(paramType = "query", name = "contact_date", value = "contact date"),
            @ApiImplicitParam(paramType = "query", name = "staff_id", value = "staff id"),
    })
    @RequestMapping(value = "/queryCtaFollowList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryCtaFollowList(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "contact_date", required = false) Date contact_date,
            @RequestParam(value = "staff_id", required = false) String staff_id) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<CtaFollow> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(type)) {
            wrapper.eq("type", type);
        }
        if (contact_date != null) {
            wrapper.eq("date", contact_date);
        }
        if (StringUtils.isNotBlank(staff_id)) {
            wrapper.eq("type", staff_id);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", ctaFollowService.selectList(wrapper));

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

    @ApiOperation(value = "updateMainMenu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuIds", value = "Menu Ids", required = true, dataType = "List<String>")
    })
    @RequestMapping(value = "updateMainMenu", method = RequestMethod.POST)
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
