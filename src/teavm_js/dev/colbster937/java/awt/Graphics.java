package dev.colbster937.java.awt;

import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;

import dev.colbster937.eagler.Utils;
import dev.colbster937.java.awt.image.BufferedImage;

public class Graphics {
  private final CanvasRenderingContext2D ctx;
  @SuppressWarnings("unused")
  private final HTMLCanvasElement canvas;

  public Graphics(CanvasRenderingContext2D ctx, HTMLCanvasElement canvas) {
    this.ctx = ctx;
    this.canvas = canvas;
  }

  public void drawImage(BufferedImage img, int x, int y, int w, int h, Object obs) {
    if (w == Utils.RENDER_WIDTH && h == Utils.RENDER_HEIGHT) {
      int cw = canvas.getWidth();
      int ch = canvas.getHeight();
      double s = Math.min(cw / (double)Utils.GAME_WIDTH, ch / (double)Utils.GAME_HEIGHT);
      w = (int)Math.round(Utils.GAME_WIDTH * s);
      h = (int)Math.round(Utils.GAME_HEIGHT * s);
      x = (cw - w) / 2;
      y = (ch - h) / 2;
      ctx.clearRect(0, 0, cw, ch);
    }
    img.flushToCanvas();
    ctx.drawImage(img.backingCanvas(), x, y, w, h);
  }
}
