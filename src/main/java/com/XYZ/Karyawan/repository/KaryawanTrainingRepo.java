package com.XYZ.Karyawan.repository;

import com.XYZ.Karyawan.entity.KaryawanTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanTrainingRepo extends JpaRepository<KaryawanTraining, Long> {
}
