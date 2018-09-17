package com.spbs.servers.impl;

import com.spbs.common.*;
import com.spbs.dao.CategoryMapper;
import com.spbs.dao.ProductMapper;
import com.spbs.entity.Category;
import com.spbs.entity.Product;
import com.spbs.servers.ProductService;
import com.spbs.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iProductService")
public class IProductImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    public ServerSponse<String> updateProductStatus(Integer status) {

        return null;
    }

    public ServerSponse<ProductDetailVo> productDetail(Integer id) {
        if (id == null) {
            return ServerSponse.createByErrorCodeMessage(Sta_Type.ILLEGAL_ARGUMENT.getCode(), "非法的参数传递");
        }
        Product product = productMapper.selectByPrimaryKey(id);
        if (product == null) {
            return ServerSponse.createByErrorMessage("产品不存在,或者以被删除");
        }
        if (product.getStatus() != Coust.ProductStatusEnum.ON_SALE.getCode()) {
            return ServerSponse.createByErrorMessage("产品不存在,或者以被删除");
        }
        ProductDetailVo plv = new ProductDetailVo();
        plv = productVoDetail(product);
        return ServerSponse.createBySuccess(plv);
    }

    private ProductDetailVo productVoDetail(Product product) {
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setSubImages(product.getSubImages());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStatus(product.getStatus());
        productDetailVo.setStock(product.getStock());

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "ftp://192.168.5.46/"));

        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if (category == null) {
            productDetailVo.setParentCategoryId(0);//默认根节点
        } else {
            productDetailVo.setParentCategoryId(category.getParentId());
        }

        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }
}
