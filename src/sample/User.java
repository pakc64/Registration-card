package sample;

public class User {
    private String name;
    private String surName;
    private String age;
    private String sex;
    private String country;
    private String login;
    private String password;


    public User(String name, String surName, String age, String sex, String country, String login, String password) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.sex = sex;
        this.country = country;
        this.login = login;
        this.password = password;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }



    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
