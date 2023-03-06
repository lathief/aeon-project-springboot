package com.XYZ.Karyawan.entity;


import com.XYZ.Karyawan.entity.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Karyawan extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private java.util.Date tanggalLahir;
    private String alamat;
    @Column(name = "jenis_kelamin")
    private String jenisKelamin;
    private String nama;
    private String status;
    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Rekening> rekening;
    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Karyawan_Training> karyawanTrainings;
    @OneToOne
    @JoinColumn(name = "id_karyawan")
    private DetailKaryawan detailKaryawan;
}

