package apap.tugas.sidok.service;

import apap.tugas.sidok.model.JadwalJagaModel;
import apap.tugas.sidok.repository.JadwalJagaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService {
    @Autowired
    private JadwalJagaDb jadwalJagaDb;

    @Override
    public void addJadwalJaga(JadwalJagaModel jadwalJaga) {
        jadwalJagaDb.save(jadwalJaga);
    }

    @Override
    public List<JadwalJagaModel> getJadwalJagaList() {
        return jadwalJagaDb.findAll();
    }
}
