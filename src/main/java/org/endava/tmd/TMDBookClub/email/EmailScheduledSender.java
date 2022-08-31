package org.endava.tmd.TMDBookClub.email;

import org.endava.tmd.TMDBookClub.entity.WaitList;
import org.endava.tmd.TMDBookClub.repository.RentRepository;
import org.endava.tmd.TMDBookClub.repository.WaitListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class EmailScheduledSender {
    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private WaitListRepository waitListRepository;

    @Autowired
    private RentRepository rentRepository;

//    @Scheduled(cron = "0 0 10 */1 * *")
    public void DailyWaitingListEmail() {
        List<WaitList> waitList = waitListRepository.findAllByDateLessThanCurrentDate();
        waitList.forEach(wait -> {
            LocalDate lastDate = rentRepository.findLastEndDateBookWasRented(wait.getBook().getId());
            if (lastDate == null || lastDate.compareTo(LocalDate.now()) < 0) {
                senderService.sendEmail(wait.getUser().getEmail(), wait.getBook());
                waitListRepository.deleteById(wait.getId());
            } else {
                wait.setDate(lastDate);
                waitListRepository.save(wait);
            }
        });
    }
}
