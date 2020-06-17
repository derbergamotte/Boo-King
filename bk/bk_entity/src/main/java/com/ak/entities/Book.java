package com.ak.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "book")
@Data
@EqualsAndHashCode(callSuper = true)
public class Book extends AEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookdetail_id", referencedColumnName = "id")
    private Bookdetail bookdetail;

    @ManyToMany
    @JoinTable(name = "book_department", 
               joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), 
               inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"))
    private List<Department> departments;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

}
