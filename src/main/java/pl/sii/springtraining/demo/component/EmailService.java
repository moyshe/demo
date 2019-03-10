package pl.sii.springtraining.demo.component;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender sender;
  public EmailService(JavaMailSender sender) {
    this.sender = sender;
  }

  public void employeeCreated(Long id, String name, String department) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("hlewandowski@sii.pl");
    message.setSubject("Emloyee created");
    message.setText("New employee hase been created! ID: " + id + ", name: '" + name + "', department: " + department);
    sender.send(message);
  }
}
