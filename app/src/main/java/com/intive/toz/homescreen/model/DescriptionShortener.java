package com.intive.toz.homescreen.model;

/**
 *  Class for shortening the description used in news/pets lists.
 */

public final class DescriptionShortener {

    private static final int DESCRIPTION_LENGTH = 100;

    private DescriptionShortener() {
    }

    /**
     *  Returns the beginning of a long string, so the shorter version of a string can be
     *  displayed e.g. in a news/pets' list description
     *
     * @param descriptionContent string that should be shortened
     * @return shortened string (beginning of the string)
     */
    public static String shortenDescription(final String descriptionContent) {
        String shortDescription = descriptionContent.substring(0, DESCRIPTION_LENGTH);
        return shortDescription;
    }
}
