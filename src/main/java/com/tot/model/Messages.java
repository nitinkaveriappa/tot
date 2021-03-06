package com.tot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Messages {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "inputmessages", columnDefinition = "LONGBLOB")
    private String[] inputmessages;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receivers", columnDefinition = "LONGBLOB")
    private String[] receivers;

    @Column(name = "verifiedreceivers", columnDefinition = "LONGBLOB")
    private String[] verifiedreceivers;
}
