package com.XYZ.Karyawan.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "detail_karyawan")
public class DetailKaryawan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nik;
    private String npwp;
    @OneToOne
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;
}
