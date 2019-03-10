package pl.sii.springtraining.demo.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

  private static final Logger log = LoggerFactory.getLogger(ScheduledTaskService.class);

    @Scheduled(fixedRate = 5000)
    public void executeTask() {
      log.info("Scheduled task has been executed");
    }
}
