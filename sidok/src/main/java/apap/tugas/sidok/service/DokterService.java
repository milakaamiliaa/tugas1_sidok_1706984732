package apap.tugas.sidok.service;

import apap.tugas.sidok.model.DokterModel;

import java.util.List;
import java.util.Optional;

public interface DokterService {
    void addDokter(DokterModel dokter);
    Optional<DokterModel> getDokterById(Long idDokter);
    DokterModel changeDokter(DokterModel dokterModel);
    void deleteDokter(DokterModel dokterModel);
    List<DokterModel> getDokterList();
}
