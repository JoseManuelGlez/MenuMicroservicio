package com.example.menumicroservicio.persistances.repositories;

import com.example.menumicroservicio.persistances.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMenuRepository extends JpaRepository <Menu, Long> {

}
