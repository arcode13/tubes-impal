package com.dormhub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Ganti dari int ke Long

    @Column(nullable = false)
    private String nama;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
