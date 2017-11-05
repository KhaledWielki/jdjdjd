package com.freelancerworld.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

/**
 * Created by AdamR on 2017-10-31.
 */

@Entity
@Table(name = "request_taker")
public class RequestTaker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_taker_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}