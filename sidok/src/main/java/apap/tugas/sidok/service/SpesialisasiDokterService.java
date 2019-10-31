package apap.tugas.sidok.service;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.SpesialisasiDokterModel;
import apap.tugas.sidok.model.SpesialisasiModel;

import java.util.List;
import java.util.Optional;

public interface SpesialisasiDokterService {
    List<SpesialisasiDokterModel> getDokterBySpesialisasi(SpesialisasiModel spesialisasi);
    void addSpesialisasi(SpesialisasiDokterModel spesialisasiDokterModel);
    List<SpesialisasiDokterModel> getAllSpesialisasiDokter();
}
