package org.endava.tmd.TMDBookClub.email;

import org.endava.tmd.TMDBookClub.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, Book book){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("my.gmail@gmail.com");
        message.setTo(toEmail);
        message.setSubject(book.getInfo().getTitle() + " is now available");
        String text = book.getInfo().getTitle() + " by " + book.getInfo().getAuthor() + " is now available and can be rent on: http://localhost:3000/";
        message.setText(text);

        mailSender.send(message);
    }
}
