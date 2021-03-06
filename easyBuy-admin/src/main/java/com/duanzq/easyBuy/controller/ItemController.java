package com.duanzq.easyBuy.controller;

import com.duanzq.easyBuy.model.TbItem;
import com.duanzq.easyBuy.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by duanzq on 16/6/5.
 */
@Controller
public class ItemController {
    @Resource
    private ItemService itemService;

    @RequestMapping("/test")
    public String selectByPrimaryKey(Long id){
        TbItem tbItem = itemService.selectByPrimaryKey(536563L);
        System.out.println(tbItem.getBarcode());
        return "123";
    }

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

    /**
     * 映射所有页面请求
     * @param page 页面name
     * @return
     */
    @RequestMapping("/showPage/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
