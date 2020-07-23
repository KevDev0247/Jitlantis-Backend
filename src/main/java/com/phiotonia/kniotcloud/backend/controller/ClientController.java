package com.phiotonia.kniotcloud.backend.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.phiotonia.kniotcloud.backend.model.Client;
import com.phiotonia.kniotcloud.backend.model.ClientProduct;
import com.phiotonia.kniotcloud.backend.service.ClientProductService;
import com.phiotonia.kniotcloud.backend.service.ClientService;
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

@Api(tags = {"client"})
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientProductService clientProductService;

    @ApiOperation(value = "create client")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "client", value = "Client entity", required = true, dataType = "Client")
    })
    @RequestMapping(value = "/createClient", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createClient(@RequestBody Client client) {
        Map<String, Object> map = new HashMap<>();
        boolean response = clientService.insert(client);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update client")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "client", value = "Client entity", required = true, dataType = "Client")
    })
    @RequestMapping(value = "/updateClient", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateClient(@RequestBody Client client) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Client clientRetrieved = clientService.selectById(client.getId());
        if (clientRetrieved == null) {
            response = false;
        } else {
            response = clientService.updateById(client);
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

        Client clientRetrieved = clientService.selectById(clientId);
        if (clientRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the client does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        clientRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = clientService.updateById(clientRetrieved);

        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "create client product")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ClientProduct", value = "ClientProductEntity", required = true, dataType = "ClientProduct")
    })
    @RequestMapping(value = "/createClientProduct", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createClientProduct(@RequestBody ClientProduct clientProduct) {
        Map<String, Object> map = new HashMap<>();
        boolean response;
        response = clientProductService.insert(clientProduct);
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
}
