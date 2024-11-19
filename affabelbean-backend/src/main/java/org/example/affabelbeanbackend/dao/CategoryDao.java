package org.example.affabelbeanbackend.dao;

import org.example.affabelbeanbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {
}
