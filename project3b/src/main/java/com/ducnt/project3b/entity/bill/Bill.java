package com.ducnt.project3b.entity.bill;

import com.ducnt.project3b.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date buyDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
