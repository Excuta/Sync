package com.waslabrowser.service.watcher;

import com.waslabrowser.service.common.audit.AuditEntity;
import com.waslabrowser.service.watcher.twitter.Datum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "followers", schema = "public")
public class Follower extends AuditEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "username", nullable = false)
    private String username;

    public Follower() {
    }

    public Follower(Datum datum) {
        this.id = datum.getId();
        this.username = datum.getUsername();
    }

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follower follower = (Follower) o;
        return Objects.equals(id, follower.id) && Objects.equals(username, follower.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Follower(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
