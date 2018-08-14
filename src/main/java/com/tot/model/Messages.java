package com.tot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Messages {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "inputmessages")
    private List<String> inputmessages;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receivers")
    private List<String> receivers;

}
