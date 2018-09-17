package com.spbs.servers.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.spbs.common.ServerSponse;
import com.spbs.dao.CategoryMapper;
import com.spbs.entity.Category;
import com.spbs.servers.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Service("iCategoryService")
public class ICategoryImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMappers;

    @Override
    public ServerSponse<String> addCategoryItems(String cateName, Integer cateId) {
        if (cateId == null || StringUtils.isBlank(cateName)) {
            return ServerSponse.createByErrorMessage("参数错误，需要仔细核对是否输入");
        }
        Category category = new Category();
        category.setName(cateName);
        category.setParentId(cateId);
        category.setStatus(true);//这个分类是可用的
        int count = categoryMappers.insert(category);
        if (count > 0) {
            return ServerSponse.createBySuccessMessage("添加商品分类成功");
        }
        return ServerSponse.createBySuccessMessage("添加商品失败");
    }

    @Override
    public ServerSponse<String> updateCategoryItems(String cateName, Integer categoryId) {
        if (categoryId == null || StringUtils.isBlank(cateName)) {
            return ServerSponse.createByErrorMessage("参数错误，需要仔细核对是否输入");
        }
        Category category = new Category();
        category.setName(cateName);
        category.setId(categoryId);
        int count = categoryMappers.updateByPrimaryKeySelective(category);
        if (count > 0) {
            return ServerSponse.createBySuccessMessage("更新商品分类成功");
        }
        return ServerSponse.createBySuccessMessage("更新商品失败");
    }

    public ServerSponse<List<Category>> getChildrenParallelCategory(Integer pare_id) {
        List<Category> list = categoryMappers.selectCategoryChildrenByParentId(pare_id);
        if (list != null) {
            return ServerSponse.createBySuccess(list);
        }
        return ServerSponse.createBySuccessMessage("当前数据为空");
    }

    public ServerSponse<List<Category>> listCategory(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Category> list = categoryMappers.selectList();
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(list);
        return ServerSponse.createBySuccess(list);
    }

    @Override
    public ServerSponse<List<Category>> listCategorys() {
        List<Category> list = categoryMappers.selectList();
        return ServerSponse.createBySuccess(list);
    }

    //递归查询本节点的id及孩子节点的id
    public ServerSponse<List<Integer>> selectCategoryAndChildrenById(Integer pare_id) {
        Set<Category> newSet = Sets.newHashSet();
        findChildCategory(newSet, pare_id);
        List<Integer> cateIdList = Lists.newArrayList();
        if (pare_id != null) {
            for (Category items : newSet) {
                cateIdList.add(items.getId());
            }
        }
        return ServerSponse.createBySuccess(cateIdList);
    }

    @Override
    public ServerSponse<String> deleteCategoryItems(Integer categoryId) {
        int count = categoryMappers.deleteByPrimaryKey(categoryId);
        if (count > 0) {
            System.out.println(categoryId);
            return ServerSponse.createBySuccessMessage("删除成功");
        }
        return ServerSponse.createByErrorMessage("删除失败");
    }

    //递归算法,算出子节点
    private Set<Category> findChildCategory(Set<Category> set, Integer find_id) {
        Category category = categoryMappers.selectByPrimaryKey(find_id);
        System.out.println(category);
        if (category != null) {
            set.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> list = categoryMappers.selectCategoryChildrenByParentId(find_id);
        System.out.println(list);
        for (Category item : list) {
            System.out.println(item.getId() + "  " + item.getName());
            findChildCategory(set, item.getId());
        }
        return set;
    }
}
