package com.XYZ.Karyawan.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Rekening extends DataDate{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String jenis;
    private String nama;
    private String nomor;

    @ManyToOne
    @JoinColumn(name = "karyawan_id")
    private Karyawan karyawan;
}
