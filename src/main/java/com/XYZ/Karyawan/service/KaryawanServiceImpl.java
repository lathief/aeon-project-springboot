package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.DetailKaryawan;
import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.Rekening;
import com.XYZ.Karyawan.entity.exception.NotFoundException;
import com.XYZ.Karyawan.entity.response.Response;
import com.XYZ.Karyawan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanRepo karyawanRepo;
    @Autowired
    DetailKaryawanRepo detailKaryawanRepo;
    @Autowired
    KaryawanTrainingRepo karyawanTrainingRepo;
    @Autowired
    RekeningRepo rekeningRepo;
    public Response findById(Long id) {
        if (!karyawanRepo.existsById(id)) {
            throw new NotFoundException("Karyawan with id " + id + " Didn't exists");
        }
        return new Response(karyawanRepo.findById(id), HttpStatus.OK);
    }
    public Response createKaryawan(Karyawan karyawan) {
        DetailKaryawan detailKaryawan = new DetailKaryawan("","");
        karyawan.setDetailKaryawan(detailKaryawan);
        karyawanRepo.save(karyawan);
        detailKaryawanRepo.save(detailKaryawan);
        return new Response("Create karyawan success", HttpStatus.CREATED);
    }
    public Response updateKaryawan(Long id, Karyawan karyawan) {
        if (!karyawanRepo.existsById(id)) {
            throw new NotFoundException("Karyawan with id " + id + " Didn't exists");
        } else {
            if (karyawan.getDetailKaryawan() != null){
                detailKaryawanRepo.updateDetail(karyawan.getId(), karyawan.getDetailKaryawan().getNik(), karyawan.getDetailKaryawan().getNpwp());
            }
            karyawanRepo.updateKaryawan(id, karyawan.getNama(), karyawan.getStatus(), karyawan.getAlamat(), karyawan.getTanggalLahir(), karyawan.getJenisKelamin(), new Date().toInstant());
            return new Response("Update karyawan success", HttpStatus.CREATED);
        }
    }
    public Response searchKaryawanByNama(String nama, Pageable pageable){
        try {
            List<Karyawan> karyawans = new ArrayList<Karyawan>();
            Page<Karyawan> karyawanPage;
            if (nama == null) {
                karyawanPage = karyawanRepo.findAll(pageable);
            } else {
                karyawanPage = karyawanRepo.findByNamaContaining(nama, pageable);
            }
            karyawans = karyawanPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("karyawan", karyawans);
            response.put("currentPage", karyawanPage.getNumber());
            response.put("totalItems", karyawanPage.getTotalElements());
            response.put("totalPages", karyawanPage.getTotalPages());
            return new Response(response, HttpStatus.OK);
        } catch (Exception e){
            return new Response(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Response deleteKaryawan(Long id) {
        if (!karyawanRepo.existsById(id)) {
            throw new NotFoundException("Karyawan with id " + id + " Didn't exists");
        }
        karyawanRepo.deleteById(id);
        return new Response("Delete karyawan success", HttpStatus.OK);
    }

    public Response updateDetail(Long id, DetailKaryawan detailKaryawan) {
        if (!karyawanRepo.existsById(id)) {
            throw new NotFoundException("Karyawan with id " + id + " Didn't exists");
        }
        detailKaryawanRepo.updateDetail(id, detailKaryawan.getNik(), detailKaryawan.getNpwp());
        karyawanRepo.updateKaryawan(id, new Date().toInstant());
        return new Response("Update detail success", HttpStatus.OK);
    }

}
