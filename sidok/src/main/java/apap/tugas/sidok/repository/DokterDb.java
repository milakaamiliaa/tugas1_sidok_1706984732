package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DokterDb extends JpaRepository<DokterModel, Long> {
    Optional<DokterModel> findById(Long idDokter);
    List<DokterModel> findAll();
}
