package com.intive.toz.petslist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Pet model class.
 */
public class Pet implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("created")
    @Expose
    private Long created;
    @SerializedName("lastModified")
    @Expose
    private Long lastModified;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("gallery")
    @Expose
    private List<Image> gallery = null;

    /**
     * Gets created.
     *
     * @return created date Pet.
     */
    public long getCreated() {
        return created;
    }

    /**
     * Gets image url.
     *
     * @return imgUrl of Pet.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Gets name.
     *
     * @return name of Pet.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets type.
     *
     * @return type of Pet.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets description.
     *
     * @return description of Pet.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets address.
     *
     * @return Address of Pet.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets id.
     *
     * @return id of Pet.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets sex.
     *
     * @return sex of Pet.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Sets sex.
     *
     * @param sex the sex
     */
    public void setSex(final String sex) {
        this.sex = sex;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * Gets gallery.
     *
     * @return the gallery
     */
    public List<Image> getGallery() {
        return gallery;
    }

    /**
     * Sets gallery.
     *
     * @param gallery the gallery
     */
    public void setGallery(final List<Image> gallery) {
        this.gallery = gallery;
    }
}