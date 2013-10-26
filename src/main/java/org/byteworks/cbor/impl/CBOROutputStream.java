package org.byteworks.cbor.impl;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class CBOROutputStream implements DataOutput {
  private static final int UINT_8 = 0x18;
  private static final int UINT_16 = 0x19;
  private static final int UINT_32 = 0x1a;
  private static final int UINT_64 = 0x1b;
  
  private OutputStream output;

  public CBOROutputStream(OutputStream output) {
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeChar");
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

}
