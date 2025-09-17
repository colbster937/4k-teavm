package dev.colbster937.java.awt.image;

public class WritableRaster {
  private final DataBufferInt db;
  public WritableRaster(DataBufferInt db) { this.db = db; }
  public DataBufferInt getDataBuffer() { return db; }
}
