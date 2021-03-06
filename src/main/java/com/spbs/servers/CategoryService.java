package com.spbs.servers;

import com.spbs.common.ServerSponse;
import com.spbs.entity.Category;

import java.util.List;

public interface CategoryService {
    ServerSponse<String> addCategoryItems(String cateName, Integer cateId);

    ServerSponse<String> updateCategoryItems(String cateName, Integer categoryId);

    ServerSponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerSponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

    ServerSponse<String> deleteCategoryItems(Integer categoryId);

    ServerSponse<List<Category>> listCategory(Integer pageNum, Integer pageSize);

    ServerSponse<List<Category>> listCategorys();
}
