package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.response.ResponseGlobal;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface KaryawanTrainingService {
    ResponseGlobal daftar(Long karyawan_id, Long training_id);
    ResponseGlobal search(String karyawan, String training, Pageable pageable);
}
