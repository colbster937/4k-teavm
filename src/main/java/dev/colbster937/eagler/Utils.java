package dev.colbster937.eagler;

public class Utils {
  public static String NAME = "Minecraft 4k";
  public static String NAME_SHORT = "4k";
  public static int[] GAME_SIZE = new int[] { 214, 120 };
  public static int[] RENDER_SIZE = new int[] { 856, 480 };
  public static boolean FULLSCREEN_ALT = false;

  public static int[] scaleMouse(int cw, int ch, int ox, int oy) {
    double s = FULLSCREEN_ALT ? Math.min(cw / GAME_SIZE[0], ch / GAME_SIZE[1]) : Math.max(cw / GAME_SIZE[0], ch / GAME_SIZE[1]);
    int dw = (int) Math.round(GAME_SIZE[0] * s);
    int dh = (int) Math.round(GAME_SIZE[1] * s);
    int dx = (cw - dw) / 2;
    int dy = (ch - dh) / 2;

    if (ox < dx || ox >= dx + dw || oy < dy || oy >= dy + dh) {
      return new int[] { -1, -1 };
    }

    double u = (ox - dx) / (double) dw;
    double v = (oy - dy) / (double) dh;
    int mx = (int) Math.round(u * RENDER_SIZE[0]);
    int my = (int) Math.round(v * RENDER_SIZE[1]);

    if (mx < 0)
      mx = 0;
    else if (mx > RENDER_SIZE[0] - 1)
      mx = RENDER_SIZE[0] - 1;
    if (my < 0)
      my = 0;
    else if (my > RENDER_SIZE[1])
      my = RENDER_SIZE[1] - 1;
    return new int[] { mx, my };
  }

  public static int keyUtoL(int key) {
    if (key >= 65 && key <= 90) key += 32;
    return key;
  }

  public static int keyLtoU(int key) {
    if (key >= 97 && key <= 122) key -= 32;
    return key;
  }
}