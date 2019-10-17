package apap.tugas.sidok.service;

import apap.tugas.sidok.model.SpesialisasiModel;
import apap.tugas.sidok.repository.SpesialisasiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpesialisasiServiceImpl implements SpesialisasiService {

    @Autowired
    private SpesialisasiDb spesialisasiDb;

    @Override
    public void addSpesialisasi(SpesialisasiModel spesialisasi){
        spesialisasiDb.save(spesialisasi);
    }


    @Override
    public Optional<SpesialisasiModel> getSpesialisasiById(Long id){return spesialisasiDb.findById(id);}
}
