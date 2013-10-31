package org.byteworks.cbor;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.byteworks.cbor.UInt.UInt16;
import org.byteworks.cbor.UInt.UInt32;
import org.byteworks.cbor.UInt.UInt64;
import org.byteworks.cbor.UInt.UInt8;
import org.junit.Test;

public class WhenUsingUInts {
  @Test
  public void createsUintOfCorrectTypeAndValue() throws Exception {
    createAndExpect(0, UInt8.class);
    createAndExpect(1, UInt8.class);
    createAndExpect(0xff, UInt8.class);
    createAndExpect(0x100, UInt16.class);
    createAndExpect(0xffff, UInt16.class);
    createAndExpect(0x10000, UInt32.class);
    createAndExpect(0xffffffffL, UInt32.class);
    createAndExpect(0x100000000L, UInt64.class);
    createAndExpect(0x7fffffffffffffffL, UInt64.class);
    createAndExpect(new BigInteger("18446744073709551615"), UInt64.class);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void throwsUpWhenLongValueLessThanZero() {
    @SuppressWarnings("unused")
    UInt i = UInt.create(-1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void throwsUpWhenBigIntegerValueLessThanZero() throws Exception {
    @SuppressWarnings("unused")
    UInt i = new UInt.UInt64(new BigInteger("-1"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void throwsUpWhenValueTooLarge() throws Exception {
    @SuppressWarnings("unused")
    UInt i = UInt.create(new BigInteger("18446744073709551616"));
  }
  
  private void createAndExpect(BigInteger value, Class<UInt64> type) {
    UInt i = UInt.create(value);
    assertEquals(i.getClass(), type);
    assertEquals(value, i.asBigInteger());
  }

  private void createAndExpect(long value, Class type) {
    UInt i = UInt.create(value);
    assertEquals(type, i.getClass());
    assertEquals(value, i.longValue());
  }
 
}
