package com.example.login_formulario;

public class Resume {
    public Resume(String ID, String firstName, String lastName, String streetAddress, String streetAddress2, String city,
                  String state_province, String postal_zipCode, String country, String emailAddress, String areaCode,
                  String phoneNumber, String positionApplaying, String startDate) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state_province = state_province;
        this.postal_zipCode = postal_zipCode;
        this.country = country;
        this.emailAddress = emailAddress;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
        this.positionApplaying = positionApplaying;
        this.startDate = startDate;

    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public String getState_province() {
        return state_province;
    }

    public String getPostal_zipCode() {
        return postal_zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPositionApplaying() {
        return positionApplaying;
    }

    public String getStartDate() {
        return startDate;
    }

    private String ID, firstName, lastName, streetAddress, streetAddress2, city, state_province,
            postal_zipCode, country, emailAddress, areaCode, phoneNumber, positionApplaying,
            startDate;
}

