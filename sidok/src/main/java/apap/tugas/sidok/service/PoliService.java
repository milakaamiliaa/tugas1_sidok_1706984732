package apap.tugas.sidok.service;

import apap.tugas.sidok.model.PoliModel;

import java.util.List;
import java.util.Optional;

public interface PoliService {
    void addPoli(PoliModel poli);
    Optional<PoliModel> getPoliById(Long idPoli);
    List<PoliModel> getPoliList();
    PoliModel changePoli(PoliModel poli);
    void deletePoli(PoliModel poli);
}
