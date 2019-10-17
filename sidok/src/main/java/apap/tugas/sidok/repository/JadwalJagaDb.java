package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.JadwalJagaModel;
import apap.tugas.sidok.model.PoliModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long> {
    List<JadwalJagaModel> getByDokter(DokterModel dokter);
    List<JadwalJagaModel> getByPoli(PoliModel poli);
}
