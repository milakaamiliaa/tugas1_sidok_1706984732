package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.JadwalJagaModel;
import apap.tugas.sidok.model.PoliModel;
import apap.tugas.sidok.service.DokterService;
import apap.tugas.sidok.service.JadwalJagaService;
import apap.tugas.sidok.service.PoliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PoliController {
    @Qualifier("poliServiceImpl")
    @Autowired
    private PoliService poliService;

    @Autowired
    private JadwalJagaService jadwalJagaService;

    @RequestMapping(value = "/poli", method = RequestMethod.GET)
    public String viewPoli(Model model){
        List<PoliModel> listPoli = poliService.getPoliList();
        model.addAttribute("listPoli", listPoli);
        return "view-poli";
    }

    @RequestMapping(value = "/poli/tambah", method = RequestMethod.GET)
    public String tambahPoliForm(Model model){
        PoliModel newPoli = new PoliModel();
        model.addAttribute("poli", newPoli);
        return "form-add-poli";
    }

    @RequestMapping(value = "/poli/tambah", method = RequestMethod.POST)
    public String tambahPoliSubmit(@ModelAttribute PoliModel poli, Model model){
        poliService.addPoli(poli);
        model.addAttribute("namaPoli", poli.getNama());
        return "add-poli";
    }

    @RequestMapping(value = "/poli/view/{id}", method = RequestMethod.GET)
    public String viewPoliDetail(@PathVariable Long id, Model model){
        PoliModel existingPoli = poliService.getPoliById(id).get();
        model.addAttribute("poli",existingPoli);
        return "view-poli-by-id";
    }

    @RequestMapping(value = "poli/update/{id}", method = RequestMethod.GET)
    public String updatePoliForm(@PathVariable Long id, Model model) {
        PoliModel existingPoli = poliService.getPoliById(id).get();
        model.addAttribute("poli", existingPoli);
        return "form-change-poli";
    }

    @RequestMapping(value = "poli/update/{id}", method = RequestMethod.POST)
    public String updatePoliFormSubmit(@PathVariable Long id, @ModelAttribute PoliModel poli, Model model) {
        PoliModel newPoliData = poliService.changePoli(poli);
        model.addAttribute("poli", newPoliData);
        return "change-poli";
    }

    @RequestMapping(value = "poli/delete/{id}")
    public String deletePoli(@PathVariable(value = "id") Long id, Model model) {
        PoliModel poli = poliService.getPoliById(id).get();
        poliService.deletePoli(poli);
        model.addAttribute("nama", poli.getNama());
        return "delete-poli";
    }

    @RequestMapping(path = "/poli/dokter/{id}", method = RequestMethod.GET)
    public String viewListDokterByIdPoli(@PathVariable Long id, Model model) {
        List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getJadwalJagaList();
        PoliModel poli = poliService.getPoliById(id).get();
        model.addAttribute("listJadwalJaga", listJadwalJaga);
        model.addAttribute("poli", poli);
        return "view-dokter-by-poli";
    }

}
