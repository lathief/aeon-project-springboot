package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.DetailTraining;
import com.XYZ.Karyawan.entity.Training;
import com.XYZ.Karyawan.entity.exception.NotFoundException;
import com.XYZ.Karyawan.entity.request.TrainingDTO;
import com.XYZ.Karyawan.entity.response.ResponseGlobal;
import com.XYZ.Karyawan.repository.DetailTrainingRepo;
import com.XYZ.Karyawan.repository.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TrainingServiceImpl implements TrainingService{
    @Autowired
    TrainingRepo trainingRepo;
    @Autowired
    DetailTrainingRepo detailTrainingRepo;
    public ResponseGlobal findById(Long id) {
        if (!trainingRepo.existsById(id)) {
            throw new NotFoundException("Training with id " + id + " Didn't exists");
        }
        return new ResponseGlobal(trainingRepo.findById(id), HttpStatus.OK);
    }

    public ResponseGlobal createTraining(TrainingDTO createtraining) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Training training = new Training();
            training.setTema(createtraining.getTema());
            training.setNamaPengajar(createtraining.getNamapengajar());

            DetailTraining detailTraining = new DetailTraining();
            detailTraining.setStartTraining(format.parse(createtraining.getStarttraining()));
            detailTraining.setEndTraining(format.parse(createtraining.getStarttraining()));
            detailTraining.setNumSeat(createtraining.getNumseat());
            detailTraining.setLimitSeat(createtraining.getLimitseat());
            detailTraining.setDescription(createtraining.getDescription());
            detailTraining.setTraining(training);
            training.setDetailTraining(detailTraining);

            trainingRepo.save(training);
            detailTrainingRepo.save(detailTraining);
            return new ResponseGlobal("Create training success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseGlobal updateTraining(Long id, TrainingDTO updateTraining) {
        if (!trainingRepo.existsById(id)) {
            throw new NotFoundException("Training with id " + id + " Didn't exists");
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Training training = trainingRepo.findById(id).get();
            if (updateTraining.getNamapengajar() == null) {
                updateTraining.setNamapengajar(training.getNamaPengajar());
            } if (updateTraining.getTema() == null) {
                updateTraining.setTema(training.getTema());
            }
            trainingRepo.updateTraining(id, updateTraining.getTema(), updateTraining.getNamapengajar(), new Date().toInstant());
            DetailTraining detailTraining = detailTrainingRepo.findById(id).get();
            if (updateTraining.getStarttraining() == null) {
                updateTraining.setStarttraining(String.valueOf(detailTraining.getStartTraining()));
            } if (updateTraining.getEndtraining() == null) {
                updateTraining.setEndtraining(String.valueOf(detailTraining.getEndTraining()));
            } if (updateTraining.getNumseat() == null) {
                updateTraining.setNumseat(detailTraining.getNumSeat());
            } if (updateTraining.getLimitseat() == null) {
                updateTraining.setLimitseat(detailTraining.getLimitSeat());
            } if (updateTraining.getDescription() == null) {
                updateTraining.setDescription(detailTraining.getDescription());
            }
            detailTrainingRepo.updateTraining(id, format.parse(updateTraining.getStarttraining()), format.parse(updateTraining.getEndtraining()),
                    updateTraining.getNumseat(), updateTraining.getLimitseat(), updateTraining.getDescription());
            return new ResponseGlobal("Update training success", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseGlobal updateDetailTraining(Long id, TrainingDTO detailTraining) {
        if (!trainingRepo.existsById(id)) {
            throw new NotFoundException("Training with id " + id + " Didn't exists");
        }
        try {
            DetailTraining getDetail = detailTrainingRepo.findById(id).get();
            if (detailTraining.getStarttraining() == null) {
                detailTraining.setStarttraining(String.valueOf(getDetail.getStartTraining()));
            } if (detailTraining.getEndtraining() == null) {
                detailTraining.setEndtraining(String.valueOf(getDetail.getEndTraining()));
            } if (detailTraining.getNumseat() == null) {
                detailTraining.setNumseat(getDetail.getNumSeat());
            } if (detailTraining.getLimitseat() == null) {
                detailTraining.setLimitseat(getDetail.getLimitSeat());
            } if (detailTraining.getDescription() == null) {
                detailTraining.setDescription(getDetail.getDescription());
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            detailTrainingRepo.updateTraining(id, format.parse(detailTraining.getStarttraining()), format.parse(detailTraining.getEndtraining()),
                    detailTraining.getNumseat(), detailTraining.getLimitseat(), detailTraining.getDescription());
            return new ResponseGlobal("Update training success", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseGlobal searchTraining(String tema, String namaPengajar, Pageable pageable) {
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
            return new ResponseGlobal(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseGlobal deleteTraining(Long id) {
        if (!trainingRepo.existsById(id)) {
            throw new NotFoundException("training with id " + id + " Didn't exists");
        }
        try {
            trainingRepo.deleteById(id);
            return new ResponseGlobal("Delete training success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
