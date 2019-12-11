package apap.tugas.sidok.service;

import apap.tugas.sidok.model.PoliModel;
import apap.tugas.sidok.repository.PoliDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliServiceImpl implements PoliService{

    @Autowired
    PoliDb poliDb;

    @Override
    public void addPoli(PoliModel poli){
        poliDb.save(poli);
    }

    @Override
    public Optional<PoliModel> getPoliById(Long id){
        return poliDb.findById(id);
    }

    @Override
    public List<PoliModel> getPoliList(){return poliDb.findAll();}

    @Override
    public PoliModel changePoli(PoliModel poli){
        PoliModel targetPoli = poliDb.findById(poli.getId()).get();

        try{
            targetPoli.setNama(poli.getNama());
            targetPoli.setLokasi(poli.getLokasi());
            poliDb.save(targetPoli);
            return targetPoli;
        } catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deletePoli(PoliModel poli){
        poliDb.delete(poli);
    }
}
