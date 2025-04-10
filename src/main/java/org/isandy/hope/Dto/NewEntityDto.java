package org.isandy.hope.Dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.isandy.hope.Entity.NewEntity}
 */
public record NewEntityDto(String name, String description) implements Serializable {
}