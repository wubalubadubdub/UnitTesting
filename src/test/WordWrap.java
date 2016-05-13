package test;

/**
 * Created by bearg on 5/13/2016.
 */
public class WordWrap {

    public static String wrap(final String inputLine, final int charactersBeforeWrap) {

        final StringBuilder accumulator = new StringBuilder();
        final int inputLength = inputLine.length();

        accumulator.append(
                inputLine, 0, Math.min(inputLength, charactersBeforeWrap));

        int split = charactersBeforeWrap;


        while (inputLength > split) {

            accumulator.append("\n");
            accumulator.append(inputLine, split, Math.min(inputLength, split + charactersBeforeWrap));
            split += charactersBeforeWrap;
        }

        return accumulator.toString();
    }
}
