package com.dmainali.veggievault.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/** This class represents a base entity class that provides a common ID field and JPA annotations
 * for child entities. This class is abstract and cannot be instantiated directly.
 * @author Dirghayu Mainali
 * @version 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * 'id' is the unique identifier for this entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    @Column(name="id", unique=true)
    @JsonProperty
    private Long id;

}
