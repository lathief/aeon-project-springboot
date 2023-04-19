package com.XYZ.Karyawan.controller;

import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.Training;
import com.XYZ.Karyawan.entity.response.Response;
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
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Response result = trainingService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Response> createTraining(@RequestBody Training training){
        Response result = trainingService.createTraining(training);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTraining(@PathVariable Long id, @RequestBody Training training){
        Response result = trainingService.updateTraining(id ,training);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<Response> searchTraining(@RequestParam(required = false) String Tema,
                                                   @RequestParam(required = false) String NamaPengajar,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page, size);
        Response result = trainingService.searchTraining(
                Tema, NamaPengajar, paging);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTraining(@PathVariable Long id){
        Response result = trainingService.deleteTraining(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
