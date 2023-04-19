package com.XYZ.Karyawan.repository;

import com.XYZ.Karyawan.entity.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
public interface KaryawanRepo extends JpaRepository<Karyawan, Long> {
    @Query(nativeQuery = true, value = "UPDATE karyawan SET nama=?2, status=?3, alamat=?4, tanggal_lahir=?5, jenis_kelamin=?6, updated_at=?7 WHERE id=?1")
    @Modifying
    @Transactional
    void updateKaryawan(Long id, String nama, String status, String alamat, Date birthDate, String gender, Instant updated_at);

    @Query(nativeQuery = true, value = "UPDATE karyawan SET updated_at=?2 WHERE id=?1")
    @Modifying
    @Transactional
    void updateKaryawan(Long id, Instant updated_at);
    Page<Karyawan> findByNamaContaining(String name, Pageable pageable);
}
