package com.base.console.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StreamTest {

    @Test
    public void Stream_filter_사용() {
        String contents = "abcd,def,ghi,qwe,q,w,aaa";
        List<String> words = List.of(contents.split(","));

        long count = words.stream()
                        .filter(w -> w.length() > 2 )
                        .count();
        assertThat(count, is(5l));
    }

    @Test
    public void ParrallelStream_filter_사용() {
        String contents = "abcd,def,ghi,qwe,q,w,aaa";
        List<String> words = List.of(contents.split(","));

        long count = words.parallelStream()
                .filter(w -> w.length() > 2 )
                .count();
        assertThat(count, is(5l));
    }
}
