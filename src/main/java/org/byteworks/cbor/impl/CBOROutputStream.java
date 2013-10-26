package org.byteworks.cbor.impl;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class CBOROutputStream implements DataOutput {
  private OutputStream output;
  private static final int UINT_8 = 0x18;

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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeShort");
  }

  @Override
  public void writeChar(int v) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeChar");
  }

  @Override
  public void writeInt(int v) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeInt");
  }

  @Override
  public void writeLong(long v) throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented: writeLong");
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
