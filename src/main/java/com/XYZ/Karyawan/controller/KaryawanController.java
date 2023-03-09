package com.XYZ.Karyawan.controller;

import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.response.Response;
import com.XYZ.Karyawan.service.KaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/v1/karyawan")
public class KaryawanController {
    @Autowired
    KaryawanService karyawanService;
    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Response result = karyawanService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Response> createKaryawan(Karyawan karyawan){
        Response result = karyawanService.createKaryawan(karyawan);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<Response> updateKaryawan(Karyawan karyawan){
        Response result = karyawanService.updateKaryawan(karyawan);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<Response> searchKaryawan(@RequestParam(required = false) String nama,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page, size);
        Response result = karyawanService.searchKaryawanByNama(
                nama, paging);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
