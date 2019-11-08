package mvc.mvc.model.repos;

import mvc.mvc.model.entity.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClusterRepository extends JpaRepository<Cluster, Integer> {
    Cluster findByName(String cluster_name);
}
