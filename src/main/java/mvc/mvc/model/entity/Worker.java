package mvc.mvc.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "workers")
public class Worker {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String job;

    @ManyToOne
    @JoinColumn(name = "cluster_id")
    private Cluster cluster;

    private boolean isBoss;

    @ManyToOne
    @JoinColumn(name = "bossed_cluster")
    private Cluster bossedCluster;

    public boolean getIsBoss() {
        return isBoss;
    }

    public void setIsBoss(boolean param) {
        isBoss = param;
    }

    public Cluster getBossedCluster() {
        return bossedCluster;
    }

    public void setBossedCluster(Cluster bossedCluster) {
        this.bossedCluster = bossedCluster;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}