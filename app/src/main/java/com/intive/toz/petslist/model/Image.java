package com.intive.toz.petslist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The type Image.
 */
public class Image implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("createDate")
    @Expose
    private Long createDate;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("fileUrl")
    @Expose
    private String fileUrl;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public Long getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(final Long createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * Gets file url.
     *
     * @return the file url
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * Sets file url.
     *
     * @param fileUrl the file url
     */
    public void setFileUrl(final String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
