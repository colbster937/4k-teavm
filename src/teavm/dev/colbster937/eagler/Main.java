package dev.colbster937.eagler;

import org.teavm.jso.browser.Window;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.MouseEvent;

import dev.colbster937.render.Canvas;
import dev.colbster937.render.RenderContext;

import m4k.M;

public class Main {
  private static M game;
  private static Window window;
  private static HTMLDocument doc;
  private static Canvas canvas;
  private static RenderContext ctx;
  private static int w;
  private static int h;
  
  private static String id = "game_frame";
  private static int lx = 0;
  private static int ly = 0;

  public static void main(String[] args) {
    window = Window.current();
    doc = window.getDocument();
    HTMLElement node = doc.getElementById(id);

    w = window.getInnerWidth();
    h = window.getInnerHeight();

    if (node == null) {
      createCanvas(doc.getBody());
    } else if (!(node instanceof HTMLCanvasElement)) {
      node.removeAttribute("id");
      createCanvas(node);
    } else {
      canvas = (Canvas) node;
      canvas.setWidth(w);
      canvas.setHeight(h);
    }

    ctx = (RenderContext) canvas.getContext("2d");

    game = new M(canvas, ctx);
    game.start();

    Util.setSmoothing(ctx, false);
    canvas.setTabIndex(0);
    canvas.focus();

    canvas.addEventListener("mousedown", (MouseEvent e) -> {
      int[] p = Util.scaleMouse(canvas, e);
      AwtEvent ev = new AwtEvent();
      ev.id = 501;
      ev.x = p[0];
      ev.y = p[1];
      ev.modifiers = (e.getButton() == 2 ? 4 : 0);
      game.handleEvent(ev);
      e.preventDefault();
    });

    canvas.addEventListener("mouseup", (MouseEvent e) -> {
      int[] p = Util.scaleMouse(canvas, e);
      AwtEvent ev = new AwtEvent();
      ev.id = 502;
      ev.x = p[0];
      ev.y = p[1];
      ev.modifiers = (e.getButton() == 2 ? 4 : 0);
      game.handleEvent(ev);
      e.preventDefault();
    });

    canvas.addEventListener("mousemove", (MouseEvent e) -> {
      int[] p;
      if (doc.getPointerLockElement() == canvas) {
        lx += e.getMovementX();
        ly += e.getMovementY();

        if (lx < 0) lx = 0;
        if (ly < 0) ly = 0;
        if (lx > canvas.getWidth()) lx = canvas.getWidth();
        if (ly > canvas.getHeight()) ly = canvas.getHeight();

        p = new int[] { lx, ly };
      } else {
        p = Util.scaleMouse(canvas, e);
        lx = p[0];
        ly = p[1];
      }
      AwtEvent ev = new AwtEvent();
      ev.id = 503;
      ev.x = p[0];
      ev.y = p[1];
      game.handleEvent(ev);
    });

    canvas.addEventListener("mouseleave", (MouseEvent e) -> {
      AwtEvent ev = new AwtEvent();
      ev.id = 505;
      game.handleEvent(ev);
    });

    canvas.addEventListener("contextmenu", (Event e) -> {
      e.preventDefault();
    });

    canvas.addEventListener("keydown", (KeyboardEvent e) -> {
      AwtEvent ev = new AwtEvent();
      ev.id = 401;
      int code = e.getKeyCode();
      if (code == 76 || code == 108) {
        if (doc.getPointerLockElement() == canvas) {
          doc.exitPointerLock();
        } else {
          canvas.requestPointerLock();
        }
        e.preventDefault();
        return;
      }
      if (code >= 65 && code <= 90) code += 32;
      ev.key = code;
      game.handleEvent(ev);
      e.preventDefault();
    });

    canvas.addEventListener("keyup", (KeyboardEvent e) -> {
      AwtEvent ev = new AwtEvent();
      ev.id = 402;
      int code = e.getKeyCode();
      if (code >= 65 && code <= 90) code += 32;
      ev.key = code;
      game.handleEvent(ev);
      e.preventDefault();
    });

    window.addEventListener("resize", (Event e) -> {
      int nw = window.getInnerWidth();
      int nh = window.getInnerHeight();
      canvas.setWidth(nw);
      canvas.setHeight(nh);
      w = nw;
      h = nh;
    });

    doc.addEventListener("click", (Event e) -> {
      canvas.focus();
    });
  }

  private static void createCanvas(HTMLElement parent) {
    canvas = (Canvas) doc.createElement("canvas");
    canvas.setId(id);
    canvas.setWidth(w);
    canvas.setHeight(h);
    parent.appendChild(canvas);
  }

  public static int getWidth() {
    return w;
  }

  public static int getHeight() {
    return h;
  }
}
