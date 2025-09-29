package dev.colbster937.java.applet;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;

import dev.colbster937.eagler.Utils;
import dev.colbster937.eagler.render.Canvas;
import dev.colbster937.eagler.render.RenderContext;
import dev.colbster937.java.awt.Event;
import dev.colbster937.java.awt.Graphics;
import dev.colbster937.java.awt.jCanvas;

public class Applet extends JFrame {
  public jCanvas canvas;

  public Applet() {
    super(Utils.NAME);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(Utils.RENDER_SIZE[0], Utils.RENDER_SIZE[1]);
    setLayout(new BorderLayout());
    setBackground(Color.BLACK);
    canvas = new jCanvas();
    canvas.setSize(Utils.RENDER_SIZE[0], Utils.RENDER_SIZE[1]);
    add(canvas, BorderLayout.CENTER);
    setLocationRelativeTo(null);
    setVisible(true);
    setIgnoreRepaint(true);
    canvas.setFocusable(true);
    canvas.requestFocus();
  }

  public void setArgs(String[] args) {
    for (String arg : args) {
      if (arg.startsWith("--mode")) {
        String mode = arg.split("=")[1];
        System.out.println(mode);
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

  public Graphics getGraphics() {
    return new Graphics((Graphics2D) canvas.getGraphics(), canvas);
  }

  public Applet(Canvas c, RenderContext ctx) {
    this();
  }
}
