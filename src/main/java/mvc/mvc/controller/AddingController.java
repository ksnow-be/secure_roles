package mvc.mvc.controller;

import mvc.mvc.model.services.WorkerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddingController {

    private WorkerDTO workerDTO;

    @Autowired
    public AddingController(WorkerDTO workerDTO) {
        this.workerDTO = workerDTO;
    }

    @PostMapping("/add")
    public String addNewWorker(@RequestParam String name, @RequestParam String job, @RequestParam String cluster, @RequestParam String bossName) {
       return workerDTO.addWorker(name, job, cluster, bossName);
    }
}
