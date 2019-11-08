package mvc.mvc.controller;

import mvc.mvc.model.services.ClusterWorkerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddingController {

    private ClusterWorkerDTO clusterWorkerDTO;

    @Autowired
    public AddingController(ClusterWorkerDTO clusterWorkerDTO) {
        this.clusterWorkerDTO = clusterWorkerDTO;
    }

    @PostMapping("/addWorker")
    public String addNewWorker(@RequestParam String name, @RequestParam String job, @RequestParam String cluster_name) {
        clusterWorkerDTO.addWorker(name, job, cluster_name);
        return "redirect:/";
    }

    @PostMapping("/addCluster")
    public String addNewCluster(@RequestParam String name, @RequestParam String bossName, @RequestParam String headCluster) {
        clusterWorkerDTO.addCluster(name, bossName, headCluster);
        return "redirect:/";
    }
}
