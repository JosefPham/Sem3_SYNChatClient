package Business;

/**
 *
 * @author Group 9
 */
public class Cipher implements CipherInterface {

    protected String cipher(String input) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            //If is for when the index is not in the Alphbeth-array i.e "?".
            if (findCharIndex(input.charAt(i)) == -1) {
                builder.append(input.charAt(i));
            } else {
                builder.append(ALPHABETH[((ALPHABETH.length - 1) - findCharIndex(input.charAt(i)))]);
            }
        }

        return builder.toString();
    }

    protected int findCharIndex(char ch) {
        int index = -1;
        for (int i = 0; i < ALPHABETH.length; i++) {
            if (ch == ALPHABETH[i]) {
                index = i;
            }
        }
        return index;
    }

}
