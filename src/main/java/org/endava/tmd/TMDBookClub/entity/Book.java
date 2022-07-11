package org.endava.tmd.TMDBookClub.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , updatable = false)
    private Long id;


    @ManyToOne(optional = false)
    private User owner;

    @ManyToOne(optional = false)
    private BookInfo info;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Rent> rentedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<WaitList> waitedBy;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", owner=" + getOwner() +
                ", info=" + getInfo() +
                '}';
    }
}
