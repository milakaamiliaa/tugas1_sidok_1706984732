package apap.tugas.sidok.service;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.SpesialisasiDokterModel;
import apap.tugas.sidok.model.SpesialisasiModel;
import apap.tugas.sidok.repository.SpesialisasiDokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpesialisasiDokterServiceImpl implements SpesialisasiDokterService {

    @Autowired
    private SpesialisasiDokterDb spesialisasiDokterDb;

    @Override
    public List<SpesialisasiDokterModel> getDokterBySpesialisasi(SpesialisasiModel spesialisasi) {
        return spesialisasiDokterDb.findBySpesialisasi(spesialisasi);
    }

    @Override
    public void addSpesialisasi(SpesialisasiDokterModel spesialisasiDokterModel) {
        spesialisasiDokterDb.save(spesialisasiDokterModel);
    }

    @Override
    public List<SpesialisasiDokterModel> getAllSpesialisasiDokter() {
        return spesialisasiDokterDb.findAll();
    }

}

