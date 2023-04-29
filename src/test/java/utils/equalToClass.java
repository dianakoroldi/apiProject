package utils;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class equalToClass {

    public static Matcher<Object> equalTo(Object expected) {
        return new BaseMatcher<Object>() {
            @Override
            public boolean matches(Object actual) {
                return expected.equals(actual);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Expected value: " + expected);
            }
        };
    }
}

