package com.internationalairport.airportmanagementsystem.CustomMatchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeMatcher extends TypeSafeMatcher<String> {

    private final LocalDateTime expected;

    public LocalDateTimeMatcher(LocalDateTime expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(String actual) {
        LocalDateTime actualDateTime = LocalDateTime.parse(actual, DateTimeFormatter.ISO_DATE_TIME);
        return actualDateTime.withNano(actualDateTime.getNano() / 1000 * 1000)
                .equals(expected.withNano(expected.getNano() / 1000 * 1000));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a LocalDateTime matching ").appendValue(expected);
    }

    public static LocalDateTimeMatcher matchesLocalDateTime(LocalDateTime expected) {
        return new LocalDateTimeMatcher(expected);
    }
}
