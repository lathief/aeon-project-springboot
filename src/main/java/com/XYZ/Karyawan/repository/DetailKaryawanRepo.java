package com.XYZ.Karyawan.repository;

import com.XYZ.Karyawan.entity.DetailKaryawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DetailKaryawanRepo extends JpaRepository<DetailKaryawan, Long> {
    @Query(nativeQuery = true, value = "UPDATE detail_karyawan SET nik=?2, npwp=?3 WHERE id = ?1")
    @Modifying
    @Transactional
    void updateDetail(Long id, String NIK, String NPWP);
}
