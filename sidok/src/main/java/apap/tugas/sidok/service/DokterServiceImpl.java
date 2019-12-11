package apap.tugas.sidok.service;

import apap.tugas.sidok.model.*;
import apap.tugas.sidok.repository.DokterDb;
import apap.tugas.sidok.repository.JadwalJagaDb;
import apap.tugas.sidok.repository.SpesialisasiDokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class DokterServiceImpl implements DokterService{
    @Autowired
    private DokterDb dokterDb;

    @Autowired
    private JadwalJagaDb jadwalJagaDb;

    @Autowired
    private SpesialisasiDokterDb spesialisasiDokterDb;

    @Override
    public void addDokter(DokterModel dokter){
        dokterDb.save(dokter);
    }

    @Override
    public List<DokterModel> getDokterList(){return dokterDb.findAll();}

    @Override
    public DokterModel getDokterById(Long id){return dokterDb.findById(id).get();}

    @Override
    public DokterModel getDokterByNip(String nip){return dokterDb.findByNip(nip).get();}

    @Override
    public DokterModel getDokterByNik(String nik){return dokterDb.findByNik(nik).get();}

    @Override
    public DokterModel changeDokter(DokterModel dokter){
        DokterModel targetDokter = dokterDb.findById(dokter.getId()).get();

        try{
            targetDokter.setNama(dokter.getNama());
            targetDokter.setTanggal_lahir(dokter.getTanggal_lahir());
            targetDokter.setTempat_lahir(dokter.getTempat_lahir());
            targetDokter.setJenis_kelamin(dokter.getJenis_kelamin());
            dokterDb.save(targetDokter);
            return targetDokter;
        } catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deleteDokter(DokterModel dokter){
        dokterDb.delete(dokter);
    }

    @Override
    public String generateNip(DokterModel dokter){
        String nip="";

        Date currDate = new Date();
        Calendar curr = Calendar.getInstance();
        curr.setTime(currDate);
        nip += String.valueOf(curr.get(Calendar.YEAR)+5);

        String tglLahir = String.valueOf(dokter.getTanggal_lahir());
        nip += tglLahir.substring(8,10);
        nip += tglLahir.substring(5,7);
        nip += tglLahir.substring(0,4);

        nip += String.valueOf(dokter.getJenis_kelamin());

        int batasBawah = 97;
        int batasAtas = 122;
        Random random = new Random();
        StringBuffer randomChar = new StringBuffer(2);
        for(int i=0; i<2; i++){
            int nextChar = batasBawah + (int)(random.nextFloat()*(batasAtas-batasBawah+1));
            randomChar.append((char) nextChar);
        }nip += randomChar;
        nip = nip.toUpperCase();

        return nip;
    }

    @Override
    public List<JadwalJagaModel> getJadwal(DokterModel dokter){
        return jadwalJagaDb.getByDokter(dokter);
    }

    @Override
    public List<SpesialisasiModel> getSpesialisasiDokter(DokterModel dokter){
        List<SpesialisasiModel> result = new ArrayList<>();
        List<SpesialisasiDokterModel> listSpeDok = spesialisasiDokterDb.getByDokter(dokter);
        for(SpesialisasiDokterModel x : listSpeDok){
            result.add(x.getSpesialisasi());
        }return result;
    }

    @Override
    public List<DokterModel> getTopDokterInPoli(PoliModel poli) {
        List<JadwalJagaModel> listJadwalJaga = poli.getListJadwalJaga();
        List<DokterModel> listJadwalJagaDokter = new ArrayList<>();
        for(JadwalJagaModel dokterJadwal: listJadwalJaga){
            listJadwalJagaDokter.add(dokterJadwal.getDokter());
        }

        try{
            Map<DokterModel,Integer> mapping = new HashMap<>();
            for(DokterModel jadwalDokter: listJadwalJagaDokter){
                Integer x = mapping.get(jadwalDokter);
                mapping.put(jadwalDokter, x == null ? 1 : x+1);
            }
            Map.Entry<DokterModel,Integer> max = null;
            for(Map.Entry<DokterModel, Integer> findMax : mapping.entrySet()){
                if(max==null || findMax.getValue() > max.getValue()) {
                    max = findMax;
                }
            }
            int valueMax = max.getValue();

            List<DokterModel> topDokterList = new ArrayList<>();
            for(Map.Entry<DokterModel, Integer> findDokter : mapping.entrySet()){
                if(findDokter.getValue() == valueMax){
                    topDokterList.add(findDokter.getKey());
                }
            }
            return topDokterList;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
