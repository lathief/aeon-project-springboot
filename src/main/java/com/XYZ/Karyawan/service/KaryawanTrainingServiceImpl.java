package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.KaryawanTraining;
import com.XYZ.Karyawan.entity.exception.NotFoundException;
import com.XYZ.Karyawan.entity.response.ResponseGlobal;
import com.XYZ.Karyawan.repository.KaryawanRepo;
import com.XYZ.Karyawan.repository.KaryawanTrainingRepo;
import com.XYZ.Karyawan.repository.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KaryawanTrainingServiceImpl implements KaryawanTrainingService{
    @Autowired
    KaryawanTrainingRepo karyawanTrainingRepo;
    @Autowired
    TrainingRepo trainingRepo;
    @Autowired
    KaryawanRepo karyawanRepo;
    public ResponseGlobal daftar(Long karyawan_id, Long training_id) {
        KaryawanTraining karyawanTraining = new KaryawanTraining();
        if(!karyawanRepo.existsById(karyawan_id) || !trainingRepo.existsById(training_id)){
            throw new NotFoundException("Karyawan atau training tidak ada");
        } else {
            karyawanTraining.setKaryawan(karyawanRepo.findById(karyawan_id).get());
            karyawanTraining.setTraining(trainingRepo.findById(training_id).get());
            karyawanTrainingRepo.save(karyawanTraining);
            return new ResponseGlobal("Berhasil mendaftar", HttpStatus.OK);
        }
    }

    public ResponseGlobal search(String karyawan, String training, Pageable pageable) {
        try {
            List<?> data = new ArrayList<>();
            Map<String, Object> response = new HashMap<>();
            Page<?> dataPage;
            if (karyawan == null && training == null) {
                dataPage = karyawanTrainingRepo.findAll(pageable);
            } else if (karyawan != null && training == null) {
                dataPage = karyawanRepo.findByNamaContaining(karyawan, pageable);
            } else if (training != null && karyawan == null) {
                dataPage = trainingRepo.findByTemaContaining(training, pageable);
            } else {
                Map<String, Object> result = new HashMap<>();
                dataPage = trainingRepo.findByTemaContaining(training, pageable);
                result.put("Training", dataPage.getContent());
                dataPage = karyawanRepo.findByNamaContaining(karyawan, pageable);
                result.put("Karyawan", dataPage.getContent());
                return new ResponseGlobal(result, HttpStatus.OK);
            }
            data = dataPage.getContent();
            response.put("result", data);
            response.put("currentPage", dataPage.getNumber());
            response.put("totalItems", dataPage.getTotalElements());
            response.put("totalPages", dataPage.getTotalPages());
            return new ResponseGlobal(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
