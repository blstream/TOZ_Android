package com.intive.toz.data;

/**
 * To format IBAN with prefix and gap between section.
 */

public class IBANFormatter {

    final int numberIBANisPL = 26;
    final String prefix = "PL";
    final int segments = 6;
    final int[] gap = {0, 2, 6, 10, 14, 18, 22};

    /**
     * method to add prefix and gap.
     *
     * @param toConvert IBAN from financial info.
     * @return formatted string IBAN.
     */
    public String toIBAM(final String toConvert) {
        if (toConvert.length() == numberIBANisPL) {
            StringBuilder result = new StringBuilder();
            result.append(prefix);

            for (int j = 0; j < segments; j++) {
                String fra = toConvert.substring(gap[j], gap[j + 1]);
                result.append(fra).append(" ");
            }
            return result.toString();
        } else {
            return toConvert; // not our interest, is not PL.
        }
    }
}
