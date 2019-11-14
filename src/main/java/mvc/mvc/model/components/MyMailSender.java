package mvc.mvc.model.components;

import mvc.mvc.model.repos.WorkerRepository;
import mvc.mvc.model.services.ClusterWorkerDTO;
import mvc.mvc.model.services.UserRoleDTO;
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
    private UserRoleDTO userRoleDTO;
    private ClusterWorkerDTO clusterWorkerDTO;
    private MyLogger logger;

    @Autowired
    public MyMailSender(JavaMailSender javaMailSender, TemplateEngine templateEngine, UserRoleDTO userRoleDTO, ClusterWorkerDTO clusterWorkerDTO, MyLogger logger) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.userRoleDTO = userRoleDTO;
        this.clusterWorkerDTO = clusterWorkerDTO;
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
        } catch (MailException e){
            System.out.println(e.getLocalizedMessage());
        }
        return "redirect:/";
    }

    private String constructHTMLTemplate(){
        Context context = new Context();
        context.setVariable("workers", clusterWorkerDTO.showAllWorkers());
        context.setVariable("clusters", clusterWorkerDTO.showAllClusters());
        return templateEngine.process("mail", context);
    }
}
