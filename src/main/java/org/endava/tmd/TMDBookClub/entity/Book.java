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


    @ManyToOne(cascade = CascadeType.ALL)
    User owner;

    @ManyToOne(cascade = CascadeType.ALL)
    BookInfo info;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Rent> rentedBy;


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", owner=" + owner +
                ", info=" + info +
                '}';
    }
}
