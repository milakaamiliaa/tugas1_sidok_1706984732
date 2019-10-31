package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.*;
import apap.tugas.sidok.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DokterController {
    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Autowired
    private SpesialisasiService spesialisasiService;

    @Autowired
    private SpesialisasiDokterService spesialisasiDokterService;

    @Autowired
    private PoliService poliService;

    @Autowired
    private JadwalJagaService jadwalJagaService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        List<DokterModel> listDokter = dokterService.getDokterList();
        List<SpesialisasiDokterModel> listSpesialisasiDokter = spesialisasiDokterService.getAllSpesialisasiDokter();
        model.addAttribute("listDokter", listDokter);
        return "home";

    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.GET)
    public String tambahDokterForm(Model model){
        DokterModel newDokter = new DokterModel();
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();
        List<SpesialisasiDokterModel> listSpesialisasiDokterBaru = new ArrayList<>();
        listSpesialisasiDokterBaru.add(new SpesialisasiDokterModel());

        newDokter.setListSpesialisasiDokter(listSpesialisasiDokterBaru);

        model.addAttribute("listSpesialisasi", listSpesialisasi);
        model.addAttribute("dokter", newDokter);
        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST, params = "tambahSpesialisasi")
    public String addSpesialisasiRow(@ModelAttribute DokterModel dokter, Model model){
        SpesialisasiDokterModel spesialisasiDokter = new SpesialisasiDokterModel();
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();

        dokter.getListSpesialisasiDokter().add(spesialisasiDokter);

        model.addAttribute("listSpesialisasi", listSpesialisasi);
        model.addAttribute("dokter", dokter);

        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST)
    public String tambahDokterSubmit(@ModelAttribute DokterModel dokter, Model model){
        List<SpesialisasiDokterModel> listSpesialisasiDokter = dokter.getListSpesialisasiDokter();
        for(SpesialisasiDokterModel x : listSpesialisasiDokter){
            x.setDokter(dokter);
        }

        System.out.println(dokter.getNama());

        dokter.setNip(dokterService.generateNip(dokter));
        dokterService.addDokter(dokter);
        for(SpesialisasiDokterModel x : listSpesialisasiDokter){
            spesialisasiDokterService.addSpesialisasi(x);
        }

        model.addAttribute("namaDokter", dokter.getNama());
        model.addAttribute("nipDokter", dokter.getNip());
        return "add-dokter";
    }

    @RequestMapping(value = "/dokter/{nik}", method = RequestMethod.GET)
    public String viewDokterByNik(@PathVariable String nik, Model model){
        DokterModel dokter = dokterService.getDokterByNik(nik);
        List<SpesialisasiDokterModel> listSpesialisasi = dokter.getListSpesialisasiDokter();

        model.addAttribute("dokter", dokter);
        model.addAttribute("listSpesialisasi", listSpesialisasi);

        return "view-dokter";
    }

    @RequestMapping(value = "/dokter/update/{id}", method = RequestMethod.GET)
    public String changeDokterForm(@PathVariable Long id, Model model){
        DokterModel existingDokter = dokterService.getDokterById(id);
        model.addAttribute("dokter", existingDokter);
        return "form-change-dokter";
    }

    @RequestMapping(value = "/dokter/update/{id}", method = RequestMethod.POST)
    public String changeDokterSubmit(@PathVariable Long id, @ModelAttribute DokterModel dokter, Model model){
        DokterModel newDokter = dokterService.changeDokter(dokter);
        List<SpesialisasiDokterModel> listSpesialisasi = newDokter.getListSpesialisasiDokter();
        model.addAttribute("dokter", newDokter);
        model.addAttribute("listSpesialisasi", listSpesialisasi);
        return "view-dokter";
    }

    @RequestMapping(value = "/dokter/delete/{id}", method = RequestMethod.GET)
    public String deleteDokter(@PathVariable Long id, Model model){
        DokterModel targetDokter = dokterService.getDokterById(id);
        model.addAttribute("dokter", targetDokter);
        dokterService.deleteDokter(targetDokter);
        return "deleted-dokter";
    }

    @RequestMapping(path = "/cari", method = RequestMethod.GET)
    public String searchDokterbySpesialisasiAndPoli(Model model) {
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();
        List<PoliModel> listPoli = poliService.getPoliList();

        model.addAttribute("listPoli", listPoli);
        model.addAttribute("listSpesialisasi", listSpesialisasi);
        model.addAttribute("pagetitle", "Search Dokter by Spesialisasi and Poli");
        return "search-dokter-by-spesialisasi-and-poli";
    }

    @RequestMapping(path = "/cari", params ={"idSpesialisasi","idPoli"}, method = RequestMethod.GET)
    public String getDokterBySpesialisasiAndPoli(
            @RequestParam(value = "idSpesialisasi") Long idSpesialisasi,
            @RequestParam(value = "idPoli") Long idPoli,
            Model model
    ) {
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();
        List<PoliModel> listPoli = poliService.getPoliList();

        List<DokterModel> listAllPoli = new ArrayList<>();
        List<DokterModel> listAllSpesialisasi = new ArrayList<>();

        PoliModel poli = poliService.getPoliById(idPoli).get();
        SpesialisasiModel spesialisasi = spesialisasiService.getSpesialisasiById(idSpesialisasi).get();

        List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getJadwalJagaList();
        List<SpesialisasiDokterModel> listSpesialisasiDokter = spesialisasiDokterService.getDokterBySpesialisasi(spesialisasi);

        for (JadwalJagaModel jadwalJaga : listJadwalJaga){
            DokterModel dokterModel = jadwalJaga.getDokter();
            listAllPoli.add(dokterModel);
        }

        for (SpesialisasiDokterModel spesialisasiDokter : listSpesialisasiDokter){
            DokterModel dokterModel = spesialisasiDokter.getDokter();
            listAllSpesialisasi.add(dokterModel);
        }

        listAllPoli.retainAll(listAllSpesialisasi);

        model.addAttribute("poli", poli);
        model.addAttribute("spesial", spesialisasi);
        model.addAttribute("listDokter", listAllPoli);
        model.addAttribute("listPoli", listPoli);
        model.addAttribute("listSpesialisasi", listSpesialisasi);
        model.addAttribute("pagetitle", "View Dokter by Spesialisasi and Poli");

        return "search-dokter-by-spesialisasi-and-poli";
    }

    @RequestMapping(value = "/bonus", method = RequestMethod.GET)
    public String viewNumberOfDoctorsInEverySpesialisasi(@ModelAttribute SpesialisasiModel spesialisasi, Model model){
        List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();
        model.addAttribute("listSpesialisasi", listSpesialisasi);
        return "bonus";
    }

