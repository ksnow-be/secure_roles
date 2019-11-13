package mvc.mvc.model.repos;

import mvc.mvc.model.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    Worker findByName(String name);
    List<Worker> findAllByIsBoss(boolean param);
    void deleteById(Long id);
}
