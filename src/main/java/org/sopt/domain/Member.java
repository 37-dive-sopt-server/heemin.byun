package org.sopt.domain;

public class Member {

    private Long id;
    private String name;
    private String birthdate;
    private String email;
    private Gender gender;
    private boolean isDeleted;

    public Member(Long id, String name, String birthdate, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.gender = gender;
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
