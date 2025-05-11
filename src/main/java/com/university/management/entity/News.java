package com.example.entity;

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

    @Column(name = "news_code", unique = true, nullable = false, length = 8)
    @NotBlank(message = "Mã tin tức là bắt buộc")
    @Size(min = 8, max = 8, message = "Mã tin tức phải có 8 ký tự")
    private String matin;

    @Column(name = "title", nullable = false, length = 200)
    @NotBlank(message = "Tiêu đề là bắt buộc")
    @Size(max = 200, message = "Tiêu đề tối đa 200 ký tự")
    private String tieude;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Nội dung là bắt buộc")
    private String noidung;

    @Column(name = "document_url", length = 255)
    private String tailieu_url;

    @Column(name = "image", length = 255)
    private String hinhanh;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime ngaytao;

    @Column(name = "created_by", length = 100)
    @Size(max = 100, message = "Tác giả tối đa 100 ký tự")
    private String nguoitao;

    // Constructors
    public News() {
        this.ngaytao = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatin() {
        return matin;
    }

    public void setMatin(String matin) {
        this.matin = matin;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTailieu_url() {
        return tailieu_url;
    }

    public void setTailieu_url(String tailieu_url) {
        this.tailieu_url = tailieu_url;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public LocalDateTime getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getNguoitao() {
        return nguoitao;
    }

    public void setNguoitao(String nguoitao) {
        this.nguoitao = nguoitao;
    }
}