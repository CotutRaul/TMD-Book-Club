package org.endava.tmd.TMDBookClub.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WaitList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , updatable = false)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;

    private LocalDate date;

    @Override
    public String toString() {
        return "WaitList{" +
                "id=" + getId() +
                ", user=" + getUser() +
                ", book=" + getBook() +
                ", date=" + getDate() +
                '}';
    }
}
