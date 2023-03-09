package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.exception.NotFoundException;
import com.XYZ.Karyawan.entity.response.Response;
import com.XYZ.Karyawan.repository.DetailKaryawanRepo;
import com.XYZ.Karyawan.repository.KaryawanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanRepo karyawanRepo;
    @Autowired
    DetailKaryawanRepo detailKaryawanRepo;
    public Response findById(Long id) {
        if (!karyawanRepo.existsById(id)) {
            throw new NotFoundException("Karyawan with id " + id + " Didn't exists");
        }
        return new Response(karyawanRepo.findById(id), HttpStatus.OK);
    }
    public Response createKaryawan(Karyawan karyawan) {
        karyawanRepo.save(karyawan);
        return new Response("Create karyawan success", HttpStatus.CREATED);
    }
    public Response updateKaryawan(Karyawan karyawan) {
        if (karyawan.getDetailKaryawan() != null){
            detailKaryawanRepo.updateDetail(karyawan.getId(), karyawan.getDetailKaryawan().getNik(), karyawan.getDetailKaryawan().getNpwp());
        }
        karyawanRepo.updateKaryawan(karyawan.getId(), karyawan.getNama(), karyawan.getStatus(), karyawan.getAlamat(), karyawan.getTanggalLahir().toString(), karyawan.getJenisKelamin());
        return new Response("Update karyawan success", HttpStatus.CREATED);
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
            response.put("tutorials", karyawans);
            response.put("currentPage", karyawanPage.getNumber());
            response.put("totalItems", karyawanPage.getTotalElements());
            response.put("totalPages", karyawanPage.getTotalPages());
            return new Response(response, HttpStatus.OK);
        } catch (Exception e){
            return new Response(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
