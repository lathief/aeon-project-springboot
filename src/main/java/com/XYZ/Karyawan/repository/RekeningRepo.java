package com.XYZ.Karyawan.repository;

import com.XYZ.Karyawan.entity.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeningRepo extends JpaRepository<Rekening, Long> {
}
