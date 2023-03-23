package com.dmainali.veggievault.entity;

import com.google.common.base.MoreObjects;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;

/**
 * This class represents a Category entity that extends the BaseEntity class and is mapped to
 * the "category" table in the database. It inherits the ID field and JPA annotations from the BaseEntity class.
 * @author Dirghayu Mainali
 * @version 1.0
 */
@Getter
@Setter
@Table(name="category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity{

    @Column(name = "name")
    @EqualsAndHashCode.Include
    String name;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",getId())
                .add("name",getName())
                .toString();
    }
}
