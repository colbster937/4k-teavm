package dev.colbster937.java.applet;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.InputStream;

import dev.colbster937.eagler.Utils;
import dev.colbster937.eagler.render.Canvas;
import dev.colbster937.eagler.render.RenderContext;
import dev.colbster937.java.awt.Event;
import dev.colbster937.java.awt.Graphics;
import dev.colbster937.java.awt.jCanvas;

public class Applet extends JFrame {
  public jCanvas canvas;
  public BufferStrategy bs;

  public Applet() {
    super(Utils.NAME);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(Utils.RENDER_SIZE[0], Utils.RENDER_SIZE[1]);
    setLayout(new BorderLayout());
    setBackground(Color.BLACK);
    setLocationRelativeTo(null);
    setVisible(true);
    setIgnoreRepaint(true);
    canvas = new jCanvas();
    canvas.setSize(Utils.RENDER_SIZE[0], Utils.RENDER_SIZE[1]);
    canvas.setIgnoreRepaint(true);
    add(canvas, BorderLayout.CENTER);
    canvas.setFocusable(true);
    canvas.requestFocus();
    canvas.createBufferStrategy(2);
    bs = canvas.getBufferStrategy();
    setIconImage(loadImage("/icon.png"));
  }

  public Image loadImage(String name) {
    try (InputStream in = Applet.class.getResourceAsStream(name)) {
      if (in != null) {
        return ImageIO.read(in);
      }
    } catch (Exception e) {e.printStackTrace();}
    return null;
  }

  public void setIconImage(Image image) {
    super.setIconImage(image);
    if (image == null) return;
    try {
      Class<?> cls1 = Class.forName("java.awt.Taskbar");
      Object taskbar = cls1.getMethod("getTaskbar").invoke(null);
      Class<?> cls2 = Class.forName("java.awt.Taskbar$Feature");
      if ((Boolean) cls1.getMethod("isSupported", cls2).invoke(taskbar,  Enum.valueOf((Class<Enum>) cls2, "ICON_IMAGE"))) {
        cls1.getMethod("setIconImage", Image.class).invoke(taskbar, image);
        return;
      }
    } catch (Exception e) {}
    try {
      Class<?> cls = Class.forName("com.apple.eawt.Application");
      Object app = cls.getMethod("getApplication").invoke(null);
      cls.getMethod("setDockIconImage", Image.class).invoke(app, image);
    } catch (Exception e) {}
  }

  public void setArgs(String[] args) {
    for (String arg : args) {
      if (arg.startsWith("--mode")) {
        String mode = arg.split("=")[1];
        if (mode.equals("fullscreen")) {
          Utils.FULLSCREEN_ALT = false;
        } else if (mode.equals("border")) {
          Utils.FULLSCREEN_ALT = true;
        }
      }
    }
  }

  public void start() {}

  public boolean handleEvent(Event e) { return true; }

  public boolean isActive() { return true; }

  boolean b = false;

  public Graphics getGraphics() {
    if (b) {
      try { bs.show(); } finally { Toolkit.getDefaultToolkit().sync(); }
      b = false;
    }
    b = true;
    return new Graphics((Graphics2D) bs.getDrawGraphics(), canvas);
  }

  public void present() {
    try { bs.show(); } finally { Toolkit.getDefaultToolkit().sync(); }  }

  public Applet(Canvas c, RenderContext ctx) {
    this();
  }
}
