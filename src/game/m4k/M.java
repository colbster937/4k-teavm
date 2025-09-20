package m4k;

import java.util.Random;

import dev.colbster937.java.applet.Applet;
import dev.colbster937.java.awt.Event;
import dev.colbster937.java.awt.image.BufferedImage;
import dev.colbster937.java.awt.image.DataBufferInt;

public class M extends Applet implements Runnable {
   public M(dev.colbster937.render.Canvas canvas, dev.colbster937.render.RenderContext ctx) {
      super(canvas, ctx);
   }
   
   private int[] M = new int[32767];

   @Override
   public void start() {
      new Thread(this).start();
   }

   @Override
   public void run() {
      try {
         Random var1 = new Random();
         BufferedImage var2 = new BufferedImage(214, 120, 1);
         int[] var3 = ((DataBufferInt)var2.getRaster().getDataBuffer()).getData();
         int[] var4 = new int[262144];
         var1.setSeed(18295169L);

         for (int var5 = 0; var5 < 262144; var5++) {
            var4[var5] = var5 / 64 % 64 > 32 + var1.nextInt(8) ? var1.nextInt(8) + 1 : 0;
         }

         int[] var57 = new int[12288];

         for (int var6 = 1; var6 < 16; var6++) {
            int var7 = 255 - var1.nextInt(96);

            for (int var8 = 0; var8 < 48; var8++) {
               for (int var9 = 0; var9 < 16; var9++) {
                  int var10 = 9858122;
                  if (var6 == 4) {
                     var10 = 8355711;
                  }

                  if (var6 != 4 || var1.nextInt(3) == 0) {
                     var7 = 255 - var1.nextInt(96);
                  }

                  if (var6 == 1 && var8 < (var9 * var9 * 3 + var9 * 81 >> 2 & 3) + 18) {
                     var10 = 6990400;
                  } else if (var6 == 1 && var8 < (var9 * var9 * 3 + var9 * 81 >> 2 & 3) + 19) {
                     var7 = var7 * 2 / 3;
                  }

                  if (var6 == 7) {
                     var10 = 6771249;
                     if (var9 > 0 && var9 < 15 && (var8 > 0 && var8 < 15 || var8 > 32 && var8 < 47)) {
                        var10 = 12359778;
                        int var11 = var9 - 7;
                        int var12 = (var8 & 15) - 7;
                        if (var11 < 0) {
                           var11 = 1 - var11;
                        }

                        if (var12 < 0) {
                           var12 = 1 - var12;
                        }

                        if (var12 > var11) {
                           var11 = var12;
                        }

                        var7 = 196 - var1.nextInt(32) + var11 % 3 * 32;
                     } else if (var1.nextInt(2) == 0) {
                        var7 = var7 * (150 - (var9 & 1) * 100) / 100;
                     }
                  }

                  if (var6 == 5) {
                     var10 = 11876885;
                     if ((var9 + var8 / 4 * 4) % 8 == 0 || var8 % 4 == 0) {
                        var10 = 12365733;
                     }
                  }

                  int var65 = var7;
                  if (var8 >= 32) {
                     var65 = var7 / 2;
                  }

                  if (var6 == 8) {
                     var10 = 5298487;
                     if (var1.nextInt(2) == 0) {
                        var10 = 0;
                        var65 = 255;
                     }
                  }

                  int var68 = (var10 >> 16 & 0xFF) * var65 / 255 << 16 | (var10 >> 8 & 0xFF) * var65 / 255 << 8 | (var10 & 0xFF) * var65 / 255;
                  var57[var9 + var8 * 16 + var6 * 256 * 3] = var68;
               }
            }
         }

         float var58 = 96.5F;
         float var59 = 65.0F;
         float var60 = 96.5F;
         float var61 = 0.0F;
         float var63 = 0.0F;
         float var66 = 0.0F;
         long var69 = System.currentTimeMillis();
         int var14 = -1;
         int var15 = 0;
         float var16 = 0.0F;
         float var17 = 0.0F;

         while (true) {
            float var18 = (float)Math.sin(var16);
            float var19 = (float)Math.cos(var16);
            float var20 = (float)Math.sin(var17);
            float var21 = (float)Math.cos(var17);

            while (System.currentTimeMillis() - var69 > 10L) {
               if (this.M[2] > 0) {
                  float var22 = (this.M[2] - 428) / 214.0F * 2.0F;
                  float var23 = (this.M[3] - 240) / 120.0F * 2.0F;
                  float var24 = (float)Math.sqrt(var22 * var22 + var23 * var23) - 1.2F;
                  if (var24 < 0.0F) {
                     var24 = 0.0F;
                  }

                  if (var24 > 0.0F) {
                     var16 += var22 * var24 / 400.0F;
                     var17 -= var23 * var24 / 400.0F;
                     if (var17 < -1.57F) {
                        var17 = -1.57F;
                     }

                     if (var17 > 1.57F) {
                        var17 = 1.57F;
                     }
                  }
               }

               var69 += 10L;
               float var71 = 0.0F;
               float var75 = 0.0F;
               var75 += (this.M[119] - this.M[115]) * 0.02F;
               var71 += (this.M[100] - this.M[97]) * 0.02F;
               var61 *= 0.5F;
               var63 *= 0.99F;
               var66 *= 0.5F;
               var61 += var18 * var75 + var19 * var71;
               var66 += var19 * var75 - var18 * var71;
               var63 += 0.003F;

               label265:
               for (int var79 = 0; var79 < 3; var79++) {
                  float var25 = var58 + var61 * ((var79 + 0) % 3 / 2);
                  float var26 = var59 + var63 * ((var79 + 1) % 3 / 2);
                  float var27 = var60 + var66 * ((var79 + 2) % 3 / 2);

                  for (int var28 = 0; var28 < 12; var28++) {
                     int var29 = (int)(var25 + (var28 >> 0 & 1) * 0.6F - 0.3F) - 64;
                     int var30 = (int)(var26 + ((var28 >> 2) - 1) * 0.8F + 0.65F) - 64;
                     int var31 = (int)(var27 + (var28 >> 1 & 1) * 0.6F - 0.3F) - 64;
                     if (var29 < 0 || var30 < 0 || var31 < 0 || var29 >= 64 || var30 >= 64 || var31 >= 64 || var4[var29 + var30 * 64 + var31 * 4096] > 0) {
                        if (var79 == 1) {
                           if (this.M[32] > 0 && var63 > 0.0F) {
                              this.M[32] = 0;
                              var63 = -0.1F;
                           } else {
                              var63 = 0.0F;
                           }
                        }
                        continue label265;
                     }
                  }

                  var58 = var25;
                  var59 = var26;
                  var60 = var27;
               }
            }

            int var73 = 0;
            int var77 = 0;
            if (this.M[1] > 0 && var14 > 0) {
               var4[var14] = 0;
               this.M[1] = 0;
            }

            if (this.M[0] > 0 && var14 > 0) {
               var4[var14 + var15] = 1;
               this.M[0] = 0;
            }

            for (int var80 = 0; var80 < 12; var80++) {
               int var82 = (int)(var58 + (var80 >> 0 & 1) * 0.6F - 0.3F) - 64;
               int var84 = (int)(var59 + ((var80 >> 2) - 1) * 0.8F + 0.65F) - 64;
               int var86 = (int)(var60 + (var80 >> 1 & 1) * 0.6F - 0.3F) - 64;
               if (var82 >= 0 && var84 >= 0 && var86 >= 0 && var82 < 64 && var84 < 64 && var86 < 64) {
                  var4[var82 + var84 * 64 + var86 * 4096] = 0;
               }
            }

            int var81 = -1;

            for (int var83 = 0; var83 < 214; var83++) {
               float var85 = (var83 - 107) / 90.0F;

               for (int var87 = 0; var87 < 120; var87++) {
                  float var88 = (var87 - 60) / 90.0F;
                  float var89 = 1.0F;
                  float var90 = var89 * var21 + var88 * var20;
                  float var91 = var88 * var21 - var89 * var20;
                  float var32 = var85 * var19 + var90 * var18;
                  float var33 = var90 * var19 - var85 * var18;
                  int var34 = 0;
                  int var35 = 255;
                  double var36 = 20.0;
                  float var38 = 5.0F;

                  for (int var39 = 0; var39 < 3; var39++) {
                     float var40 = var32;
                     if (var39 == 1) {
                        var40 = var91;
                     }

                     if (var39 == 2) {
                        var40 = var33;
                     }

                     float var41 = 1.0F / (var40 < 0.0F ? -var40 : var40);
                     float var42 = var32 * var41;
                     float var43 = var91 * var41;
                     float var44 = var33 * var41;
                     float var45 = var58 - (int)var58;
                     if (var39 == 1) {
                        var45 = var59 - (int)var59;
                     }

                     if (var39 == 2) {
                        var45 = var60 - (int)var60;
                     }

                     if (var40 > 0.0F) {
                        var45 = 1.0F - var45;
                     }

                     float var46 = var41 * var45;
                     float var47 = var58 + var42 * var45;
                     float var48 = var59 + var43 * var45;
                     float var49 = var60 + var44 * var45;
                     if (var40 < 0.0F) {
                        if (var39 == 0) {
                           var47--;
                        }

                        if (var39 == 1) {
                           var48--;
                        }

                        if (var39 == 2) {
                           var49--;
                        }
                     }

                     while (var46 < var36) {
                        int var50 = (int)var47 - 64;
                        int var51 = (int)var48 - 64;
                        int var52 = (int)var49 - 64;
                        if (var50 < 0 || var51 < 0 || var52 < 0 || var50 >= 64 || var51 >= 64 || var52 >= 64) {
                           break;
                        }

                        int var53 = var50 + var51 * 64 + var52 * 4096;
                        int var54 = var4[var53];
                        if (var54 > 0) {
                           var73 = (int)((var47 + var49) * 16.0F) & 15;
                           var77 = ((int)(var48 * 16.0F) & 15) + 16;
                           if (var39 == 1) {
                              var73 = (int)(var47 * 16.0F) & 15;
                              var77 = (int)(var49 * 16.0F) & 15;
                              if (var43 < 0.0F) {
                                 var77 += 32;
                              }
                           }

                           int var55 = 16777215;
                           if (var53 != var14 || var73 > 0 && var77 % 16 > 0 && var73 < 15 && var77 % 16 < 15) {
                              var55 = var57[var73 + var77 * 16 + var54 * 256 * 3];
                           }

                           if (var46 < var38 && var83 == this.M[2] / 4 && var87 == this.M[3] / 4) {
                              var81 = var53;
                              byte var70 = 1;
                              if (var40 > 0.0F) {
                                 var70 = -1;
                              }

                              var15 = var70 << 6 * var39;
                              var38 = var46;
                           }

                           if (var55 > 0) {
                              var34 = var55;
                              var35 = 255 - (int)(var46 / 20.0F * 255.0F);
                              var35 = var35 * (255 - (var39 + 2) % 3 * 50) / 255;
                              var36 = var46;
                           }
                        }

                        var47 += var42;
                        var48 += var43;
                        var49 += var44;
                        var46 += var41;
                     }
                  }

                  int var93 = (var34 >> 16 & 0xFF) * var35 / 255;
                  int var94 = (var34 >> 8 & 0xFF) * var35 / 255;
                  int var95 = (var34 & 0xFF) * var35 / 255;
                  var3[var83 + var87 * 214] = var93 << 16 | var94 << 8 | var95;
               }
            }

            var14 = var81;
            Thread.sleep(2L);
            if (!this.isActive()) {
               return;
            }

            this.getGraphics().drawImage(var2, 0, 0, 856, 480, null);
         }
      } catch (Exception var56) {
      }
   }

   @Override
   public boolean handleEvent(Event var1) {
      byte var2 = 0;
      switch (var1.id) {
         case 401:
            var2 = 1;
         case 402:
            this.M[var1.key] = var2;
            break;
         case 501:
            var2 = 1;
            this.M[2] = var1.x;
            this.M[3] = var1.y;
         case 502:
            if ((var1.modifiers & 4) > 0) {
               this.M[1] = var2;
            } else {
               this.M[0] = var2;
            }
            break;
         case 503:
         case 506:
            this.M[2] = var1.x;
            this.M[3] = var1.y;
            break;
         case 505:
            this.M[2] = 0;
      }

      return true;
   }
}