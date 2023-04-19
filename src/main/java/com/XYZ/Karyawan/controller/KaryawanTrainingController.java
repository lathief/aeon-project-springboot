package com.XYZ.Karyawan.controller;

import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.Training;
import com.XYZ.Karyawan.entity.response.Response;
import com.XYZ.Karyawan.service.KaryawanTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/training-karyawan")
public class KaryawanTrainingController {
    @Autowired
    KaryawanTrainingService karyawanTrainingService;
    @PostMapping("/daftar")
    public ResponseEntity<Response> daftarTraining(@RequestParam Long karyawan_id, @RequestParam Long training_id){
        Response result = karyawanTrainingService.daftar(karyawan_id, training_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<Response> searchTrainingKaryawan(@RequestParam(required = false) String karyawan,
                                                           @RequestParam(required = false) String training,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page, size);
        Response result = karyawanTrainingService.search(
                karyawan, training, paging);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
