package mvc.mvc.model.components;

import mvc.mvc.model.repos.WorkerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = Logger.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    private WorkerRepository workerRepository;

    @Autowired
    public ScheduledTasks(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }


    @Scheduled(fixedRate = 60000 * 60) //каждый час отчет о пользователях в базах
    public void reportTime(){
        log.info("Время :" + dateFormat.format(new Date()) + " Количетсво пользователей в базе: " + workerRepository.findAll().size());
    }

}
