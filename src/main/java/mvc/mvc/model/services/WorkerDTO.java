package mvc.mvc.model.services;

import mvc.mvc.model.components.MyLogger;
import mvc.mvc.model.entity.Worker;
import mvc.mvc.model.repos.WorkerRepository;
import mvc.mvc.model.repos.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class WorkerDTO implements WorkerService {

    private String name;
    private String job;
    private String cluster;
    private String bossName;

    private WorkerRepository workerRepository;
    private MyLogger logger;

    @Autowired
    public WorkerDTO(WorkerRepository workerRepository, MyLogger logger) {
        this.workerRepository = workerRepository;
        this.logger = logger;
    }

    @Override
    public String listOfAll(Model model) {
        model.addAttribute("workers", workerRepository.findAll());
        return "index";
    }

    @Transactional
    @Override
    public String deleteWorker(Long id) {
        String name = workerRepository.findById(id).getName();
        workerRepository.deleteByName(name);
        logger.deleteUser(name);
        return "redirect:/";
    }

    @Override
    public String searchName(String name, Model model) {
        model.addAttribute("name", name);
        if (workerRepository.findByName(name) != null) {
            model.addAttribute("nameSearch", workerRepository.findByName(name));
            logger.searchUser(name);
            return "name";
        }
        else{
            return "notfound";
        }
    }

    @Override
    public String searchAllUnder(String bossName, Model model) {
        if (workerRepository.findByName(bossName) == null){
            return "notfound";
        }

        model.addAttribute("name", bossName);
        List<Worker> unders = new ArrayList<>();
        for (Long i = workerRepository.findByName(bossName).getId(); i <= workerRepository.findAll().size(); i++) {
            unders.addAll(workerRepository.findByBossId(i));
        }
        model.addAttribute("under", unders);
        logger.showUnder(bossName);
        return "under";
    }

    @Override
    public String searchAllBosses(String workerName, Model model){
        if (workerRepository.findByName(workerName) == null){
            return "notfound";
        }

        String name_tmp = workerName;
        List<Worker> bosses = new ArrayList<>();
        while (true){

            String bossName = workerRepository.findByName(name_tmp).getBossName();

            if (bossName == null){
                break;
            }
            bosses.add(workerRepository.findByName(bossName));
            name_tmp = bossName;
        }
        model.addAttribute("name", workerName);
        model.addAttribute("bosses", bosses);
        logger.showBosses(workerName);
        return "bosses";
    }

    @Override
    public String addWorker(String name,String job, String cluster, String bossName) {
        WorkerDTO dto = new WorkerDTO(name, job, cluster, bossName);
        Worker worker = new Worker();
        worker.setName(dto.getName());
        worker.setCluster(dto.getCluster());
        worker.setJob(dto.getJob());

        if (bossName.equals("...")){
            if (!workerRepository.findAll().isEmpty()){
                return "stopadd";
            }
            worker.setBossId(null);
            worker.setBossName(null);
        }
        else{
            worker.setBossId(workerRepository.findByName(bossName).getId());
            worker.setBossName(dto.getBossName());
        }
        workerRepository.save(worker);
        logger.addUser(name);
        return "redirect:/";
    }

    public WorkerDTO(String name, String job, String cluster, String bossName) {
        this.name = name;
        this.job = job;
        this.cluster = cluster;
        this.bossName = bossName;
    }

    public WorkerDTO(String name){
        this.name = name;
    }

    public WorkerDTO(){}

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getCluster() {
        return cluster;
    }

    public String getBossName() {
        return bossName;
    }
}
