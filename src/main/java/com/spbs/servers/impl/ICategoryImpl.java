package com.spbs.servers.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.spbs.common.ServerSponse;
import com.spbs.dao.CategoryMapper;
import com.spbs.dao.UserMapper;
import com.spbs.entity.Category;
import com.spbs.servers.CategoryServer;
import net.sf.jsqlparser.schema.Server;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ICategoryImpl implements CategoryServer {
    @Autowired
    private CategoryMapper categoryMappers;

    public ServerSponse<List<Category>> getChildrenParallelCategory(Integer pare_id){
        List<Category> list=categoryMappers.selectCategoryChildrenByParentId(pare_id);
        if (list!=null){
            return ServerSponse.createBySuccess(list);
        }
        return ServerSponse.createBySuccessMessage("当前数据为空");
    }


    //递归查询本节点的id及孩子节点的id
    public ServerSponse<List<Integer>> selectCategoryAndChildrenById(Integer pare_id){
        Set<Category> newSet=Sets.newHashSet();
        findChildCategory(newSet,pare_id);
        List<Integer> cateIdList=Lists.newArrayList();
        if (pare_id!=null){
            for (Category items:newSet){
                cateIdList.add(items.getId());
            }
        }
        return ServerSponse.createBySuccess(cateIdList);

    }

    //递归算法,算出子节点
    private Set<Category> findChildCategory(Set<Category> set,Integer find_id){
        Category category=categoryMappers.selectByPrimaryKey(find_id);
        if (category!=null){
            set.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> list=categoryMappers.selectCategoryChildrenByParentId(find_id);
        for(Category item:list){
            findChildCategory(set,item.getId());
        }
        return set;
    }
}
