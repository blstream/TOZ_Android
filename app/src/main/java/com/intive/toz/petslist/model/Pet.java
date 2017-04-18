package com.intive.toz.petslist.model;

import java.io.Serializable;

/**
 * Pet model class.
 */
public class Pet implements Serializable {

    private String id;
    private String name;
    private String type;
    private String sex;
    private String description;
    private String address;
    private long created;
    private long lastModified;

    /**
     * @return imgUrl of Pet.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    private String imageUrl;

    /**
     * @return name of Pet.
     */
    public String getName() {
        return name;
    }

    /**
     * @return type of Pet.
     */
    public String getType() {
        return type;
    }

    /**
     * @return description of Pet.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return address of Pet.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return id of Pet.
     */
    public String getId() {
        return id;
    }

    /**
     * @return sex of Pet.
     */
    public String getSex() {
        return sex;
    }

}