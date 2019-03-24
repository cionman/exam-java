package com.base.console.utillibrary;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ApacheCommonsTest {

    @Test
    public void 문자열_HTML_형식으로_변형() {
        final String exampleText = "Left & Right";
        final String escapedString = StringEscapeUtils.escapeHtml4(exampleText);
        assertEquals("Left &amp; Right", escapedString);
    }

    @Test
    public void InputStream객체를_OutputStream객체에_연결하기() throws IOException {
        final String exampleText = "Text To be Streamed";
        final InputStream inputStream = new ByteArrayInputStream(exampleText.getBytes());
        final OutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(inputStream, outputStream);

        final String streamContents = ((ByteArrayOutputStream) outputStream).toString();
        assertEquals(exampleText, streamContents);
        assertNotSame(exampleText, streamContents); // 같은 객체를 참조 하지 않음을 확인
    }

    @Test
    public void InputStream객체를_String_객체로_변환하기() throws IOException {
        String exampleText = "An example String";
        final InputStream inputStream = new ByteArrayInputStream(exampleText.getBytes());
        final String consumedString = IOUtils.toString(inputStream, "UTF-8");

        assertEquals(exampleText, consumedString);
    }

    @Test
    public void OutputStream_객체_나누기() throws IOException {
        String exampleText = "예제 데이터";
        final InputStream inputStream = IOUtils.toInputStream(exampleText, "UTF-8");
        final File tempFile = File.createTempFile("example", "txt");
        tempFile.deleteOnExit();
        final OutputStream outputStream1 = new FileOutputStream(tempFile);
        final OutputStream outputStream2 = new ByteArrayOutputStream();
        final OutputStream tee = new TeeOutputStream(outputStream1, outputStream2);

        IOUtils.copy(inputStream, tee);

        final FileInputStream fis = new FileInputStream(tempFile);
        final String stream1Contents = IOUtils.toString(fis, "UTF-8");
        final String stream2Contents = outputStream2.toString();

        assertEquals(exampleText, stream1Contents);
        assertEquals(exampleText, stream2Contents);
    }
}
