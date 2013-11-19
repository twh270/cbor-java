package org.byteworks.cbor.impl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.byteworks.cbor.UInt;
import org.byteworks.cbor.UInt.UInt64;
import org.junit.Before;
import org.junit.Test;

public class WhenEncodingNegativeIntegers {
  private ByteArrayOutputStream bos;
  private CBOROutputStreamImpl os;

  @Before
  public void beforeTest() {
    bos = new ByteArrayOutputStream();
    os = new CBOROutputStreamImpl(bos);
  }
  
  @Test
  public void encodesByte_Negative1() throws IOException {
    int b = -1;
    os.write(b);
    assertWritten(1, 0b00100000);
  }

  @Test
  public void encodesByte_Negative10() throws IOException {
    int b = -10;
    os.write(b);
    assertWritten(1, 0b00101001);
  }
  
  @Test
  public void encodesByte_Negative16() throws IOException {
    int b = -16;
    os.write(b);
    assertWritten(1, 0b00101111);
  }
  
  @Test
  public void encodesByte_Negative23() throws IOException {
    int b = -23;
    os.write(b);
    assertWritten(1, 0b00110110);
  }
  
  @Test
  public void encodesByte_Negative24() throws IOException {
    int b = -24;
    os.write(b);
    assertWritten(2, 0x18, b);
  }
  
  @Test
  public void encodesByte_Negative25() throws IOException {
    int b = -25;
    os.write(b);
    assertWritten(2, 0x18, b);
  }
  
  @Test
  public void encodesByte_Negative255() throws IOException {
    int b = -255;
    os.write(b);
    assertWritten(2, 0x18, b);
  }

  @Test
  public void encodesShort_Negative256() throws IOException {
    int b = -256;
    os.writeShort(b);
    assertWritten(3, 0x19, 1, 0);
  }
  
  @Test
  public void encodesLargestNegative16BitInteger() throws IOException {
    int b = 0x8000ffff;
    os.writeShort(b);
    assertWritten(3, 0x19, 0xff, 0xff);
  }
  
  @Test
  public void encodesSmallestNegative32BitInteger() throws IOException {
    int b = 0x80010000;
    os.writeInt(b);
    assertWritten(5, 0x1a, 0x00, 0x01, 0x00, 0x00);
  }
  
  @Test
  public void encodesLargestNegative32BitJavaInteger() throws IOException {
    int b = Integer.MIN_VALUE;
    os.writeInt(b);
    assertWritten(5, 0x1a, 0x7f, 0xff, 0xff, 0xff);
  }
  
  @Test
  public void encodesSmallestNegative64BitInteger() throws IOException {
    long b = 0x8000000100000000L;
    os.writeLong(b);
    assertWritten(9, 0x1b, 0x00, 0x00, 0x00, 0x01, 0x0, 0x00, 0x00, 0x00);
  }
  
  @Test
  public void encodesLargestNegative64BitJavaLong() throws IOException {
    long b = Long.MIN_VALUE;
    os.writeLong(b);
    assertWritten(9, 0x1b, 0x7f, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff);
  }
  
  @Test
  public void encodesLargestNegativeUInt() throws Exception {
    UInt i = UInt.create(UInt64.MAX_VALUE);
    os.write(i);
    assertWritten(9, 0x1b, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff);
  }
  
  private void assertWritten(int length, int...expectedBytes) throws IOException {
    os.close();
    byte[] bytes = bos.toByteArray();
    assertEquals(length, bytes.length);
    assertEquals(expectedBytes.length, bytes.length);
    for(int i = 0; i < expectedBytes.length; i++) {
      assertEquals("byte mismatch at index " + i, (byte) expectedBytes[i], (byte) bytes[i]);
    }
  }

}
