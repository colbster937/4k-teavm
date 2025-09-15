package dev.colbster937.eagler;

import org.teavm.jso.JSBody;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.html.HTMLCanvasElement;

public class Util {
  @JSBody(params = { "ctx", "v" }, script =
    "ctx.imageSmoothingEnabled = v;" +
    "if (ctx.mozImageSmoothingEnabled !== undefined) ctx.mozImageSmoothingEnabled = v;" +
    "if (ctx.webkitImageSmoothingEnabled !== undefined) ctx.webkitImageSmoothingEnabled = v;" +
    "if (ctx.msImageSmoothingEnabled !== undefined) ctx.msImageSmoothingEnabled = v;")
  public static native void setSmoothing(CanvasRenderingContext2D ctx, boolean v);

  private static int[] scaleMouse(HTMLCanvasElement canvas, int offsetX, int offsetY) {
    int cw = canvas.getWidth();
    int ch = canvas.getHeight();

    double s  = Math.min(cw / 214.0, ch / 120.0);
    int dw = (int) Math.round(214 * s);
    int dh = (int) Math.round(120 * s);
    int dx = (cw - dw) / 2;
    int dy = (ch - dh) / 2;

    if (offsetX < dx || offsetX >= dx + dw || offsetY < dy || offsetY >= dy + dh) {
      return new int[] { -1, -1 };
    }

    double u = (offsetX - dx) / (double) dw;
    double v = (offsetY - dy) / (double) dh;
    int mx = (int) Math.round(u * 856.0);
    int my = (int) Math.round(v * 480.0);

    if (mx < 0) mx = 0; else if (mx > 855) mx = 855;
    if (my < 0) my = 0; else if (my > 479) my = 479;
    return new int[] { mx, my };
  }

  public static int[] scaleMouse(HTMLCanvasElement canvas, MouseEvent e) {
    return scaleMouse(canvas, e.getOffsetX(), e.getOffsetY());
  }
}
