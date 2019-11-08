package mvc.mvc.controller;

import mvc.mvc.model.services.ClusterWorkerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchController {

    private ClusterWorkerDTO clusterWorkerDTO;

    @Autowired
    public SearchController(ClusterWorkerDTO clusterWorkerDTO) {
        this.clusterWorkerDTO = clusterWorkerDTO;
    }

    @PostMapping(path = "/showBosses")
    public String showBosses(@RequestParam String workerName, Model model){
        return "redirect:/";
    }

    @PostMapping(path = "/showUnder")
    public String showUnder(@RequestParam String bossName, Model model){
        return "redirect:/";
    }

    @PostMapping(path = "/showName")
    public String findByName(@RequestParam String name, Model model){
        return "redirect:/";
    }
}
