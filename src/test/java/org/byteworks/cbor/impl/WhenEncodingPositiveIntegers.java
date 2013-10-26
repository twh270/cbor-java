package org.byteworks.cbor.impl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class WhenEncodingPositiveIntegers {
  private ByteArrayOutputStream bos;
  private CBOROutputStream os;

  @Before
  public void beforeTest() {
    bos = new ByteArrayOutputStream();
    os = new CBOROutputStream(bos);
  }
  
  @Test
  public void encodesZero() throws IOException {
    int b = 0;
    os.write(b);
    assertWritten(1, 0);
  }

  @Test
  public void encodesOne() throws IOException {
    int b = 1;
    os.write(b);
    assertWritten(1, 1);
  }

  private void assertWritten(int length, int...expectedBytes) throws IOException {
    os.close();
    byte[] bytes = bos.toByteArray();
    assertEquals(length, bytes.length);
    for(int i = 0; i < expectedBytes.length; i++) {
      assertEquals(expectedBytes[i], bytes[i]);
    }
  }
}
