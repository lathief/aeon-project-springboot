package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.DetailKaryawan;
import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.exception.NotFoundException;
import com.XYZ.Karyawan.entity.response.ResponseGlobal;
import com.XYZ.Karyawan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    public ResponseGlobal findById(Long id) {
        if (!karyawanRepo.existsById(id)) {
            throw new NotFoundException("Karyawan with id " + id + " Didn't exists");
        }
        return new ResponseGlobal(karyawanRepo.findById(id), HttpStatus.OK);
    }
    public ResponseGlobal createKaryawan(Karyawan karyawan) {
       try {
           DetailKaryawan detailKaryawan = new DetailKaryawan("","");
           karyawan.setDetailKaryawan(detailKaryawan);
           karyawanRepo.save(karyawan);
           detailKaryawanRepo.save(detailKaryawan);
           return new ResponseGlobal("Create karyawan success", HttpStatus.CREATED);
       }
       catch (Exception e) {
           return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    public ResponseGlobal updateKaryawan(Long id, Karyawan karyawan) {
        if (!karyawanRepo.existsById(id)) {
            throw new NotFoundException("Karyawan with id " + id + " Didn't exists");
        } else {
            try {
                DetailKaryawan detail = detailKaryawanRepo.findById(id).get();
                if (karyawan.getDetailKaryawan() != null){
                    if (karyawan.getDetailKaryawan().getNpwp() == null) {
                        karyawan.getDetailKaryawan().setNpwp(detail.getNpwp());
                    } if (karyawan.getDetailKaryawan().getNik() == null) {
                        karyawan.getDetailKaryawan().setNik(detail.getNik());
                    }
                    detailKaryawanRepo.updateDetail(karyawan.getId(), karyawan.getDetailKaryawan().getNik(), karyawan.getDetailKaryawan().getNpwp());
                }
                Karyawan updateKaryawan = karyawanRepo.findById(id).get();
                if (karyawan.getNama() == null)  {
                    karyawan.setNama(updateKaryawan.getNama());
                } if (karyawan.getStatus() == null) {
                    karyawan.setStatus(updateKaryawan.getStatus());
                } if (karyawan.getAlamat() == null) {
                    karyawan.setAlamat(updateKaryawan.getAlamat());
                } if (karyawan.getJenisKelamin() == null) {
                    karyawan.setJenisKelamin(updateKaryawan.getJenisKelamin());
                } if (karyawan.getTanggalLahir() == null) {
                    karyawan.setTanggalLahir(updateKaryawan.getTanggalLahir());
                }
                karyawanRepo.updateKaryawan(id, karyawan.getNama(), karyawan.getStatus(), karyawan.getAlamat(), karyawan.getTanggalLahir(), karyawan.getJenisKelamin(), new Date().toInstant());
                return new ResponseGlobal("Update karyawan success", HttpStatus.OK);
            }
            catch (Exception e) {
                return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    public ResponseGlobal searchKaryawanByNama(String nama, Pageable pageable){
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
            return new ResponseGlobal(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseGlobal deleteKaryawan(Long id) {
        if (!karyawanRepo.existsById(id)) {
            throw new NotFoundException("Karyawan with id " + id + " Didn't exists");
        }
        try {
            karyawanRepo.deleteById(id);
            return new ResponseGlobal("Delete karyawan success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseGlobal updateDetail(Long id, DetailKaryawan detailKaryawan) {
        if (!karyawanRepo.existsById(id)) {
            throw new NotFoundException("Karyawan with id " + id + " Didn't exists");
        }
        try {
            DetailKaryawan detail = detailKaryawanRepo.findById(id).get();
            if (detailKaryawan.getNpwp() == null) {
                detailKaryawan.setNpwp(detail.getNpwp());
            } if (detailKaryawan.getNik() == null) {
                detailKaryawan.setNik(detail.getNik());
            }
            detailKaryawanRepo.updateDetail(id, detailKaryawan.getNik(), detailKaryawan.getNpwp());
            karyawanRepo.updateKaryawan(id, new Date().toInstant());
            return new ResponseGlobal("Update detail success", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseGlobal(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
