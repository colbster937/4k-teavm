package dev.colbster937.eagler;

import dev.colbster937.eagler.Utils;

import org.teavm.jso.JSBody;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.html.HTMLCanvasElement;

public class CanvasUtil {
  @JSBody(params = { "ctx", "v" }, script =
    "ctx.imageSmoothingEnabled = v;" +
    "if (ctx.mozImageSmoothingEnabled !== undefined) ctx.mozImageSmoothingEnabled = v;" +
    "if (ctx.webkitImageSmoothingEnabled !== undefined) ctx.webkitImageSmoothingEnabled = v;" +
    "if (ctx.msImageSmoothingEnabled !== undefined) ctx.msImageSmoothingEnabled = v;")
  public static native void setSmoothing(CanvasRenderingContext2D ctx, boolean v);

  private static int[] scaleMouse(HTMLCanvasElement canvas, int offsetX, int offsetY) {
    int cw = canvas.getWidth();
    int ch = canvas.getHeight();

    double s = Math.min(cw / Utils.GAME_WIDTH, ch / Utils.GAME_HEIGHT);
    int dw = (int) Math.round(Utils.GAME_WIDTH * s);
    int dh = (int) Math.round(Utils.GAME_HEIGHT * s);
    int dx = (cw - dw) / 2;
    int dy = (ch - dh) / 2;

    if (offsetX < dx || offsetX >= dx + dw || offsetY < dy || offsetY >= dy + dh) {
      return new int[] { -1, -1 };
    }

    double u = (offsetX - dx) / (double) dw;
    double v = (offsetY - dy) / (double) dh;
    int mx = (int) Math.round(u * Utils.RENDER_WIDTH);
    int my = (int) Math.round(v * Utils.RENDER_HEIGHT);

    if (mx < 0) mx = 0; else if (mx > Utils.RENDER_WIDTH - 1) mx = Utils.RENDER_WIDTH - 1;
    if (my < 0) my = 0; else if (my > Utils.RENDER_HEIGHT) my = Utils.RENDER_HEIGHT - 1;
    return new int[] { mx, my };
  }

  public static int[] scaleMouse(HTMLCanvasElement canvas, MouseEvent e) { return scaleMouse(canvas, e.getOffsetX(), e.getOffsetY()); }

  public static boolean[] isFixedSize(HTMLCanvasElement canvas) {
    boolean[] result = new boolean[] { false, false };
    if (canvas.getWidth() != 0) result[0] = true;
    if (canvas.getHeight() != 0) result[1] = true;
    return result;
  }

  public static boolean hasFixedSize(HTMLCanvasElement canvas) {
    boolean[] result = isFixedSize(canvas);
    if (result[0] || result[1]) return true;
    else return false;
  }
}
