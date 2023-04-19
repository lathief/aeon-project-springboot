package com.XYZ.Karyawan.entity.request;

import lombok.Data;

import java.util.Date;

@Data
public class TrainingDTO {
    private String namapengajar;
    private String tema;
    private String starttraining;
    private String endtraining;
    private Integer numseat;
    private Integer limitseat;
    private String description;
}
