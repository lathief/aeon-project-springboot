package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.Training;
import com.XYZ.Karyawan.entity.exception.NotFoundException;
import com.XYZ.Karyawan.entity.response.Response;
import com.XYZ.Karyawan.repository.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainingServiceImpl implements TrainingService{
    @Autowired
    TrainingRepo trainingRepo;
    public Response findById(Long id) {
        if (!trainingRepo.existsById(id)) {
            throw new NotFoundException("Training with id " + id + " Didn't exists");
        }
        return new Response(trainingRepo.findById(id), HttpStatus.OK);
    }

    public Response createTraining(Training training) {
        trainingRepo.save(training);
        return new Response("Create training success", HttpStatus.CREATED);
    }

    public Response updateTraining(Long id, Training training) {
        if (!trainingRepo.existsById(id)) {
            throw new NotFoundException("Training with id " + id + " Didn't exists");
        }
        trainingRepo.updateTraining(id, training.getTema(), training.getNamaPengajar(), new Date().toInstant());
        return new Response("Update training success", HttpStatus.CREATED);
    }

    public Response searchTraining(String tema, String namaPengajar, Pageable pageable) {
        try {
            List<Training> trainings = new ArrayList<Training>();
            Page<Training> trainingPage;
            if (tema == null && namaPengajar == null) {
                trainingPage = trainingRepo.findAll(pageable);
            } else {
                trainingPage = trainingRepo.findByTemaAndNamaPengajarContaining(tema, namaPengajar, pageable);
            }
            trainings = trainingPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("trainings", trainings);
            response.put("currentPage", trainingPage.getNumber());
            response.put("totalItems", trainingPage.getTotalElements());
            response.put("totalPages", trainingPage.getTotalPages());
            return new Response(response, HttpStatus.OK);
        } catch (Exception e){
            return new Response(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Response deleteTraining(Long id) {
        if (!trainingRepo.existsById(id)) {
            throw new NotFoundException("training with id " + id + " Didn't exists");
        }
        trainingRepo.deleteById(id);
        return new Response("Delete training success", HttpStatus.OK);
    }
}
