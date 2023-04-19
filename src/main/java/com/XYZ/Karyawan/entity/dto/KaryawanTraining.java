package com.XYZ.Karyawan.entity.dto;

import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.Training;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

public class KaryawanTraining {
    private Long id;
    private Date tanggalTraining;
    private Karyawan karyawan;
    private Training training;
}
