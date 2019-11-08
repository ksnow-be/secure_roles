package mvc.mvc.controller;

import mvc.mvc.model.services.ClusterWorkerDTO;
import mvc.mvc.model.components.MyMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(path = "/")
public class MainController {

    private MyMailSender mailSender;
    private ClusterWorkerDTO clusterWorkerDTO;

    @Autowired
    public MainController(MyMailSender mailSender, ClusterWorkerDTO clusterWorkerDTO) {
        this.mailSender = mailSender;
        this.clusterWorkerDTO = clusterWorkerDTO;
    }

    @GetMapping(path = "/")
    public String showAllWorkers(Model model){
        return "index";
    }

    @PostMapping("/deleteWorker/{id}")
    public String deleteWorker(@PathVariable Long id){
        return "index";
    }

    @PostMapping(path = "/sendMail")
    public String sendStats(@RequestParam String addr){
        return "index";
    }
}
