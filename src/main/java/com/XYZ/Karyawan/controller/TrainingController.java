package com.XYZ.Karyawan.controller;

import com.XYZ.Karyawan.entity.Training;
import com.XYZ.Karyawan.entity.request.TrainingDTO;
import com.XYZ.Karyawan.entity.response.ResponseGlobal;
import com.XYZ.Karyawan.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/training")
public class TrainingController {
    @Autowired
    TrainingService trainingService;
    @GetMapping("/{id}")
    public ResponseEntity<ResponseGlobal> findById(@PathVariable Long id) {
        ResponseGlobal result = trainingService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<ResponseGlobal> createTraining(@RequestBody TrainingDTO training){
        ResponseGlobal result = trainingService.createTraining(training);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseGlobal> updateTraining(@PathVariable Long id, @RequestBody TrainingDTO training){
        ResponseGlobal result = trainingService.updateTraining(id ,training);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}/detail")
    public ResponseEntity<ResponseGlobal> updateDetailTraining(@PathVariable Long id, @RequestBody TrainingDTO training){
        ResponseGlobal result = trainingService.updateDetailTraining(id ,training);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseGlobal> searchTraining(@RequestParam(required = false) String Tema,
                                                         @RequestParam(required = false) String NamaPengajar,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page, size);
        ResponseGlobal result = trainingService.searchTraining(
                Tema, NamaPengajar, paging);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGlobal> deleteTraining(@PathVariable Long id){
        ResponseGlobal result = trainingService.deleteTraining(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
