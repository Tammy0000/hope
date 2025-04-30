package org.isandy.hope.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "hope_selenium_config_arguments")
@Getter
@Setter
@Accessors(chain = true)
public class HopeSeleniumArguments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "argument_id")
    private String argumentID;

    @Column(name = "argument_name", columnDefinition = "TEXT")
    private String value;

    @Column(name = "argument_description")
    private String description;
}
