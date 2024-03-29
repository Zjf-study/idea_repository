package MyIOproject;

//定义一个Patient类，实现标准javabean
public class Patient {
    private String number;
    private String name;
    private String age;
    private String address;

    public Patient() {
    }

    public Patient(String number, String name, String age, String address) {
        this.number = number;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Patient{number = " + number + ", name = " + name + ", age = " + age + ", address = " + address + "}";
    }
}
