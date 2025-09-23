package dev.colbster937.eagler;

import dev.colbster937.eagler.Utils;

import org.teavm.jso.JSBody;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.html.HTMLCanvasElement;

public class CanvasUtils {
  @JSBody(params = { "ctx", "v" }, script = "ctx.imageSmoothingEnabled = v;" +
      "if (ctx.mozImageSmoothingEnabled !== undefined) ctx.mozImageSmoothingEnabled = v;" +
      "if (ctx.webkitImageSmoothingEnabled !== undefined) ctx.webkitImageSmoothingEnabled = v;" +
      "if (ctx.msImageSmoothingEnabled !== undefined) ctx.msImageSmoothingEnabled = v;")
  public static native void setSmoothing(CanvasRenderingContext2D ctx, boolean v);

  public static boolean[] isFixedSize(HTMLCanvasElement canvas) {
    boolean[] result = new boolean[] { false, false };
    if (canvas.getWidth() != 0)
      result[0] = true;
    if (canvas.getHeight() != 0)
      result[1] = true;
    return result;
  }

  public static boolean hasFixedSize(HTMLCanvasElement canvas) {
    boolean[] result = isFixedSize(canvas);
    for (int i = 0; i < result.length; ++i) {
      if (result[i])
        return true;
    }
    return false;
  }

  public static int[] scaleMouse(HTMLCanvasElement canvas, MouseEvent e) {
    return Utils.scaleMouse(canvas.getWidth(), canvas.getHeight(), e.getOffsetX(), e.getOffsetY());
  }
}
