package com.example.cat_project.Cat.Repository;

import com.example.cat_project.Cat.Entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

}
