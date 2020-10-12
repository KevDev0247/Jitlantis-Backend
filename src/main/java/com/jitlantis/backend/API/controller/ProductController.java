package com.jitlantis.backend.API.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.model.Product;
import com.jitlantis.backend.API.service.ProductService;
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
import java.util.List;
import java.util.Map;

/**
 * The controller for Product that handles HTTP requests and responses.
 * In this frontend-backend-separated architecture,
 * the controller interacts with the particular service on the frontend.
 *
 * @author Kevin Zhijun Wang
 * @see Product
 * created on 2020/07/22
 */
@Api(tags = {"product"})
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "create product")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "product", value = "Product entity", required = true, dataType = "Product")
    })
    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
        Map<String, Object> map = new HashMap<>();
        boolean response = productService.insert(product);
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "update product")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "product", value = "Product entity", required = true, dataType = "Product")
    })
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateProduct(@RequestBody Product product) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Product productRetrieved = productService.selectById(product.getId());
        if (productRetrieved == null) {
            response = false;
        } else {
            response = productService.updateById(product);
        }
        map.put("data", response);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "delete product")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, paramType = "query", name = "productId", value = "product id")
    })
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteProduct(Integer productId) {
        Map<String, Object> map = new HashMap<>();
        boolean response;

        Product productRetrieved = productService.selectById(productId);
        if (productRetrieved == null) {
            response = false;
            map.put("data", response);
            map.put("message", "deletion failed, the product does not exist!");

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        productRetrieved.setIsDelete(DeletedEnum.Y.value());
        response = productService.updateById(productRetrieved);

        if (response) {
            map.put("message", "deletion successful");
        } else {
            map.put("message", "deletion failed");
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation("query product option list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "Product Name"),
    })
    @RequestMapping(value = "/optionList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryClientOptionList (@RequestParam(value = "name", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        List<BaseItemDto> baseItemDtoList = productService.optionList(name);
        map.put("data", baseItemDtoList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "query product list", notes = "no pagination")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dept", value = "department"),
            @ApiImplicitParam(paramType = "query", name = "type", value = "type"),
            @ApiImplicitParam(paramType = "query", name = "serial_no", value = "serial_no"),
    })
    @RequestMapping(value = "/queryProductList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> queryProductList(
            @RequestParam(value = "dept", required = false) String dept,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "serial_no", required = false) String serial_no) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<Product> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(dept)) {
            wrapper.eq("dept", dept);
        }
        if (StringUtils.isNotBlank(type)) {
            wrapper.eq("type", type);
        }
        if (StringUtils.isNotBlank(serial_no)) {
            wrapper.eq("serial_no", serial_no);
        }

        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        map.put("list", productService.selectList(wrapper));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "product detail")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getContact(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Product product = productService.selectById(id);
        map.put("data", product);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
