package apap.tugas.sidok.service;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.JadwalJagaModel;
import apap.tugas.sidok.model.PoliModel;
import apap.tugas.sidok.model.SpesialisasiModel;

import java.util.List;
import java.util.Optional;

public interface DokterService {
    void addDokter(DokterModel dokter);
    DokterModel getDokterById(Long idDokter);
    DokterModel getDokterByNip(String nip);
    DokterModel getDokterByNik(String nik);
    DokterModel changeDokter(DokterModel dokterModel);
    void deleteDokter(DokterModel dokterModel);
    List<DokterModel> getDokterList();
    String generateNip(DokterModel dokter);
    List<JadwalJagaModel> getJadwal(DokterModel dokter);
    List<SpesialisasiModel> getSpesialisasiDokter(DokterModel dokter);
    List<DokterModel> getTopDokterInPoli(PoliModel poli);
}
