package mvc.mvc.model.services;

import mvc.mvc.model.components.MyLogger;
import mvc.mvc.model.entity.Cluster;
import mvc.mvc.model.entity.Worker;
import mvc.mvc.model.repos.ClusterRepository;
import mvc.mvc.model.repos.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClusterWorkerDTO {

    private WorkerRepository workerRepository;
    private ClusterRepository clusterRepository;
    private MyLogger logger;

    @Autowired
    public ClusterWorkerDTO(WorkerRepository workerRepository, ClusterRepository clusterRepository, MyLogger logger) {
        this.workerRepository = workerRepository;
        this.clusterRepository = clusterRepository;
        this.logger = logger;
    }

    public void addWorker(String name, String job, String cluster_name){
        if (workerRepository.findByName(name) != null){
            return;
        }
        Worker worker = new Worker();
        worker.setName(name);
        worker.setJob(job);
        worker.setIsBoss(false);
        worker.setBossedCluster(null);
        if (cluster_name.equals("...")){
            worker.setCluster(null);
        }
        else if (clusterRepository.findByName(cluster_name) == null){
            logger.errorCluster(cluster_name);
            return;
        }
        else{
            worker.setCluster(clusterRepository.findByName(cluster_name));
        }
        workerRepository.save(worker);
        logger.addWorker(worker.getName());
    }

    public void addCluster(String name, String bossName, String headCluster){
        if (clusterRepository.findByName(name) != null){
            return;
        }
        Cluster cluster = new Cluster();
        cluster.setName(name);

        Worker contender_to_boss = workerRepository.findByName(bossName);
        cluster.setBoss(contender_to_boss);

        if (headCluster.equals("...")){
            cluster.setHeadCluster(null);
        }
        else{
            cluster.setHeadCluster(clusterRepository.findByName(headCluster));
        }
        clusterRepository.save(cluster);
        logger.addCluster(cluster.getName());

        if (!contender_to_boss.getIsBoss()){
            contender_to_boss.setIsBoss(true);
            contender_to_boss.setBossedCluster(cluster);
            workerRepository.save(contender_to_boss);
        }
    }

    public String showClusterInfo(String cluster_name, Model model){
        Cluster cluster = clusterRepository.findByName(cluster_name);
        if (cluster == null){
            logger.errorCluster(cluster_name);
            return "notfound";
        }
        model.addAttribute("podr", cluster);
        model.addAttribute("workers", cluster.getWorkers());
        model.addAttribute("clusters", recurFindCluster(cluster));
        return "cluster";
    }

    private List<Cluster> recurFindCluster(Cluster cluster){
        List<Cluster> res = new ArrayList<>();
        for (Cluster c : cluster.getClusters()){
            res.add(c);
            res.addAll(recurFindCluster(c));
        }
        return res;
    }

    public List<Worker> showAllUnders(String worker_name){
        Cluster cluster = workerRepository.findByName(worker_name).getBossedCluster();
        List<Worker> res = new ArrayList<>();
        res.addAll(cluster.getWorkers());
        recurFindWorkers(res, cluster);
        return res;
    }

    private void recurFindWorkers(List<Worker> list, Cluster cluster){
        for (Cluster c : cluster.getClusters()){
            if (!list.contains(c.getBoss())){
                list.add(c.getBoss());
            }
            list.addAll(c.getWorkers());
            recurFindWorkers(list, c);
        }
    }

    public List<Worker> showAllBosses(String worker_name){
        Cluster cluster = workerRepository.findByName(worker_name).getCluster();
        if (cluster == null){
            return null;
        }
        List<Worker> res = new ArrayList<>();
        while (true){
            res.add(cluster.getBoss());
            if (cluster.getHeadCluster() == null){
                break;
            }
            cluster = cluster.getHeadCluster();
        }
        return res;
    }

    public List<Worker> showAllWorkers(){
        return workerRepository.findAll();
    }

    public List<Cluster> showAllClusters(){
        return clusterRepository.findAll();
    }

    public List<Worker> showClusterBosses(){
        return workerRepository.findAllByIsBoss(true);
    }

    public Worker findByName(String name){
        return workerRepository.findByName(name);
    }

    @Transactional
    public void deleteWorker(Long id){
        workerRepository.deleteById(id);
    }
}
