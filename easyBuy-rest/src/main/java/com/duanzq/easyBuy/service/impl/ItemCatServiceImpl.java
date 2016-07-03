package com.duanzq.easyBuy.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.duanzq.easyBuy.bean.ItemCatBean;
import com.duanzq.easyBuy.bean.ItemCatResult;
import com.duanzq.easyBuy.mapper.TbItemCatMapper;
import com.duanzq.easyBuy.model.TbItemCat;
import com.duanzq.easyBuy.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duanzq on 16/6/29.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public ItemCatResult getItemCatList() {
        ItemCatResult itemCatResult = new ItemCatResult();
        //递归调用获取所有商品类别
        List data = getItemCatList(0l);
        itemCatResult.setData(data);
        return itemCatResult;
    }

    private List getItemCatList(long parentId) {
        List resultList = new ArrayList();
        //查询出当前节点的信息
        List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByParentId(parentId);
        //遍历所有节点
        for (TbItemCat tbItemCat: tbItemCatList){
            ItemCatBean itemCatBean = new ItemCatBean();
            //父节点
            if (tbItemCat.getIsParent()){
                itemCatBean.setUrl("/products/" + tbItemCat.getId() + ".html");
                //根节点
                if (tbItemCat.getParentId() == 0){
                    itemCatBean.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                } else {
                    itemCatBean.setName(tbItemCat.getName());
                }
                //递归
                itemCatBean.setItems(getItemCatList(tbItemCat.getId()));
                resultList.add(itemCatBean);
            } else {
                String item = "/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
                resultList.add(item);
            }
        }

        return resultList;
    }
}
