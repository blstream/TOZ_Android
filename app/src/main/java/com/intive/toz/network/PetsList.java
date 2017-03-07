package com.intive.toz.network;

import com.intive.toz.Pet;

import java.util.ArrayList;

/**
 * Pet list model class.
 */
public class PetsList {

    private ArrayList<Pet> pets = new ArrayList<>();

    /**
     * returns list of pets.
     *
     * @return ArrayList
     */
    public ArrayList<Pet> getPets() {
        return pets;
    }

}
