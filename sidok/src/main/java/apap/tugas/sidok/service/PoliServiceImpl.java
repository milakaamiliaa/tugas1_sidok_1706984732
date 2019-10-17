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
}
