package com.example.restcrud.commentary;

import com.example.restcrud.thread.Thread;
import com.example.restcrud.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commentaries")
public class Commentary {
    @Id
    @SequenceGenerator(
            name = "commentary_sequence",
            sequenceName = "commentary_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "commentary_sequence"
    )
    @Column(name = "commentary_id", nullable = false)
    private Long commentaryId;
    @Column(name = "commentary_content", nullable = false)
    private String commentaryContent;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "thread_id", nullable = false)
    private Thread thread;

    public Commentary() {
    }

    public Commentary(String commentaryContent, User user, Thread thread) {
        this.commentaryContent = commentaryContent;
        this.user = user;
        this.thread = thread;
    }

    public Commentary(String commentaryContent, LocalDateTime createdAt, User user, Thread thread) {
        this.commentaryContent = commentaryContent;
        this.createdAt = createdAt;
        this.user = user;
        this.thread = thread;
    }

    public Long getCommentId() {
        return commentaryId;
    }

    public void setCommentId(Long commentaryId) {
        this.commentaryId = commentaryId;
    }

    public String getCommentaryContent() {
        return commentaryContent;
    }

    public void setCommentaryContent(String commentaryContent) {
        this.commentaryContent = commentaryContent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentaryId=" + commentaryId +
                ", comment='" + commentaryContent + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", thread=" + thread +
                '}';
    }
}
