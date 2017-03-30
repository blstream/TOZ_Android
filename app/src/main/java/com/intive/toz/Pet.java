package com.intive.toz;

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

    /**
     * @return name of Pet.
     */
    public String getName() {
        return name;
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

    /**
     * @return type of Pet.
     */
    public String getType() {
        return type;
    }

    /**
     * @return date created record of Pet.
     */
    public long getCreated() {
        return created;
    }
}