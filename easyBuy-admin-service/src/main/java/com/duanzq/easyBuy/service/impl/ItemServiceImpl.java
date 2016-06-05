package com.duanzq.easyBuy.service.impl;

import com.duanzq.easyBuy.mapper.TbItemMapper;
import com.duanzq.easyBuy.model.TbItem;
import com.duanzq.easyBuy.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by duanzq on 16/6/5.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem selectByPrimaryKey(Long id){
        return tbItemMapper.selectByPrimaryKey(id);
    }
}
