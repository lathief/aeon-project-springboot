package com.XYZ.Karyawan.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Training extends DataDate{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="nama_pengajar")
    private String namaPengajar;
    private String tema;
    @OneToMany(mappedBy = "training", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Karyawan_Training> karyawanTrainings;
}
