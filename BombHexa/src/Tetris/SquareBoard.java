/*     */ package Tetris;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ public class SquareBoard
/*     */ {
/*  44 */   private int width = 0;
/*     */ 
/*  49 */   private int height = 0;
/*     */ 
/*  56 */   private Color[][] matrix = null;
/*     */ 
/*  62 */   private String message = null;
/*     */ 
/*  68 */   private int removedLines = 0;
/*     */ 
/*  75 */   private SquareBoardComponent component = null;
/*     */ 
/*     */   public SquareBoard(int width, int height)
/*     */   {
/*  85 */     this.width = width;
/*  86 */     this.height = height;
/*  87 */     this.matrix = new Color[height][width];
/*  88 */     clear();
/*     */   }
/*     */ 
/*     */   public boolean isSquareEmpty(int x, int y)
/*     */   {
/* 104 */     if ((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height)) {
/* 105 */       return (x >= 0) && (x < this.width) && (y < 0);
/*     */     }
/* 107 */     return this.matrix[y][x] == null;
/*     */   }
/*     */ 
/*     */   public boolean isLineEmpty(int y)
/*     */   {
/* 122 */     if ((y < 0) || (y >= this.height)) {
/* 123 */       return false;
/*     */     }
/* 125 */     for (int x = 0; x < this.width; x++) {
/* 126 */       if (this.matrix[y][x] != null) {
/* 127 */         return false;
/*     */       }
/*     */     }
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isLineFull(int y)
/*     */   {
/* 144 */     if ((y < 0) || (y >= this.height)) {
/* 145 */       return true;
/*     */     }
/* 147 */     for (int x = 0; x < this.width; x++) {
/* 148 */       if (this.matrix[y][x] == null) {
/* 149 */         return false;
/*     */       }
/*     */     }
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean hasFullLines()
/*     */   {
/* 162 */     for (int y = this.height - 1; y >= 0; y--) {
/* 163 */       if (isLineFull(y)) {
/* 164 */         return true;
/*     */       }
/*     */     }
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */   public Component getComponent()
/*     */   {
/* 180 */     if (this.component == null) {
/* 181 */       this.component = new SquareBoardComponent();
/*     */     }
/* 183 */     return this.component;
/*     */   }
/*     */ 
/*     */   public int getBoardHeight()
/*     */   {
/* 193 */     return this.height;
/*     */   }
/*     */ 
/*     */   public int getBoardWidth()
/*     */   {
/* 203 */     return this.width;
/*     */   }
/*     */ 
/*     */   public int getRemovedLines()
/*     */   {
/* 212 */     return this.removedLines;
/*     */   }
/*     */ 
/*     */   public Color getSquareColor(int x, int y)
/*     */   {
/* 225 */     if ((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height)) {
/* 226 */       return null;
/*     */     }
/* 228 */     return this.matrix[y][x];
/*     */   }
/*     */ 
/*     */   public void setSquareColor(int x, int y, Color color)
/*     */   {
/* 243 */     if ((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height)) {
/* 244 */       return;
/*     */     }
/* 246 */     this.matrix[y][x] = color;
/* 247 */     if (this.component != null)
/* 248 */       this.component.invalidateSquare(x, y);
/*     */   }
/*     */ 
/*     */   public void setMessage(String message)
/*     */   {
/* 261 */     this.message = message;
/* 262 */     if (this.component != null)
/* 263 */       this.component.redrawAll();
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 273 */     this.removedLines = 0;
/* 274 */     for (int y = 0; y < this.height; y++) {
/* 275 */       for (int x = 0; x < this.width; x++) {
/* 276 */         this.matrix[y][x] = null;
/*     */       }
/*     */     }
/* 279 */     if (this.component != null)
/* 280 */       this.component.redrawAll();
/*     */   }
/*     */ 
/*     */   public void removeFullLines()
/*     */   {
/* 293 */     boolean repaint = false;
/*     */ 
/* 296 */     for (int y = this.height - 1; y >= 0; y--) {
/* 297 */       if (isLineFull(y)) {
/* 298 */         removeLine(y);
/* 299 */         this.removedLines += 1;
/* 300 */         repaint = true;
/* 301 */         y++;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 306 */     if ((repaint) && (this.component != null))
/* 307 */       this.component.redrawAll();
/*     */   }
/*     */ 
/*     */   private void removeLine(int y)
/*     */   {
/* 319 */     if ((y < 0) || (y >= this.height))
/* 320 */       return;
/*     */     do
/*     */     {
/* 323 */       for (int x = 0; x < this.width; x++)
/* 324 */         this.matrix[y][x] = this.matrix[(y - 1)][x];
/* 322 */       y--; } while (y > 0);
/*     */ 
/* 327 */     for (int x = 0; x < this.width; x++)
/* 328 */       this.matrix[0][x] = null;
/*     */   }
/*     */ 
/*     */   public void update()
/*     */   {
/* 337 */     this.component.redraw();
/*     */   }
/*     */ 
/*     */   private class SquareBoardComponent extends Component
/*     */   {
/* 354 */     private Dimension size = null;
/*     */ 
/* 362 */     private Insets insets = new Insets(0, 0, 0, 0);
/*     */ 
/* 369 */     private Dimension squareSize = new Dimension(0, 0);
/*     */ 
/* 378 */     private Image bufferImage = null;
/*     */ 
/* 385 */     private Rectangle bufferRect = new Rectangle();
/*     */ 
/* 390 */     private Color messageColor = Color.white;
/*     */ 
/* 397 */     private Hashtable lighterColors = new Hashtable();
/*     */ 
/* 404 */     private Hashtable darkerColors = new Hashtable();
/*     */ 
/* 409 */     private boolean updated = true;
/*     */ 
/* 415 */     private Rectangle updateRect = new Rectangle();
/*     */ 
/*     */     public SquareBoardComponent()
/*     */     {
/* 421 */       setBackground(Configuration.getColor("board.background", "#000000"));
/*     */ 
/* 423 */       this.messageColor = Configuration.getColor("board.message", "#ffffff");
/*     */     }
/*     */ 
/*     */     public void invalidateSquare(int x, int y)
/*     */     {
/* 434 */       if (this.updated) {
/* 435 */         this.updated = false;
/* 436 */         this.updateRect.x = x;
/* 437 */         this.updateRect.y = y;
/* 438 */         this.updateRect.width = 0;
/* 439 */         this.updateRect.height = 0;
/*     */       } else {
/* 441 */         if (x < this.updateRect.x) {
/* 442 */           this.updateRect.width += this.updateRect.x - x;
/* 443 */           this.updateRect.x = x;
/* 444 */         } else if (x > this.updateRect.x + this.updateRect.width) {
/* 445 */           this.updateRect.width = (x - this.updateRect.x);
/*     */         }
/* 447 */         if (y < this.updateRect.y) {
/* 448 */           this.updateRect.height += this.updateRect.y - y;
/* 449 */           this.updateRect.y = y;
/* 450 */         } else if (y > this.updateRect.y + this.updateRect.height) {
/* 451 */           this.updateRect.height = (y - this.updateRect.y);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     public void redraw()
/*     */     {
/* 465 */       if (!this.updated) {
/* 466 */         this.updated = true;
/* 467 */         Graphics g = getGraphics();
/* 468 */         g.setClip(this.insets.left + this.updateRect.x * this.squareSize.width, this.insets.top + this.updateRect.y * this.squareSize.height, (this.updateRect.width + 1) * this.squareSize.width, (this.updateRect.height + 1) * this.squareSize.height);
/*     */ 
/* 472 */         paint(g);
/*     */       }
/*     */     }
/*     */ 
/*     */     public void redrawAll()
/*     */     {
/* 482 */       this.updated = true;
/* 483 */       Graphics g = getGraphics();
/* 484 */       g.setClip(this.insets.left, this.insets.top, SquareBoard.this.width * this.squareSize.width, SquareBoard.this.height * this.squareSize.height);
/*     */ 
/* 488 */       paint(g);
/*     */     }
/*     */ 
/*     */     public boolean isDoubleBuffered()
/*     */     {
/* 497 */       return true;
/*     */     }
/*     */ 
/*     */     public Dimension getPreferredSize()
/*     */     {
/* 506 */       return new Dimension(SquareBoard.this.width * 20, SquareBoard.this.height * 20);
/*     */     }
/*     */ 
/*     */     public Dimension getMinimumSize()
/*     */     {
/* 515 */       return getPreferredSize();
/*     */     }
/*     */ 
/*     */     public Dimension getMaximumSize()
/*     */     {
/* 524 */       return getPreferredSize();
/*     */     }
/*     */ 
/*     */     private Color getLighterColor(Color c)
/*     */     {
/* 541 */       Color lighter = (Color)this.lighterColors.get(c);
/* 542 */       if (lighter == null) {
/* 543 */         lighter = c.brighter().brighter();
/* 544 */         this.lighterColors.put(c, lighter);
/*     */       }
/* 546 */       return lighter;
/*     */     }
/*     */ 
/*     */     private Color getDarkerColor(Color c)
/*     */     {
/* 563 */       Color darker = (Color)this.darkerColors.get(c);
/* 564 */       if (darker == null) {
/* 565 */         darker = c.darker().darker();
/* 566 */         this.darkerColors.put(c, darker);
/*     */       }
/* 568 */       return darker;
/*     */     }
/*     */ 
/*     */     public synchronized void paint(Graphics g)
/*     */     {
/* 583 */       if ((this.size == null) || (!this.size.equals(getSize()))) {
/* 584 */         this.size = getSize();
/* 585 */         this.squareSize.width = (this.size.width / SquareBoard.this.width);
/* 586 */         this.squareSize.height = (this.size.height / SquareBoard.this.height);
/* 587 */         if (this.squareSize.width <= this.squareSize.height)
/* 588 */           this.squareSize.height = this.squareSize.width;
/*     */         else {
/* 590 */           this.squareSize.width = this.squareSize.height;
/*     */         }
/* 592 */         this.insets.left = ((this.size.width - SquareBoard.this.width * this.squareSize.width) / 2);
/* 593 */         this.insets.right = this.insets.left;
/* 594 */         this.insets.top = 0;
/* 595 */         this.insets.bottom = (this.size.height - SquareBoard.this.height * this.squareSize.height);
/* 596 */         this.bufferImage = createImage(SquareBoard.this.width * this.squareSize.width, SquareBoard.this.height * this.squareSize.height);
/*     */       }
/*     */ 
/* 601 */       Rectangle rect = g.getClipBounds();
/* 602 */       Graphics bufferGraphics = this.bufferImage.getGraphics();
/* 603 */       bufferGraphics.setClip(rect.x - this.insets.left, rect.y - this.insets.top, rect.width, rect.height);
/*     */ 
/* 607 */       paintComponent(bufferGraphics);
/*     */ 
/* 610 */       g.drawImage(this.bufferImage, this.insets.left, this.insets.top, getBackground(), null);
/*     */     }
/*     */ 
/*     */     private void paintComponent(Graphics g)
/*     */     {
/* 627 */       g.setColor(getBackground());
/* 628 */       g.fillRect(0, 0, SquareBoard.this.width * this.squareSize.width, SquareBoard.this.height * this.squareSize.height);
/*     */ 
/* 634 */       for (int y = 0; y < SquareBoard.this.height; y++) {
/* 635 */         for (int x = 0; x < SquareBoard.this.width; x++) {
/* 636 */           if (SquareBoard.this.matrix[y][x] != null) {
/* 637 */             paintSquare(g, x, y);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 643 */       if (SquareBoard.this.message != null)
/* 644 */         paintMessage(g, SquareBoard.this.message);
/*     */     }
/*     */ 
/*     */     private void paintSquare(Graphics g, int x, int y)
/*     */     {
/* 657 */       Color color = SquareBoard.this.matrix[y][x];
/* 658 */       int xMin = x * this.squareSize.width;
/* 659 */       int yMin = y * this.squareSize.height;
/* 660 */       int xMax = xMin + this.squareSize.width - 1;
/* 661 */       int yMax = yMin + this.squareSize.height - 1;
/*     */ 
/* 665 */       this.bufferRect.x = xMin;
/* 666 */       this.bufferRect.y = yMin;
/* 667 */       this.bufferRect.width = this.squareSize.width;
/* 668 */       this.bufferRect.height = this.squareSize.height;
/* 669 */       if (!this.bufferRect.intersects(g.getClipBounds())) {
/* 670 */         return;
/*     */       }
/*     */ 
/* 674 */       g.setColor(color);
/* 675 */       g.fillRect(xMin, yMin, this.squareSize.width, this.squareSize.height);
/*     */ 
/* 678 */       g.setColor(getLighterColor(color));
/* 679 */       for (int i = 0; i < this.squareSize.width / 10; i++) {
/* 680 */         g.drawLine(xMin + i, yMin + i, xMax - i, yMin + i);
/* 681 */         g.drawLine(xMin + i, yMin + i, xMin + i, yMax - i);
/*     */       }
/*     */ 
/* 685 */       g.setColor(getDarkerColor(color));
/* 686 */       for (int i = 0; i < this.squareSize.width / 10; i++) {
/* 687 */         g.drawLine(xMax - i, yMin + i, xMax - i, yMax - i);
/* 688 */         g.drawLine(xMin + i, yMax - i, xMax - i, yMax - i);
/*     */       }
/*     */     }
/*     */ 
/*     */     private void paintMessage(Graphics g, String msg)
/*     */     {
/* 706 */       g.setFont(new Font("SansSerif", 1, this.squareSize.width + 4));
/* 707 */       int fontWidth = g.getFontMetrics().stringWidth(msg);
/*     */ 
/* 710 */       int x = (SquareBoard.this.width * this.squareSize.width - fontWidth) / 2;
/* 711 */       int y = SquareBoard.this.height * this.squareSize.height / 2;
/*     */ 
/* 714 */       int offset = this.squareSize.width / 10;
/* 715 */       g.setColor(Color.black);
/* 716 */       g.drawString(msg, x - offset, y - offset);
/* 717 */       g.drawString(msg, x - offset, y);
/* 718 */       g.drawString(msg, x - offset, y - offset);
/* 719 */       g.drawString(msg, x, y - offset);
/* 720 */       g.drawString(msg, x, y + offset);
/* 721 */       g.drawString(msg, x + offset, y - offset);
/* 722 */       g.drawString(msg, x + offset, y);
/* 723 */       g.drawString(msg, x + offset, y + offset);
/*     */ 
/* 726 */       g.setColor(this.messageColor);
/* 727 */       g.drawString(msg, x, y);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\admin\Desktop\tetris-1.2-bin.jar
 * Qualified Name:     net.percederberg.tetris.SquareBoard
 * JD-Core Version:    0.6.0
 */