package dev.colbster937.java.applet;

import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;

import dev.colbster937.java.awt.Event;
import dev.colbster937.java.awt.Graphics;

public class Applet {
  private final HTMLCanvasElement canvas;
  private final CanvasRenderingContext2D ctx;

  public Applet(HTMLCanvasElement canvas, CanvasRenderingContext2D ctx) {
    this.canvas = canvas;
    this.ctx = ctx;
  }

  public void start() {}
  public boolean isActive() { return true; }

  public Graphics getGraphics() {
    return new Graphics(ctx, canvas);
  }

  public boolean handleEvent(Event e) { return true; }

  public int getWidth() {
    return canvas.getWidth();
  }

  public int getHeight() {
    return canvas.getHeight();
  }
}
