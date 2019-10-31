package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.SpesialisasiDokterModel;
import apap.tugas.sidok.model.SpesialisasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpesialisasiDokterDb extends JpaRepository<SpesialisasiDokterModel, Long> {
    Optional<SpesialisasiDokterModel> findById(Long id);
    List<SpesialisasiDokterModel> findBySpesialisasi(SpesialisasiModel spesialisasi);
    List<SpesialisasiDokterModel> getByDokter(DokterModel dokter);
    List<SpesialisasiDokterModel> findAll();

}
