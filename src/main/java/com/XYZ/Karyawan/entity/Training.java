package com.XYZ.Karyawan.entity;

import com.XYZ.Karyawan.entity.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Training extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="nama_pengajar")
    private String namaPengajar;
    private String tema;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_training")
    private DetailTraining detailTraining;
    @JsonIgnore
    @OneToMany(mappedBy = "training", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<KaryawanTraining> karyawanTrainings;
}
