package com.bulvee.decorator.model;

public class Contact {

    private Long code;
    private String name;
    private String phone;

    public Contact(Long code, String name, String phone){
        this.code = code;
        this.name = name;
        this.phone = phone;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.code + " - " + this.name+ " - " + this.phone;
    }
}
