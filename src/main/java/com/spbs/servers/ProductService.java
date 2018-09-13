package com.spbs.servers;

import com.spbs.common.ServerSponse;
import com.spbs.vo.ProductDetailVo;

public interface ProductService {
    public ServerSponse<ProductDetailVo> productDetail(Integer id);
}
