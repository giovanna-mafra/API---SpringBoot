package com.example.springboot.repositories;


import com.example.springboot.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository // --> explicita que é um Bean gerenciado pelo Spring
// Jpa --> passa o Model(ProductModel) e o tipo de ID(UUID)
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
