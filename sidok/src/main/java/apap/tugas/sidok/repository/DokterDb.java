package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long> {
    Optional<DokterModel> findById(Long idDokter);
    List<DokterModel> findAll();
    Optional<DokterModel> findByNip(String nip);
    Optional<DokterModel> findByNik(String nik);
}
