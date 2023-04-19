package com.XYZ.Karyawan.repository;

import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Repository
public interface TrainingRepo extends JpaRepository<Training, Long> {
    @Query(nativeQuery = true, value = "UPDATE training SET updated_at=?4, tema=?2, nama_pengajar=?3 WHERE id=?1")
    @Modifying
    @Transactional
    void updateTraining(Long id, String tema, String nama_pengajar, Instant updated_at);
    Page<Training> findByTemaAndNamaPengajarContaining(String tema, String namaPengajar, Pageable pageable);
    Page<Training> findByTemaContaining(String tema, Pageable pageable);
}
