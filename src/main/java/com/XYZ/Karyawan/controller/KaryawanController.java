package com.XYZ.Karyawan.controller;

import com.XYZ.Karyawan.entity.DetailKaryawan;
import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.response.ResponseGlobal;
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
    public ResponseEntity<ResponseGlobal> findById(@PathVariable Long id) {
        ResponseGlobal result = karyawanService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<ResponseGlobal> createKaryawan(@RequestBody Karyawan karyawan){
        ResponseGlobal result = karyawanService.createKaryawan(karyawan);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseGlobal> updateKaryawan(@PathVariable Long id, @RequestBody Karyawan karyawan){
        ResponseGlobal result = karyawanService.updateKaryawan(id, karyawan);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PutMapping("/{id}/detail")
    public ResponseEntity<ResponseGlobal> updateDetailKaryawan(@PathVariable Long id, @RequestBody DetailKaryawan detailKaryawan){
        ResponseGlobal result = karyawanService.updateDetail(id, detailKaryawan);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<ResponseGlobal> searchKaryawan(@RequestParam(required = false) String nama,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page, size);
        ResponseGlobal result = karyawanService.searchKaryawanByNama(
                nama, paging);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGlobal> deleteKaryawan(@PathVariable Long id){
        ResponseGlobal result = karyawanService.deleteKaryawan(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
