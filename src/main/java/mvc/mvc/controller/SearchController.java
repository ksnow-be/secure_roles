package mvc.mvc.controller;

import mvc.mvc.model.services.WorkerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchController {

    private WorkerDTO workerDTO;

    @Autowired
    public SearchController(WorkerDTO workerDTO) {
        this.workerDTO = workerDTO;
    }

    @PostMapping(path = "/showBosses")
    public String showBosses(@RequestParam String workerName, Model model){
        return workerDTO.searchAllBosses(workerName, model);
    }

    @PostMapping(path = "/showUnder")
    public String showUnder(@RequestParam String bossName, Model model){
        return workerDTO.searchAllUnder(bossName, model);
    }

    @PostMapping(path = "/showName")
    public String findByName(@RequestParam String name, Model model){
        return workerDTO.searchName(name, model);
    }
}
