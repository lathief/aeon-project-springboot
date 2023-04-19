package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.DetailKaryawan;
import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.response.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface KaryawanService {
    Response findById(Long id);
    Response createKaryawan(Karyawan karyawan);
    Response updateKaryawan(Long id, Karyawan karyawan);
    Response searchKaryawanByNama(String nama, Pageable pageable);
    Response deleteKaryawan(Long id);
    Response updateDetail(Long id, DetailKaryawan detailKaryawan);
}
