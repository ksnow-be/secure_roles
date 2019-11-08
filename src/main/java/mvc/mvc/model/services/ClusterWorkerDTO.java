package mvc.mvc.model.services;

import mvc.mvc.model.components.MyLogger;
import mvc.mvc.model.entity.Cluster;
import mvc.mvc.model.entity.Worker;
import mvc.mvc.model.repos.ClusterRepository;
import mvc.mvc.model.repos.WorkerRepository;
import mvc.mvc.model.repos.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClusterWorkerDTO implements WorkerService {
    private WorkerRepository workerRepository;
    private ClusterRepository clusterRepository;

    @Autowired
    public ClusterWorkerDTO(WorkerRepository workerRepository, ClusterRepository clusterRepository) {
        this.workerRepository = workerRepository;
        this.clusterRepository = clusterRepository;
    }

    public void addWorker(String name, String job, String cluster_name){
        if (workerRepository.findByName(name) != null){
            System.out.println("Worker already exists!");
            return;
        }

        Worker worker = new Worker();
        worker.setName(name);
        worker.setJob(job);

        if (cluster_name.equals("")){
            worker.setCluster(null);
        }
        else{
            worker.setCluster(clusterRepository.findByName(cluster_name));
        }

        workerRepository.save(worker);
    }

    public void addCluster(String name, String bossName, String headCluster){
        if (clusterRepository.findByName(name) != null){
            System.out.println("Cluster already exists!");
            return;
        }

        Cluster cluster = new Cluster();
        cluster.setName(name);
        cluster.setBoss(workerRepository.findByName(bossName));

        if (headCluster.equals("")){
            cluster.setHeadId(null);
        }
        else{
            Integer id = clusterRepository.findByName(headCluster).getId();
            cluster.setHeadId(id);
        }

        clusterRepository.save(cluster);
    }
}
