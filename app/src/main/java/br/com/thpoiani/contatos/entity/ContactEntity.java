package br.com.thpoiani.contatos.entity;

import br.com.thpoiani.contatos.pojo.Contact;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ContactEntity extends RealmObject {

    @PrimaryKey
    private Long id;
    private String name;
    private String surname;
    private Long age;
    private String phoneNumber;
    private String image;

    public ContactEntity() {}

    public ContactEntity(Contact contact) {
        setId(contact.getId());
        setName(contact.getName());
        setSurname(contact.getSurname());
        setAge(contact.getAge());
        setPhoneNumber(contact.getPhoneNumber());
        setImage(contact.getImage());
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
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
