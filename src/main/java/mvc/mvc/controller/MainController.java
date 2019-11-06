package mvc.mvc.controller;

import mvc.mvc.model.services.WorkerDTO;
import mvc.mvc.model.components.MyMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(path = "/")
public class MainController {

    private MyMailSender mailSender;
    private WorkerDTO workerDTO;

    @Autowired
    public MainController(MyMailSender mailSender, WorkerDTO workerDTO) {
        this.mailSender = mailSender;
        this.workerDTO = workerDTO;
    }

    @GetMapping(path = "/")
    public String showAllWorkers(Model model){
        return workerDTO.listOfAll(model);
    }

    @PostMapping("/deleteWorker/{id}")
    public String deleteWorker(@PathVariable Long id){
        return workerDTO.deleteWorker(id);
    }

    @PostMapping(path = "/sendMail")
    public String sendStats(@RequestParam String addr){
        return mailSender.sendStats(addr);
    }
}
