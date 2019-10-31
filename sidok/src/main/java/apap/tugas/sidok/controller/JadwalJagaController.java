package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.JadwalJagaModel;
import apap.tugas.sidok.model.PoliModel;
import apap.tugas.sidok.service.DokterService;
import apap.tugas.sidok.service.JadwalJagaService;
import apap.tugas.sidok.service.PoliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JadwalJagaController {
    @Autowired
    private JadwalJagaService jadwalJagaService;

    @Autowired
    DokterService dokterService;

    @Autowired
    PoliService poliService;


    @GetMapping(value = "jadwal/tambah/{nip}")
    public String addJadwalJagaFormPage(@PathVariable String nip, Model model) {
        DokterModel targetDokter = dokterService.getDokterByNip(nip);
        List<JadwalJagaModel> listJadwalJaga = targetDokter.getListJadwalJaga();
        JadwalJagaModel jadwalJaga = new JadwalJagaModel();
        model.addAttribute("listJadwalJaga", listJadwalJaga);
        model.addAttribute("jadwalJaga", jadwalJaga);
        model.addAttribute("listPoli", poliService.getPoliList());
        model.addAttribute("dokter", targetDokter);
        return "form-add-jadwal";
    }

    @RequestMapping(value = "jadwal/tambah/{nip}", method = RequestMethod.POST)
    public String addJadwalJagaSubmit(@ModelAttribute JadwalJagaModel jadwalJaga, Model model) {
        jadwalJagaService.addJadwalJaga(jadwalJaga);
        model.addAttribute("jadwalJaga", jadwalJaga);
        return "submit-jadwal";
    }
}
