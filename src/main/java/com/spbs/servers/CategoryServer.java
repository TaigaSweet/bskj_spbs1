package com.spbs.servers;

import com.spbs.common.ServerSponse;
import com.spbs.entity.Category;

import java.util.List;

public interface CategoryServer {
    ServerSponse<String> addCategoryItems(String cateName,Integer cateId);
    ServerSponse<String> updateCategoryItems(String cateName,Integer categoryId);
    ServerSponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerSponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
    ServerSponse<String> deleteCategoryItems(Integer categoryId);
}
