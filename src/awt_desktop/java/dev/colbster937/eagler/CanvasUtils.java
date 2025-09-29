package dev.colbster937.eagler;

import java.awt.Canvas;
import java.awt.event.MouseEvent;

public class CanvasUtils {
  public static boolean[] isFixedSize(Canvas canvas) {
    boolean[] result = new boolean[] { false, false };
    if (canvas.getWidth() != 0)
      result[0] = true;
    if (canvas.getHeight() != 0)
      result[1] = true;
    return result;
  }

  public static boolean hasFixedSize(Canvas canvas) {
    boolean[] result = isFixedSize(canvas);
    for (int i = 0; i < result.length; ++i) {
      if (result[i])
        return true;
    }
    return false;
  }

  public static int[] scaleMouse(Canvas canvas, MouseEvent e) {
    return Utils.scaleMouse(canvas.getWidth(), canvas.getHeight(), e.getX(), e.getY());
  }
}
