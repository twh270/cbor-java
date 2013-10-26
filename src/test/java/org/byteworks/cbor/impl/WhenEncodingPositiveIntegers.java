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
    assertWritten(1, b);
  }

  @Test
  public void encodesOne() throws IOException {
    int b = 1;
    os.write(b);
    assertWritten(1, b);
  }

  @Test
  public void encodes10() throws IOException {
    int b = 10;
    os.write(b);
    assertWritten(1, b);
  }
  
  @Test
  public void encodes16() throws IOException {
    int b = 16;
    os.write(b);
    assertWritten(1, b);
  }
  
  @Test
  public void encodes23() throws IOException {
    int b = 23;
    os.write(b);
    assertWritten(1, b);
  }
  
  @Test
  public void encodes24() throws IOException {
    int b = 24;
    os.write(b);
    assertWritten(2, 0x18, b);
  }
  
  @Test
  public void encodes25() throws IOException {
    int b = 25;
    os.write(b);
    assertWritten(2, 0x18, b);
  }
  
  @Test
  public void encodes255() throws IOException {
    int b = 255;
    os.write(b);
    assertWritten(2, 0x18, b);
  }
  
  private void assertWritten(int length, int...expectedBytes) throws IOException {
    os.close();
    byte[] bytes = bos.toByteArray();
    assertEquals(length, bytes.length);
    for(int i = 0; i < expectedBytes.length; i++) {
      assertEquals((byte) expectedBytes[i], bytes[i]);
    }
  }
}
