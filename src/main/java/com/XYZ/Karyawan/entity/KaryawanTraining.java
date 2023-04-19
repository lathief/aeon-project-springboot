package com.XYZ.Karyawan.entity;

import com.XYZ.Karyawan.entity.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class KaryawanTraining extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="tanggal_training")
    @Temporal(TemporalType.DATE)
    private Date tanggalTraining;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "karyawan_id")
    private Karyawan karyawan;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
