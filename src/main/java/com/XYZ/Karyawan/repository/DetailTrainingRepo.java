package com.XYZ.Karyawan.repository;

import com.XYZ.Karyawan.entity.DetailTraining;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.util.Date;

@Repository
public interface DetailTrainingRepo extends JpaRepository<DetailTraining, Long> {
    @JsonIgnore
    @Query(nativeQuery = true, value = "UPDATE detail_training SET start_training=?2, end_training=?3, num_seat=?4, limit_seat=?5, description=?6 WHERE id=?1")
    @Modifying
    @Transactional
    void updateTraining(Long id, Date startTraining, Date endTraining, Integer numSeat, Integer limitSeat, String description);
}
