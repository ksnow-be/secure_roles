package mvc.mvc.model.components;

import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import java.util.Collection;

@Component
public class MyLogger {
    private final Logger logger = Logger.getLogger(this.getClass());

    public void searchUser(String name){
        logger.info("Поиск сотруника по имени " + name);
    }

    public void showBosses(String name){
        logger.info("Поиск всех боссов сотрудника " + name);
    }

    public void showUnder(String name){
        logger.info("Поиск всех подчиненных сотрудника " + name);
    }

    public void mailSend(String name){
        logger.info("Отправка статистики на почту " + name);
    }

    public void addUser(String name){
        logger.info("Добавлен пользователь " + name);
    }

    public void deleteUser(String name){
        logger.info("Удален пользователь " + name);
    }

    public void errorAdd(String name){
        logger.error("Ошибка добавления пользователя " + name);
    }
}
