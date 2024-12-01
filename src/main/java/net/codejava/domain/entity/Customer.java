package net.codejava.domain.entity;

import java.util.Date;

import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.codejava.domain.enums.ECustomerType;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String citizenId;
    private String drivingLicense;

    @Enumerated(EnumType.STRING)
    private ECustomerType customerType;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @JsonIgnore
    @ManyToOne(targetEntity = Booking.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Customer(Customer customer) {
        this.username = customer.username;
        this.email = customer.email;
        this.phoneNumber = customer.phoneNumber;
        this.address = customer.address;
        this.citizenId = customer.citizenId;
        this.drivingLicense = customer.drivingLicense;
        this.customerType = customer.customerType;
        this.birthday = customer.birthday;
        this.booking = customer.booking;
    }
}
