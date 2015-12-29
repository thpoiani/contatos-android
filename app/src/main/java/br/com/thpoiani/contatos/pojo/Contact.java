package br.com.thpoiani.contatos.pojo;

import br.com.thpoiani.contatos.entity.ContactEntity;

public class Contact {

    private static final String PLACEHOLDIT = "https://placeholdit.imgix.net/~text?txtsize=15&txt={0}&w=50&h=50";

    private Long id;
    private String name;
    private String surname;
    private Long age;
    private String phoneNumber;

    public Contact() {}

    public Contact(ContactEntity entity) {
        setId(entity.getId());
        setName(entity.getName());
        setSurname(entity.getSurname());
        setAge(entity.getAge());
        setPhoneNumber(entity.getPhoneNumber());
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImage() {
        String firstChar = String.valueOf(getName().charAt(0));
        return PLACEHOLDIT.replace("{0}", firstChar);
    }
}
