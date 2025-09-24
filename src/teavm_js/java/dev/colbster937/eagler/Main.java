package dev.colbster937.eagler;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.MouseEvent;

import dev.colbster937.eagler.Utils;
import dev.colbster937.eagler.AwtEvent;
import dev.colbster937.eagler.render.Canvas;
import dev.colbster937.eagler.render.RenderContext;

import mc4k.M;

public class Main {
  private static M game;
  private static Window window;
  private static HTMLDocument doc;
  private static Canvas canvas;
  private static RenderContext ctx;
  private static int[] d;
  private static boolean fs;

  private static String id = "game_frame";

  public static void main(String[] args) {
    window = Window.current();
    doc = window.getDocument();
    HTMLElement node = doc.getElementById(id);
    d = new int[2];

    d[0] = Utils.RENDER_SIZE[0];
    d[1] = Utils.RENDER_SIZE[1];

    if (node == null) {
      canvas = createCanvas(doc.getBody());
    } else if (!(node instanceof HTMLCanvasElement)) {
      node.removeAttribute("id");
      canvas = createCanvas(node);
      if (node.hasAttribute("fullscreen")) {
        node.removeAttribute("fullscreen");
        canvas.setAttribute("fullscreen", "");
      }
    } else {
      canvas = (Canvas) node;
      boolean[] f = CanvasUtils.isFixedSize(canvas);
      if (!f[0])
        canvas.setWidth(d[0]);
      else
        d[0] = canvas.getWidth();
      if (!f[1])
        canvas.setHeight(d[1]);
      else
        d[1] = canvas.getHeight();
    }

    ctx = (RenderContext) canvas.getContext("2d");

    if (canvas.hasAttribute("fullscreen")) {
      canvas.removeAttribute("fullscreen");
      d[0] = window.getInnerWidth();
      d[1] = window.getInnerHeight();
      canvas.setWidth(d[0]);
      canvas.setHeight(d[1]);
      doc.getBody().setAttribute("style", "margin:0;overflow:hidden;height:100vh;width:100vw;");
      fs = true;
      Utils.FULLSCREEN_ALT = canvas.hasAttribute("fullscreen-alt");
      if (Utils.FULLSCREEN_ALT) {
        canvas.removeAttribute("fullscreen-alt");
      }
    } else {
      resize();
    }

    game = new M(canvas, ctx);
    game.start();

    canvas.setTabIndex(0);
    canvas.setAttribute("style", "outline:none;border:none;image-rendering:pixelated;");
    canvas.focus();

    canvas.addEventListener("mousedown", (MouseEvent e) -> {
      int[] p = CanvasUtils.scaleMouse(canvas, e);
      AwtEvent ev = new AwtEvent();
      ev.id = 501;
      ev.x = p[0];
      ev.y = p[1];
      ev.modifiers = (e.getButton() == 2 ? 4 : 0);
      game.handleEvent(ev);
      e.preventDefault();
    });

    canvas.addEventListener("mouseup", (MouseEvent e) -> {
      int[] p = CanvasUtils.scaleMouse(canvas, e);
      AwtEvent ev = new AwtEvent();
      ev.id = 502;
      ev.x = p[0];
      ev.y = p[1];
      ev.modifiers = (e.getButton() == 2 ? 4 : 0);
      game.handleEvent(ev);
      e.preventDefault();
    });

    canvas.addEventListener("mousemove", (MouseEvent e) -> {
      int[] p = CanvasUtils.scaleMouse(canvas, e);
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
      ev.key = Utils.keyUtoL(e.getKeyCode());
      game.handleEvent(ev);
      e.preventDefault();
    });

    canvas.addEventListener("keyup", (KeyboardEvent e) -> {
      AwtEvent ev = new AwtEvent();
      ev.id = 402;
      ev.key = Utils.keyUtoL(e.getKeyCode());
      game.handleEvent(ev);
      e.preventDefault();
    });

    window.addEventListener("resize", (Event e) -> {
      resize();
    });

    doc.addEventListener("click", (Event e) -> {
      canvas.focus();
    });
  }

  private static Canvas createCanvas(HTMLElement parent) {
    Canvas c = (Canvas) doc.createElement("canvas");
    c.setId(id);
    c.setWidth(d[0]);
    c.setHeight(d[1]);
    parent.appendChild(c);
    return c;
  }

  private static void resize() {
    boolean[] f = CanvasUtils.isFixedSize(canvas);
    int nw = window.getInnerWidth();
    int nh = window.getInnerHeight();
    if (fs || !f[0] || nw < d[0]) {
      canvas.setWidth(nw);
      d[0] = nw;
    }
    if (fs || !f[1] || nh < d[1]) {
      canvas.setHeight(nh);
      d[1] = nh;
    }
  }

  public static int getWidth() {
    return d[0];
  }

  public static int getHeight() {
    return d[1];
  }
}
