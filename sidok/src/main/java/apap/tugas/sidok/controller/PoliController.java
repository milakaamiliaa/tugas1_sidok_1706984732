package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.DokterModel;
import apap.tugas.sidok.model.PoliModel;
import apap.tugas.sidok.service.DokterService;
import apap.tugas.sidok.service.PoliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PoliController {
    @Qualifier("poliServiceImpl")
    @Autowired
    private PoliService poliService;

    @RequestMapping(value = "/poli", method = RequestMethod.GET)
    public String poli(Model model){
        List<PoliModel> listPoli = poliService.getPoliList();
        model.addAttribute("listDokter", listPoli);
        return "view-poli";

    }
}
