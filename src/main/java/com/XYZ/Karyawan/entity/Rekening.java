package com.XYZ.Karyawan.entity;

import com.XYZ.Karyawan.entity.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Rekening extends DateAudit {
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
