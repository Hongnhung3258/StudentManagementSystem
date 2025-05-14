package com.university.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tendangnhap", nullable = false, length = 40)
    private String username;
    
    @Column(name = "hoatdong", nullable = false, length = 50)
    private String action;
    
    @Column(name = "muctieu", nullable = false, length = 50)
    private String target;
    
    @Column(name = "id_muctieu", nullable = false)
    private Integer targetId;
    
    @Column(name = "ngaygio", nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private Login login;
    
    public ActivityLog() {}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
    public Integer getTargetId() {
        return targetId;
    }

    public void setTarget(Integer targetId) {
        this.targetId = targetId;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}