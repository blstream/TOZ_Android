package com.intive.toz.Retrofit;

import java.util.ArrayList;

/**
 * Pet list model class.
 */
public class PetsList {

    private ArrayList<Pet> pets = new ArrayList<>();

    /**
     * returns list of pets.
     * @return ArrayList
     */
    public ArrayList<Pet> getPets() {
        return pets;
    }

   /* public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }*/
}
