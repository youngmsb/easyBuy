package com.duanzq.easyBuy.controller;

import com.duanzq.easyBuy.bean.ItemCatResult;
import com.duanzq.easyBuy.service.ItemCatService;
import com.duanzq.easyBuy.util.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by duanzq on 16/6/29.
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /*produces指定返回的文件类型及编码*/
    @RequestMapping(value = "/item/cat/list", produces = MediaType.APPLICATION_JSON_VALUE + ";charSet=UTF-8")
    @ResponseBody
    public String getItemCatList(String callback){
        /*默认返回的数据中文乱码,而且contentType是html,需要设置成application/json*/
        ItemCatResult itemCatResult = itemCatService.getItemCatList();
        String result = JSONUtils.toJSon(itemCatResult);
        if (!StringUtils.isEmpty(callback)){
            result = callback + "(" + result + ");";
        }
        return result;
    }

    @RequestMapping(value = "/item/cat/list2")
    @ResponseBody
    public Object getItemCatList2(String callback){
        /*默认返回的数据中文乱码,而且contentType是html,需要设置成application/json*/
        ItemCatResult itemCatResult = itemCatService.getItemCatList();
        String result = JSONUtils.toJSon(itemCatResult);
        if (StringUtils.isEmpty(callback)){
            return itemCatResult;
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }
}
