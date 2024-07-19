package model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(columnDefinition = "smallint default 0")
    private int alarm;

    @Column(name = "date_time_alarm")
    private Timestamp dateTimeAlarm;

}
