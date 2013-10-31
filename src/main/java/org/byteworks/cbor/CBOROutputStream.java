package org.byteworks.cbor;

import java.io.DataOutput;
import java.io.IOException;

public interface CBOROutputStream extends DataOutput {
  void write(UInt uint) throws IOException;
}
