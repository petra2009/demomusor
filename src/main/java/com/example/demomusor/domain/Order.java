package com.example.demomusor.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
public class Order extends BaseEntity {

    private Date orderDate;

    private int orderNum;

    private double amount;

    private String customerName;                        //customer - покупатель

    private String customerAddress;

    private String customerEmail;

    private String customerPhone;

}
