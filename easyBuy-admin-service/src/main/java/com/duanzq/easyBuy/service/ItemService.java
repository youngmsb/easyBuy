package com.duanzq.easyBuy.service;

import com.duanzq.easyBuy.model.TbItem;

/**
 * Created by duanzq on 16/6/4.
 */
public interface ItemService {

    TbItem selectByPrimaryKey(Long id);
}
