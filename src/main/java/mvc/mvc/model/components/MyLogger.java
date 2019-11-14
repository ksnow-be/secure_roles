package mvc.mvc.model.components;

import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import java.util.Collection;

@Component
public class MyLogger {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void addUser(String name){
        logger.info("Добавлен новый пользователь " + name);
    }

    public void addCluster(String name){
        logger.info("Добавлен новый отдел " + name);
    }

    public void addWorker(String name){
        logger.info("Добавлен новый работник " + name);
    }

    public void addRole(String name){
        logger.info("Добавлена новая роль " + name);
    }

    public void updateRole(String name, String role){
        logger.info("Смена прав у юзера " + name + " на " + role);
    }

    public void errorCluster(String name){
        logger.error("Отдела с именем " + name + " не существует");
    }
}
