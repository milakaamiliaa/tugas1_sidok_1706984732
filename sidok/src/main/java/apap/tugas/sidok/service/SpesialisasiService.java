package apap.tugas.sidok.service;

import apap.tugas.sidok.model.SpesialisasiModel;

import java.util.Optional;

public interface SpesialisasiService {
    void addSpesialisasi(SpesialisasiModel spesialisasiModel);
    Optional<SpesialisasiModel> getSpesialisasiById(Long idSpesialisasi);
}
