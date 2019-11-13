package mvc.mvc.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clusters")
public class Cluster {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "bossedCluster", fetch = FetchType.EAGER)
    private List<Worker> boss = new ArrayList<>();

    @OneToMany(mappedBy = "cluster", fetch = FetchType.EAGER)
    private List<Worker> workers;

    @ManyToOne
    private Cluster headCluster;

    @OneToMany(mappedBy = "headCluster", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cluster> clustersList = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Worker getBoss() {
        return boss.get(0);
    }

    public void setBoss(Worker boss) {
        this.boss.add(boss);
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public Cluster getHeadCluster() {
        return headCluster;
    }

    public void setHeadCluster(Cluster headCluster) {
        this.headCluster = headCluster;
    }

    public List<Cluster> getClusters() {
        return clustersList;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clustersList = clusters;
    }
}
