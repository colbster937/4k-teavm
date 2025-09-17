package dev.colbster937.java.awt.image;

import org.teavm.jso.canvas.ImageData;

public class DataBufferInt {
  private final int[] data;
  @SuppressWarnings("unused")
  private final ImageData img;

  public DataBufferInt(int size, ImageData img) {
    this.data = new int[size];
    this.img = img;
  }

  public int[] getData() { return data; }
}
