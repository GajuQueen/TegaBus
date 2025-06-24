package org.example.tegabus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Common {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    @UpdateTimestamp
    public Timestamp updatedAt;
    @CreationTimestamp
    public Timestamp createdAt;
}
