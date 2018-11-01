/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import javafx.scene.image.Image; //Might not be a correct import...



/**
 *
 */
public class Profile {
    
    String name;
    Nationality nationality;
    Image picture; //Watch out for the datatype!
    String profileText;

    public Profile(String name, Nationality nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public void setProfileText(String profileText) {
        this.profileText = profileText;
    }    
    
}