//    @RequestMapping(value = "/cari/tugas-terbanyak", method = RequestMethod.GET)
//    public String searchTopDokterInPoli(
//            @RequestParam(value="id") Long id,
//            Model model
//    ) {
//        PoliModel poli = poliService.getPoliById(id).get();
//        List<DokterModel> listDokter = dokterService.getTopDokterInPoli(poli);
//        List<PoliModel> listPoli = poliService.getPoliList();
//        model.addAttribute("listPoli",listPoli);
//        model.addAttribute("listDokter",listDokter);
//
//        //Return view template
//        return "search-top-dokter-in-poli";
//    }

    @RequestMapping(value = "/cari/tugas-terbanyak", method = RequestMethod.GET)
    public String cariDokterPalingBanyakBertugasDiPoliFormPage(
            Model model
    ) {
        List<PoliModel> listPoli = poliService.getPoliList();
        model.addAttribute("listPoli",listPoli);

        return "search-top-dokter-in-poli";
    }

    //bikin post
    @RequestMapping(value = "/cari/tugas-terbanyak", method = RequestMethod.POST)
    public String cariDokterPalingBanyakBertugasDiPoliFormSubmit(
            @RequestParam(value="id") Long id,
            Model model
    ) {
        PoliModel poli = poliService.getPoliById(id).get();
        List<DokterModel> listDokter = dokterService.getTopDokterInPoli(poli);
        List<PoliModel> listPoli = poliService.getPoliList();
        model.addAttribute("listPoli",listPoli);
        model.addAttribute("listDokter",listDokter);
        return "search-top-dokter-in-poli";
    }



}
