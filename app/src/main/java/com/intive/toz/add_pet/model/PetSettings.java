package com.intive.toz.add_pet.model;

/**
 * The type Pet settings.
 */
public class PetSettings {
    /**
     * The enum Types.
     */
    public enum TYPES {
        /**
         * Cat types.
         */
        CAT {
            /**
             * Cat to string implementation.
             *
             * @return the cat string
             */
            public String toString() {
                return "Kot";
            }
        },
        /**
         * Dog types.
         */
        DOG {
            /**
             * Dog to string implementation.
             *
             * @return the dog string
             */
            public String toString() {
                return "Pies";
            }
        }
    }

    /**
     * The enum Gender.
     */
    public enum GENDER {
        /**
         * Male gender.
         */
        MALE {
            /**
             * Male to string implementation.
             *
             * @return the male string
             */
            public String toString() {
                return "Samiec";
            }
        },
        /**
         * Female gender.
         */
        FEMALE {
            /**
             * Female to string implementation.
             *
             * @return the female string
             */
            public String toString() {
                return "Samica";
            }
        }
    }

}
