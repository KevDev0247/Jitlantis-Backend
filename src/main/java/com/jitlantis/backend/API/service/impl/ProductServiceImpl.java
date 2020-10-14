package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.base.JitConverter;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.model.Contact;
import com.jitlantis.backend.API.model.SysUser;
import com.jitlantis.backend.API.service.ProductService;
import com.jitlantis.backend.API.dao.ProductDao;
import com.jitlantis.backend.API.model.Product;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the Service interface for system user
 *
 * @author Kevin Zhijun Wang, Yonggang Su
 * created on 2020/07/27
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

    @Autowired
    private JitConverter jitConverter;

    @Autowired
    private ProductDao productDao;

    @Override
    public List<BaseItemDto> optionList(String name) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        List<Product> userList = this.selectList(wrapper);

        List<BaseItemDto> baseItemDtoList = jitConverter.mergeListByAny(BaseItemDto.class, userList, null, null);
        if (baseItemDtoList == null || baseItemDtoList.size() == 0) {
            baseItemDtoList = new ArrayList<>();
        }

        return baseItemDtoList;
    }

    @Override
    public Product selectById(Integer id) {
        return productDao.selectById(id);
    }

    @Override
    public List<Product> findAllByIds(List<Long> productIds) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.in("id", productIds);
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id", true);

        return this.selectList(wrapper);
    }
}
