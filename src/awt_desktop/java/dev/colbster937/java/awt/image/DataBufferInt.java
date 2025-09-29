package dev.colbster937.java.awt.image;

public class DataBufferInt extends java.awt.image.DataBuffer {
  private int[] data;

  public DataBufferInt(int size) {
    super(TYPE_INT, size);
    this.data = new int[size];
  }

  public DataBufferInt(int[] dataArray, int size) {
    super(TYPE_INT, size);
    this.data = dataArray;
  }

  public int[] getData() { return data; }

  public int getElem(int bank, int i) { return data[i]; }

  public void setElem(int bank, int i, int val) { data[i] = val; }
}
