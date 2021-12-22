package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Access;
import javax.persistence.Embeddable;

import static javax.persistence.AccessType.FIELD;

@Embeddable
@Access(FIELD)
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
