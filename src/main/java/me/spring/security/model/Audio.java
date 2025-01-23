package me.spring.security.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;      // Название трека
    private String artist;     // Исполнитель
    private String url;        // URL файла в облаке
    private String format;     // Формат файла (например, mp3, wav) // todo enum
    private Long size;         // Размер файла в байтах

    @ManyToOne
    private User owner;        // Владелец трека

    private Date uploadedAt; // Дата и время загрузки
}
