package Business;

import Acquaintance.IProfile;
import Acquaintance.Nationality;

public class Profile implements IProfile {

    private String firstName;
    private String lastName;
    private Nationality nationality;
    private String picture;
    private String profileText;

    public Profile(String firstName, String lastName, Nationality nationality, String profileText, String picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.profileText = profileText;
        this.picture = picture;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    @Override
    public void setProfileText(String profileText) {
        this.profileText = profileText;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public Nationality getNationality() {
        return nationality;
    }

    @Override
    public String getProfileText() {
        return profileText;
    }

    @Override
    public String getPicture() {
        return picture;
    }

    @Override
    public void setPicture(String picture) {
        this.picture = picture;
    }
}
