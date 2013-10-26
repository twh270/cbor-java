package org.byteworks.cbor.impl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

public class WhenEncodingPositiveIntegers {
  @Test
  public void encodesZero() throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    CBOROutputStream os = new CBOROutputStream(bos);
    int b = 0;
    os.write(b);
    os.close();
    byte[] bytes = bos.toByteArray();
    assertEquals(1, bytes.length);
    assertEquals(0, bytes[0]);
  }
}
