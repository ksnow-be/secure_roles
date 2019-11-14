package mvc.mvc.model.components;

import mvc.mvc.model.repos.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MyMailSender {

    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;
    private WorkerRepository workerRepository;
    private MyLogger logger;

    @Autowired
    public MyMailSender(JavaMailSender javaMailSender, TemplateEngine templateEngine, WorkerRepository workerRepository, MyLogger logger) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.workerRepository = workerRepository;
        this.logger = logger;
    }

    public String sendStats(String to){
        String processedHTMLTemplate = this.constructHTMLTemplate();

        try {
            MimeMessagePreparator preparator = message -> {
                MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
                helper.setTo(to);
                helper.setSubject("Список работников");
                helper.setText(processedHTMLTemplate, true);
            };
            javaMailSender.send(preparator);
//            logger.mailSend(to);
        } catch (MailException e){
            System.out.println(e.getLocalizedMessage());
        }
        return "redirect:/";
    }

    private String constructHTMLTemplate(){
        Context context = new Context();
        context.setVariable("workers", workerRepository.findAll());
        return templateEngine.process("mailsender", context);
    }
}
