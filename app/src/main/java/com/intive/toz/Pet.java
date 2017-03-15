package com.intive.toz;

/**
 * Pet model class.
 */
public class Pet {

    private String name;
    private String description;
    private String type;
    private String gender;
    private String email;
    private String address;
    private String date;
    private String profilePic;

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
     * @return date.
     */
    public String getDate() {
        return date;
    }

    /**
     * @return url to profile picture.
     */
    public String getProfilePic() {
        return profilePic;
    }


}