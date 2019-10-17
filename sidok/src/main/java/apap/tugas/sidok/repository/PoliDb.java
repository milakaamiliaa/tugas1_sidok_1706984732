package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.PoliModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PoliDb extends JpaRepository<PoliModel, Long> {
    Optional<PoliModel> findById(Long idPoli);
    List<PoliModel> findAll();

}
