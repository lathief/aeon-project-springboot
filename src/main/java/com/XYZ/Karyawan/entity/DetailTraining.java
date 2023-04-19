package com.XYZ.Karyawan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "detail_training")
public class DetailTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date startTraining;
    @Temporal(TemporalType.DATE)
    private Date endTraining;
    private Integer numSeat;
    private Integer limitSeat;
    @Column(columnDefinition = "TEXT")
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
