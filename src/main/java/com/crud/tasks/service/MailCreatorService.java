package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    CompanyConfig companyConfig;

    @Autowired
    AdminConfig adminConfig;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        List<String> efficiencyTips = new ArrayList<>();
        efficiencyTips.add("Close Netflix");
        efficiencyTips.add("Try to concentrate on one thing for 30 minutes");
        efficiencyTips.add("Close Netflix (this time seriously)");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "https://jakubg78.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        context.setVariable("efficiency_tips", efficiencyTips);
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        long size = taskRepository.count();
        if (size >= 9) {
            context.setVariable("queue_overloaded", true);
            context.setVariable("show_button", true);
        } else {
            context.setVariable("queue_overloaded", false);
            context.setVariable("show_button", false);
        }
        if(message.contains("Currently in")) {
            return templateEngine.process("mail/task-count-scheduler-mail", context);
        } else {
            return templateEngine.process("mail/created-trello-card-mail", context);
        }
    }
}
