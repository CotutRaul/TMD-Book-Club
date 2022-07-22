package org.endava.tmd.TMDBookClub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.endava.tmd.TMDBookClub.entity.BookInfo;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyBook {
    private Long id;
    private BookInfo info;
    private LocalDate returnDate;
    private String userName;
}
