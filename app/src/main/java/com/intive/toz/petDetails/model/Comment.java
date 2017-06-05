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
    @SerializedName("authorName")
    @Expose
    private String authorName;
    @SerializedName("authorSurname")
    @Expose
    private String authorSurname;

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

    /**
     * Sets contents.
     *
     * @param contents the contents
     */
    public void setContents(final String contents) {
        this.contents = contents;
    }

    /**
     * Sets pet uuid.
     *
     * @param petUuid the pet uuid
     */
    public void setPetUuid(final String petUuid) {
        this.petUuid = petUuid;
    }

    /**
     * Gets author name.
     *
     * @return the author name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets author name.
     *
     * @param authorName the author name
     */
    public void setAuthorName(final String authorName) {
        this.authorName = authorName;
    }

    /**
     * Gets author surname.
     *
     * @return the author surname
     */
    public String getAuthorSurname() {
        return authorSurname;
    }

    /**
     * Sets author surname.
     *
     * @param authorSurname the author surname
     */
    public void setAuthorSurname(final String authorSurname) {
        this.authorSurname = authorSurname;
    }
}
