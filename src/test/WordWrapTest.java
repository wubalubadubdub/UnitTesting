package test;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bearg on 5/13/2016.
 */
public class WordWrapTest {

    //
    private static final int CHARS_BEFORE_WRAP = 5;

    // a method that breaks words on specified space with new line, like a word processor would

    // 1 wrap
    @Test
    public void lineShouldWrapIfOverLineLength() {

        final String result = WordWrap.wrap("The sleepy", CHARS_BEFORE_WRAP);
        Assert.assertEquals("The s\nleepy", result); //after 5 chars, should wrap
    }

    // 0 wraps
    @Test
    public void shortLinesShouldNotWrap() {

        final String result = WordWrap.wrap("The", CHARS_BEFORE_WRAP);
        Assert.assertEquals("The", result);
    }

    // 2 wraps
    @Test
    public void longerLineShouldWrapTwice() {

        final String result = WordWrap.wrap("The sleepy brow", CHARS_BEFORE_WRAP);
        Assert.assertEquals("The s\nleepy\n brow", result);
    }

    @Test
    public void evenLongerLinesShouldWrapThrice() {

        final String result = WordWrap.wrap("The sleepy brown fox", CHARS_BEFORE_WRAP);
        Assert.assertEquals("The s\nleepy\n brow\nn fox", result);

    }

    @Test
    public void longLinesDontNeedToBeMultipleOfCharsBeforeWrap() {

        final String result = WordWrap.wrap("The sleepy brown fox.", CHARS_BEFORE_WRAP);
        Assert.assertEquals("The s\nleepy\n brow\nn fox\n.", result);

    }


}
