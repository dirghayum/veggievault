package com.dmainali.veggievault.entity;

import com.google.common.base.MoreObjects;
import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents a Vegetable entity that extends the BaseEntity class and is mapped to
 * the "item" table in the database. It inherits the ID field and JPA annotations from the BaseEntity class.
 * @author Dirghayu Mainali
 * @version 1.0
 */
@Entity
@Getter
@Setter
@Table(name="item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vegetable extends BaseEntity{

    @Column(name = "name")
    @EqualsAndHashCode.Include
    String name;

    @ManyToOne(cascade= CascadeType.ALL) //Many products can have same category
    @JoinColumn(name= "category_id", nullable = false)
    @EqualsAndHashCode.Include
    Category category;

    public Vegetable(String name) {
        this.name = name;
    }

    public Vegetable(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",getId())
                .add("name",getName())
                .add("category",getCategory())
                .toString();
    }
}
