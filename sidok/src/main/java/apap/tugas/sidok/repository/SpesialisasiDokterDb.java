package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.SpesialisasiDokterModel;
import apap.tugas.sidok.model.SpesialisasiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpesialisasiDokterDb extends JpaRepository<SpesialisasiDokterModel, Long> {
    List<SpesialisasiDokterModel> getByDokter(DokterModel dokter);
    List<SpesialisasiDokterModel> getBySpesialisasi(SpesialisasiModel spesialisasi);

}
