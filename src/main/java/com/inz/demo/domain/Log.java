package com.inz.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "table_row_id")
    private int tableRow;

    @Column(name = "log_date", length = 50)
    private Date timestamp;

    @Column(name = "log_operation", length = 10)
    private String operation;
}
