package com.university.management.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Entity mapping to the news table in the database.
 */
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "news_code", nullable = false, unique = true, length = 8)
    private String newsCode;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "document_url", length = 255)
    private String documentUrl;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "created_by", length = 40)
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username", insertable = false, updatable = false)
    private Login createdByLogin;

    // Constructors
    public News() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDocumentUrl () {
        return documentUrl ;
    }

    public void setDocumentUrl (String documentUrl ) {
        this.documentUrl  = documentUrl ;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
     public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}