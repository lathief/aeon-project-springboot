package com.XYZ.Karyawan.entity;

import com.XYZ.Karyawan.entity.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Rekening extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String jenis;
    private String nama;
    private String nomor;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "karyawan_id")
    private Karyawan karyawan;
}
