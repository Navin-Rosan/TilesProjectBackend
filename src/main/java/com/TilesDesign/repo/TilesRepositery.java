package com.TilesDesign.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TilesDesign.entity.TilesEntity;

@Repository
public interface TilesRepositery extends JpaRepository<TilesEntity, Long> {
	TilesEntity findByName(String name);
	TilesEntity findByEmail(String email);
}
