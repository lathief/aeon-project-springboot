package com.XYZ.Karyawan.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "detail_karyawan")
@NoArgsConstructor
public class DetailKaryawan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nik;
    private String npwp;

    public DetailKaryawan(String nik, String npwp) {
        this.nik = nik;
        this.npwp = npwp;
    }
}
