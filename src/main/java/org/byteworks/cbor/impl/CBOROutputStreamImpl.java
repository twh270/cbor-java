package org.byteworks.cbor.impl;

import java.io.IOException;
import java.io.OutputStream;

import org.byteworks.cbor.CBOROutputStream;
import org.byteworks.cbor.UInt;
import org.byteworks.cbor.UInt.UInt64;

public class CBOROutputStreamImpl implements CBOROutputStream {
  private static final int UINT_8 = 0x18;
  private static final int UINT_16 = 0x19;
  private static final int UINT_32 = 0x1a;
  private static final int UINT_64 = 0x1b;
  
  private OutputStream output;

  public CBOROutputStreamImpl(OutputStream output) {
    this.output = output;
  }

  @Override
  public void write(int b) throws IOException {
    writeByte(b);
  }

  @Override
  public void write(byte[] b) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: write");
  }

  @Override
  public void write(byte[] b, int off, int len) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: write");
  }

  @Override
  public void writeBoolean(boolean v) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeBoolean");
  }

  @Override
  public void writeByte(int v) throws IOException {
    if (v < 24) 
      output.write(v);
    else {
      output.write(UINT_8);
      output.write(v);
    }
  }

  @Override
  public void writeShort(int v) throws IOException {
    output.write(UINT_16);
    output.write((v >>> 8) & 0xff);
    output.write(v & 0xff);
  }

  @Override
  public void writeChar(int v) throws IOException {
    output.write(UINT_16);
    output.write((v >>> 8) & 0xff);
    output.write(v & 0xff);
  }

  @Override
  public void writeInt(int v) throws IOException {
    output.write(UINT_32);
    output.write((v >>> 24) & 0xff);
    output.write((v >>> 16) & 0xff);
    output.write((v >>>  8) & 0xff);
    output.write(v & 0xff);    
  }

  @Override
  public void writeLong(long v) throws IOException {
    output.write(UINT_64);
    output.write((int)((v >> 56) & 0xff));
    output.write((int)((v >> 48) & 0xff));
    output.write((int)((v >> 40) & 0xff));
    output.write((int)((v >> 32) & 0xff));
    output.write((int)((v >> 24) & 0xff));
    output.write((int)((v >> 16) & 0xff));
    output.write((int)((v >> 8) & 0xff));
    output.write((int)(v & 0xff));
  }

  @Override
  public void writeFloat(float v) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeFloat");
  }

  @Override
  public void writeDouble(double v) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeDouble");
  }

  @Override
  public void writeBytes(String s) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeBytes");
  }

  @Override
  public void writeChars(String s) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeChars");
  }

  @Override
  public void writeUTF(String s) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeUTF");
  }

  public void close() throws IOException {
    output.flush();
    output.close();
  }

  @Override
  public void write(UInt uint) throws IOException {
    long longValue = uint.longValue();
    if (longValue == -1) {
      writeBigIntegerValue(uint);
    }
    else writeLong(longValue);
  }

  private void writeBigIntegerValue(UInt uint) throws IOException {
    // just in case some joker figures out how to create a uint bigger than MAX_VALUE
    if (!(uint.asBigInteger().compareTo(UInt64.MAX_VALUE) == 0)) {
      throw new IOException("Illegal unsigned integer value " + uint.toString()); 
    }
    output.write(UINT_64);
    for(int i = 0; i < 8; i++) {
      output.write(0xff);
    }
  }
}
