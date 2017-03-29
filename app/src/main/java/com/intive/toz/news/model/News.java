package com.intive.toz.news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The type News.
 */
public class News implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("contents")
    @Expose
    private String contents;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("created")
    @Expose
    private Long created;
    @SerializedName("lastModified")
    @Expose
    private Long lastModified;
    @SerializedName("published")
    @Expose
    private Long published;

    /**
     *  Gets Id.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     *  Sets Id.
     * @param id id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     *  Gets Title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets Title.
     * @param title title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets content.
     * @return content
     */
    public String getContents() {
        return contents;
    }

    /**
     * Sets content.
     * @param contents content
     */
    public void setContents(final String contents) {
        this.contents = contents;
    }

    /**
     * Gets type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     * @param type type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets PhotoUrl.
     * @return photoUrl
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * Sets PhotoUrl.
     * @param photoUrl photoUrl
     */
    public void setPhotoUrl(final String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     *  Gets date of creation.
     * @return created
     */
    public Long getCreated() {
        return created;
    }

    /**
     * Gets date of last modification.
     * @return lastModified
     */
    public Long getLastModified() {
        return lastModified;
    }

    /**
     * Gets date of publishion.
     * @return published
     */
    public Long getPublished() {
        return published;
    }

}
