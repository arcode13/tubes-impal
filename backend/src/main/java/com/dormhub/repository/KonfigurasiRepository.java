package com.dormhub.repository;

import com.dormhub.model.Konfigurasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KonfigurasiRepository extends JpaRepository<Konfigurasi, Integer> {
}
