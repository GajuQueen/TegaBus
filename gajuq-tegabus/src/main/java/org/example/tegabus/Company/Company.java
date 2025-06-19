package org.example.tegabus.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.Common;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company extends Common {

    @Column(unique = true, nullable = false)
    public String name;
    public String address;
    @Column(unique = true, nullable = false)
    public String email;
    @Column(unique = true, nullable = false)
    public String phoneNumber;
    public CompanyStatus status;

}
