package dev.colbster937.java.awt.image;

import org.teavm.jso.canvas.ImageData;

public class DataBufferInt {
  private int[] data;
  @SuppressWarnings("unused")
  private ImageData img;

  public DataBufferInt(int size, ImageData img) {
    this.data = new int[size];
    this.img = img;
  }

  public int[] getData() { return data; }
}
