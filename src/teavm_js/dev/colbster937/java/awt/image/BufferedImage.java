package dev.colbster937.java.awt.image;

import org.teavm.jso.browser.Window;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.canvas.ImageData;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.typedarrays.Uint8ClampedArray;

public class BufferedImage {
  private final int w, h;
  private final HTMLCanvasElement backing;
  private final CanvasRenderingContext2D bctx;
  private final ImageData imgData;
  private final DataBufferInt buffer;

  public BufferedImage(int w, int h, int type) {
    this.w = w;
    this.h = h;
    var doc = Window.current().getDocument();
    this.backing = (HTMLCanvasElement) doc.createElement("canvas");
    this.backing.setWidth(w);
    this.backing.setHeight(h);
    this.bctx = (CanvasRenderingContext2D) backing.getContext("2d");
    this.imgData = bctx.createImageData(w, h);
    this.buffer = new DataBufferInt(w * h, imgData);
  }

  public int getWidth() { return w; }
  public int getHeight() { return h; }

  public WritableRaster getRaster() { return new WritableRaster(buffer); }

  public void flushToCanvas() {
    Uint8ClampedArray px = imgData.getData();
    int[] src = buffer.getData();
    for (int i = 0, j = 0; i < src.length; i++, j += 4) {
      int rgb = src[i];
      px.set(j, (rgb >> 16) & 255);
      px.set(j + 1, (rgb >> 8) & 255);
      px.set(j + 2, (rgb) & 255);
      px.set(j + 3, 255);
    }
    bctx.putImageData(imgData, 0, 0);
  }

  public HTMLCanvasElement backingCanvas() { return backing; }
}