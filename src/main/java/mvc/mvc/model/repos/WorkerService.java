package mvc.mvc.model.repos;

import org.springframework.ui.Model;

public interface WorkerService {
    String addWorker(String name,String job, String cluster, String bossName);
    String searchAllBosses(String workerName, Model model);
    String searchAllUnder(String bossName, Model model);
    String searchName(String name, Model model);
    String deleteWorker(Long id);
    String listOfAll(Model model);
}
