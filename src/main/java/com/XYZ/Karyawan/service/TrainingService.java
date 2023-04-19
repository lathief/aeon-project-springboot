package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.Training;
import com.XYZ.Karyawan.entity.request.TrainingDTO;
import com.XYZ.Karyawan.entity.response.ResponseGlobal;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TrainingService {
    ResponseGlobal findById(Long id);
    ResponseGlobal createTraining(TrainingDTO training);
    ResponseGlobal updateTraining(Long id, TrainingDTO training);
    ResponseGlobal updateDetailTraining(Long id, TrainingDTO detailTraining);
    ResponseGlobal searchTraining(String tema, String namaPengajar, Pageable pageable);
    ResponseGlobal deleteTraining(Long id);
}
