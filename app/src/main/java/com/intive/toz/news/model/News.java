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
    private long created;
    @SerializedName("lastModified")
    @Expose
    private long lastModified;
    @SerializedName("published")
    @Expose
    private long published;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets contents.
     *
     * @return the contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets photoUrl.
     *
     * @return the photoUrl
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public long getCreated() {
        return created;
    }

    /**
     * Gets lastModified.
     *
     * @return the lastModified
     */
    public long getLastModified() {
        return lastModified;
    }

    /**
     * Gets published.
     *
     * @return the published
     */
    public long getPublished() {
        return published;
    }
}
