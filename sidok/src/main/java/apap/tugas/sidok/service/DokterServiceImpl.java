package apap.tugas.sidok.service;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DokterServiceImpl implements DokterService{

    @Autowired
    private DokterDb DokterDb;

    @Override
    public void addDokter(DokterModel dokter){
        DokterDb.save(dokter);
    }

    @Override
    public List<DokterModel> getDokterList(){return DokterDb.findAll();}

    @Override
    public Optional<DokterModel> getDokterById(Long id){return DokterDb.findById(id);}



    @Override
    public DokterModel changeDokter(DokterModel dokter){
        DokterModel targetDokter = DokterDb.findById(dokter.getId()).get();

        try{
            targetDokter.setNama(dokter.getNama());
            targetDokter.setTanggal_lahir(dokter.getTanggal_lahir());
            targetDokter.setTempat_lahir(dokter.getTempat_lahir());
            targetDokter.setJenis_kelamin(dokter.getJenis_kelamin());
            DokterDb.save(targetDokter);
            return targetDokter;
        } catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deleteDokter(DokterModel dokter){
        DokterDb.delete(dokter);
    }

}
