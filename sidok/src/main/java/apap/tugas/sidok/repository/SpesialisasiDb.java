package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.SpesialisasiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpesialisasiDb extends JpaRepository<SpesialisasiModel, Long> {
    List<SpesialisasiModel> findAll();
}
