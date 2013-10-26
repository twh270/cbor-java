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
  public void encodesByte_0() throws IOException {
    int b = 0;
    os.write(b);
    assertWritten(1, b);
  }

  @Test
  public void encodesByte_1() throws IOException {
    int b = 1;
    os.write(b);
    assertWritten(1, b);
  }

  @Test
  public void encodesByte_10() throws IOException {
    int b = 10;
    os.write(b);
    assertWritten(1, b);
  }
  
  @Test
  public void encodesByte_16() throws IOException {
    int b = 16;
    os.write(b);
    assertWritten(1, b);
  }
  
  @Test
  public void encodesByte_23() throws IOException {
    int b = 23;
    os.write(b);
    assertWritten(1, b);
  }
  
  @Test
  public void encodesByte_24() throws IOException {
    int b = 24;
    os.write(b);
    assertWritten(2, 0x18, b);
  }
  
  @Test
  public void encodesByte_25() throws IOException {
    int b = 25;
    os.write(b);
    assertWritten(2, 0x18, b);
  }
  
  @Test
  public void encodesByte_255() throws IOException {
    int b = 255;
    os.write(b);
    assertWritten(2, 0x18, b);
  }

  @Test
  public void encodesShort_256() throws IOException {
    int b = 256;
    os.writeShort(b);
    assertWritten(3, 0x19, 1, 0);
  }
  
  @Test
  public void encodesLargest16BitInteger() throws IOException {
    int b = 0xffff;
    os.writeShort(b);
    assertWritten(3, 0x19, 0xff, 0xff);
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
