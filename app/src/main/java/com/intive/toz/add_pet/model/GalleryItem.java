package com.intive.toz.add_pet.model;

import com.esafirm.imagepicker.model.Image;

/**
 * The type Gallery item.
 */
public class GalleryItem {
    private Image image;
    private boolean isButton;

    /**
     * Instantiates a new Gallery item.
     *
     * @param image    the image
     * @param isButton the is button
     */
    public GalleryItem(final Image image, final boolean isButton) {
        this.image = image;
        this.isButton = isButton;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(final Image image) {
        this.image = image;
    }

    /**
     * Is button boolean.
     *
     * @return the boolean
     */
    public boolean isButton() {
        return isButton;
    }

    /**
     * Sets button.
     *
     * @param button the button
     */
    public void setButton(final boolean button) {
        isButton = button;
    }
}
