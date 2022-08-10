package com.ducnt.project3b.dto.bill;

import com.ducnt.project3b.entity.user.User;
import lombok.Data;

import java.util.Date;

@Data
public class BillDTO {

    private int id;

    private String buyDate;

    User user;
}
