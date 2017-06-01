package com.intive.toz.petDetails.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Comment.
 */
public class Comment {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("contents")
    @Expose
    private String contents;
    @SerializedName("userUuid")
    @Expose
    private String userUuid;
    @SerializedName("petUuid")
    @Expose
    private String petUuid;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("created")
    @Expose
    private Long created;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
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
     * Gets user uuid.
     *
     * @return the user uuid
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * Gets pet uuid.
     *
     * @return the pet uuid
     */
    public String getPetUuid() {
        return petUuid;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public Long getCreated() {
        return created;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setPetUuid(String petUuid) {
        this.petUuid = petUuid;
    }
}
