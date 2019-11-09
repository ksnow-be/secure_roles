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

    @ManyToOne
    private Worker boss;

    @OneToMany(mappedBy = "cluster", fetch = FetchType.EAGER)
    private List<Worker> workers;

    private Integer headId;

    @OneToMany(mappedBy = "headId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
        return boss;
    }

    public void setBoss(Worker boss) {
        this.boss = boss;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public List<Cluster> getClusters() {
        return clustersList;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clustersList = clusters;
    }
}
