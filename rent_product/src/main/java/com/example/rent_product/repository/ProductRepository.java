package com.example.rent_product.repository;

import com.example.rent_product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    public List<ProductEntity> findAllByStatusActiveInactiveIsTrue();
//    public ProductEntity findProductEntityByDiscountAmount(String s);

}
