package org.byteworks.cbor;

import java.math.BigInteger;

public abstract class UInt extends Number implements Comparable<UInt> {
  private static final long serialVersionUID = 7656188894308157342L;
  private static final BigInteger LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);

  public static UInt create(BigInteger i) {
    if (i.compareTo(LONG_MAX_VALUE) < 1) {
      return create(i.longValue());
    }
    return new UInt64(i);
  }
  
  public static UInt create(long msb, long lsb) {
    BigInteger i = BigInteger.valueOf(msb);
    i.shiftLeft(32);
    i.add(BigInteger.valueOf(lsb));
    return create(i);
  }
  
  public static UInt create(long i) {
    if (i <= UInt8.MAX_VALUE) return new UInt8((short) i);
    if (i <= UInt16.MAX_VALUE) return new UInt16((int) i);
    if (i <= UInt32.MAX_VALUE) return new UInt32(i);
    return new UInt64(BigInteger.valueOf(i));
  }

  public abstract BigInteger asBigInteger();
  
  @Override
  public int compareTo(UInt other) {
    if (this instanceof UInt64 || other instanceof UInt64) {
      BigInteger left = this.asBigInteger();
      BigInteger right = other.asBigInteger();
      return left.compareTo(right);
    }
    long result = this.longValue() - other.longValue();
    if ((result & 0xffffffff) != 0)
      return (int) result;
    if (result > Integer.MAX_VALUE) result = Integer.MAX_VALUE; 
    else if (result < Integer.MIN_VALUE) result = Integer.MIN_VALUE;
    return (int) result;
  }

  public static class UInt8 extends UInt {
    private static final long serialVersionUID = 9045927147646632343L;
    public static final short MIN_VALUE = 0x00;
    public static final short MAX_VALUE = 0xff;
    private final short value;
    
    public UInt8(short i) {
      this.value = i;
      if (value > MAX_VALUE || value < 0) {
        throw new IllegalArgumentException("Value is too big or small for a UInt8: " + i);
      }
    }

    public short getValue() {
      return value;
    }
    
    public byte byteValue() {
      return (byte) value;
    }
    
    public short shortValue() {
      return value;
    }
    
    public int intValue() {
      return value;
    }
    
    public long longValue() {
      return value;
    }

    public float floatValue() {
      return value;
    }

    public double doubleValue() {
      return value;
    }

    public BigInteger asBigInteger() {
      return BigInteger.valueOf(value); 
    }
    
    @Override
    public String toString() {
      return Short.toString(value);
    }
  }

  public static class UInt16 extends UInt {
    private static final long serialVersionUID = -5945082148274240252L;
    public static final int MIN_VALUE = 0x0000;
    public static final int MAX_VALUE = 0xffff;
    private final int value;
    
    public UInt16(int i) {
      this.value = i;
      if (value > MAX_VALUE || value < 0) {
        throw new IllegalArgumentException("Value is too big or small for a UInt16: " + i);
      }
    }
    
    public int getValue() {
      return value;
    }
    
    public byte byteValue() {
      return (byte) value;
    }
    
    public short shortValue() {
      return (short) value;
    }
    
    public int intValue() {
      return value;
    }
    
    public long longValue() {
      return value;
    }
  
    public float floatValue() {
      return value;
    }

    public double doubleValue() {
      return value;
    }

    public BigInteger asBigInteger() {
      return BigInteger.valueOf(value); 
    }
    
    @Override
    public String toString() {
      return Integer.toString(value);
    }

}
  
  public static class UInt32 extends UInt {
    private static final long serialVersionUID = 6525343052143387679L;
    public static final long MIN_VALUE = 0x00000000L;
    public static final long MAX_VALUE = 0xffffffffL;
    private final long value;
    
    public UInt32(long i) {
      this.value = i;
      if (value > MAX_VALUE || value < 0) {
        throw new IllegalArgumentException("Value is too big or small for a UInt32: " + i);
      }
    }
    
    public byte byteValue() {
      return (byte) value;
    }
    
    public long getValue() {
      return value;
    }

    public short shortValue() {
      return (short) value;
    }
    
    public int intValue() {
      return (int) value;
    }
    
    public long longValue() {
      return value;
    }

    public float floatValue() {
      return value;
    }

    public double doubleValue() {
      return value;
    }
    
    public BigInteger asBigInteger() {
      return BigInteger.valueOf(value); 
    }
    
    @Override
    public String toString() {
      return Long.toString(value);
    }

}
  
  public static class UInt64 extends UInt {
    private static final long serialVersionUID = -1196655589956409160L;
    public static final long MIN_VALUE = 0x0000000000000000L;
    public static final long MAX_LONG_VALUE = 0x7fffffffffffffffL;
    public static final BigInteger MAX_VALUE = new BigInteger("18446744073709551615");
    private static final BigInteger NEGATIVE_ONE = new BigInteger("-1");
    private final BigInteger value;
    
    public UInt64(BigInteger i) {
      this.value = i;
      if (MAX_VALUE.compareTo(value) < 0 || NEGATIVE_ONE.compareTo(value) >= 0) {
        throw new IllegalArgumentException("Value is too big or small for a UInt64: " + value.toString(16));
      }
    }
    
    public BigInteger getValue() {
      return value;
    }

    public byte byteValue() {
      return value.byteValue();
    }
    
    public short shortValue() {
      return value.shortValue();
    }
    
    public int intValue() {
      return value.intValue();
    }
    
    public long longValue() {
      return value.longValue();
    }

    public float floatValue() {
      return value.floatValue();
    }
    
    public double doubleValue() {
      return value.doubleValue();
    }
    
    public BigInteger asBigInteger() {
      return value; 
    }

    @Override
    public String toString() {
      return value.toString();
    }

  }


}
