package entity;

import java.util.Date;

public class Person {
    private int id;
    private String name;
    private int age;
    private Date bornDate;
    private String eamil;
    private String address;

    public Person() {
    }

    public Person( String name, int age, Date bornDate, String eamil, String address) {
        this.name = name;
        this.age = age;
        this.bornDate = bornDate;
        this.eamil = eamil;
        this.address = address;
    }

    public Person(int id, String name, int age, Date bornDate, String eamil, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bornDate = bornDate;
        this.eamil = eamil;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public String getEamil() {
        return eamil;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", bornDate=" + bornDate +
                ", eamil='" + eamil + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
