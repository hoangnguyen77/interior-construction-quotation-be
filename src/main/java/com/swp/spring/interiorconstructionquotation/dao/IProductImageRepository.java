package com.swp.spring.interiorconstructionquotation.dao;

import com.swp.spring.interiorconstructionquotation.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "product-image")
public interface IProductImageRepository extends JpaRepository<ProductImage, Integer> {
    public List<ProductImage> findByProduct_ProductId(int productId);
}
