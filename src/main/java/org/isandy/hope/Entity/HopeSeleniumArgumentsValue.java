package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Table(name = "hope_selenium_config_arguments_value")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class HopeSeleniumArgumentsValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "argument_id")
    private Long argumentID;

    @Column(name = "argument_value_id")
    private Long argumentValueID;

    @Column(name = "argument_value", columnDefinition = "TEXT")
    private String value;

    @Column(name = "argument_description")
    private String description;
}
