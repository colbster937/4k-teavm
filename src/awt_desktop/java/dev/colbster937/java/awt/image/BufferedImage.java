package dev.colbster937.java.awt.image;

import java.awt.Graphics2D;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;

public class BufferedImage {
  private int w, h;
  private jBufferedImage backing;
  private DataBufferInt buffer;
  private WritableRaster raster;
  private int[] pixels;

  public BufferedImage(int width, int height, int type) {
    this.w = width;
    this.h = height;

    this.pixels = new int[w * h];

    this.buffer = new DataBufferInt(pixels, pixels.length);

    int[] masks = new int[] { 0x00FF0000, 0x0000FF00, 0x000000FF, 0xFF000000 };

    SampleModel sm = new SinglePixelPackedSampleModel(java.awt.image.DataBuffer.TYPE_INT, w, h, masks);
    this.raster = Raster.createWritableRaster(sm, buffer, null);

    ColorModel cm = new DirectColorModel(32, masks[0], masks[1], masks[2], masks[3]);

    this.backing = new jBufferedImage(cm, raster, false, null);
  }

  public int getWidth() { return w; }
  public int getHeight() { return h; }
  public WritableRaster getRaster() { return raster; }
  public jBufferedImage backingImage() { return backing; }
  public Graphics2D createGraphics() { return backing.createGraphics(); }

  public void flushToCanvas() {
    for (int i = 0; i < pixels.length; i++) pixels[i] |= 0xFF000000;
  }
}
