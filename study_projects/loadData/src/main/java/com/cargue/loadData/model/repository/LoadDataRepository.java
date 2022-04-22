package com.cargue.loadData.model.repository;

import com.cargue.loadData.model.entity.LoadData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoadDataRepository extends JpaRepository<LoadData, Long> {
}
