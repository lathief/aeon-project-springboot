package com.XYZ.Karyawan.service;

import com.XYZ.Karyawan.entity.Karyawan;
import com.XYZ.Karyawan.entity.Training;
import com.XYZ.Karyawan.entity.response.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface KaryawanTrainingService {
    Response daftar(Long karyawan_id, Long training_id);
    Response search(String karyawan, String training, Pageable pageable);
}
