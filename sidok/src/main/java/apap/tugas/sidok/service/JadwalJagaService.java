package apap.tugas.sidok.service;

import apap.tugas.sidok.model.JadwalJagaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JadwalJagaService {
    void addJadwalJaga(JadwalJagaModel jadwalJaga);
    List<JadwalJagaModel> getJadwalJagaList();
}
