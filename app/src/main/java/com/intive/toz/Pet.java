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
    private long lastModified;

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

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public String getType() {
        return type;
    }

    public long getCreated() {
        return created;
    }

    public long getLastModified() {
        return lastModified;
    }
}