package com.TP.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "comment", nullable = false, length = 1000)
    @NotNull(message = "Please review this product")
    private String comment;

    @Column(name = "rating", nullable = true)
    @NotNull(message = "Please choose rating")
    private double rating;

    @ManyToOne
    @JoinColumn(name = "masanpham", nullable = false)
    private SanPham sanPham;

    @Column(name = "created", nullable = true)
    private Date created;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    //getters and setters
//toString method

}