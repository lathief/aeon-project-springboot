package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.Training;
import com.XYZ.Karyawan.entity.response.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TrainingService {
    Response findById(Long id);
    Response createTraining(Training training);
    Response updateTraining(Long id, Training training);
    Response searchTraining(String tema, String namaPengajar, Pageable pageable);
    Response deleteTraining(Long id);
}
