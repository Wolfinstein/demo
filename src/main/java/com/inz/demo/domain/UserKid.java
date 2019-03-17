package com.inz.demo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_kids")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserKid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "kid_id")
    private User kid;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


}
