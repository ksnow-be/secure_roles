package mvc.mvc.controller;

import mvc.mvc.model.services.ClusterWorkerDTO;
import mvc.mvc.model.components.MyMailSender;
import mvc.mvc.model.services.UserRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(path = "/")
public class MainController {

    private MyMailSender mailSender;
    private ClusterWorkerDTO clusterWorkerDTO;
    private UserRoleDTO userRoleDTO;

    @Autowired
    public MainController(MyMailSender mailSender, ClusterWorkerDTO clusterWorkerDTO, UserRoleDTO userRoleDTO) {
        this.mailSender = mailSender;
        this.clusterWorkerDTO = clusterWorkerDTO;
        this.userRoleDTO = userRoleDTO;
    }

    @GetMapping(path = "/")
    public String showAllWorkers(Model model){
        model.addAttribute("workers", clusterWorkerDTO.showAllWorkers());
        model.addAttribute("clusters", clusterWorkerDTO.showAllClusters());
        model.addAttribute("bosses",clusterWorkerDTO.showClusterBosses());
        model.addAttribute("roles", userRoleDTO.findAllRoles());
        model.addAttribute("users", userRoleDTO.findAllUsers());
        return "index";
    }

    @PostMapping("/deleteWorker/{id}")
    public String deleteWorker(@PathVariable Long id){
        clusterWorkerDTO.deleteWorker(id);
        return "redirect:/";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam String addr){
        return mailSender.sendStats(addr);
    }
}
