package com.canister.ecommerce.category;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryRepository
 */
public interface CategoryRepository extends JpaRepository<CategoryModel,UUID>{

}
