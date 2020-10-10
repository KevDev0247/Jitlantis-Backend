package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.base.JitConverter;
import com.jitlantis.backend.API.dao.ContactDao;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.model.Contact;
import com.jitlantis.backend.API.model.SysUser;
import com.jitlantis.backend.API.service.ContactService;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl extends ServiceImpl<ContactDao, Contact> implements ContactService {

    @Autowired
    private JitConverter jitConverter;

    @Autowired
    private ContactDao contactDao;

    @Override
    public List<BaseItemDto> optionList(String name) {
        EntityWrapper<Contact> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        List<Contact> userList = this.selectList(wrapper);

        List<BaseItemDto> baseItemDtoList = jitConverter.mergeListByAny(BaseItemDto.class, userList, null, null);
        if (baseItemDtoList == null || baseItemDtoList.size() == 0) {
            baseItemDtoList = new ArrayList<>();
        }

        return baseItemDtoList;
    }

    @Override
    public Contact selectById(Integer id) {
        return contactDao.selectById(id);
    }

    @Override
    public List<Contact> findAllByIds(List<Long> contactIds) {
        EntityWrapper<Contact> wrapper = new EntityWrapper<>();
        wrapper.in("id", contactIds);
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id", true);

        return this.selectList(wrapper);
    }
}
