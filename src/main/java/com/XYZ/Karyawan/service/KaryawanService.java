package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.DetailKaryawan;
import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.response.ResponseGlobal;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface KaryawanService {
    ResponseGlobal findById(Long id);
    ResponseGlobal createKaryawan(Karyawan karyawan);
    ResponseGlobal updateKaryawan(Long id, Karyawan karyawan);
    ResponseGlobal searchKaryawanByNama(String nama, Pageable pageable);
    ResponseGlobal deleteKaryawan(Long id);
    ResponseGlobal updateDetail(Long id, DetailKaryawan detailKaryawan);
}
