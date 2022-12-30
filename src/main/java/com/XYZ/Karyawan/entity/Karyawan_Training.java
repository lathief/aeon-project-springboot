package com.XYZ.Karyawan.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Karyawan_Training extends DataDate{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="tanggal_training")
    @Temporal(TemporalType.DATE)
    private Date tanggalTraining;

    @ManyToOne
    @JoinColumn(name = "karyawan_id")
    private Karyawan karyawan;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
