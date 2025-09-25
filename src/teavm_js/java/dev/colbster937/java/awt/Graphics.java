package dev.colbster937.java.awt;

import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;

import dev.colbster937.eagler.Utils;
import dev.colbster937.eagler.CanvasUtils;
import dev.colbster937.java.awt.image.BufferedImage;

public class Graphics {
  private CanvasRenderingContext2D ctx;
  @SuppressWarnings("unused")
  private HTMLCanvasElement canvas;

  public Graphics(CanvasRenderingContext2D ctx, HTMLCanvasElement canvas) {
    this.ctx = ctx;
    this.canvas = canvas;
  }

  public void drawImage(BufferedImage img, int x, int y, int w, int h, Object obs) {
    if (w == Utils.RENDER_SIZE[0] && h == Utils.RENDER_SIZE[1]) {
      int cw = canvas.getWidth();
      int ch = canvas.getHeight();
      double[] t = new double[] { cw / (double) Utils.GAME_SIZE[0], ch / (double) Utils.GAME_SIZE[1] };
      double s = !Utils.FULLSCREEN_ALT ? Math.min(t[0], t[1]) : Math.max(t[0], t[1]);
      w = (int) Math.round(Utils.GAME_SIZE[0] * s);
      h = (int) Math.round(Utils.GAME_SIZE[1] * s);
      x = (cw - w) / 2;
      y = (ch - h) / 2;
      ctx.clearRect(0, 0, cw, ch);
    }
    img.flushToCanvas();
    CanvasUtils.setSmoothing(ctx, false);
    ctx.drawImage(img.backingCanvas(), x, y, w, h);
  }
}
