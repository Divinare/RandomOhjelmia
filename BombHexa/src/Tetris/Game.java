/*     */ package Tetris;
/*     */ 
/*     */ import java.awt.Button;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Label;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.*;
/*     */ 
/*     */ public class Game
/*     */ {
/*  49 */   private SquareBoard board = null;
/*     */ 
/*  55 */   private SquareBoard previewBoard = new SquareBoard(5, 5);
/*     */ 
/*  63 */   private Figure[] figures = { new Figure(1), new Figure(2), new Figure(3), new Figure(4), new Figure(5), new Figure(6), new Figure(7) };
/*     */ 
/*  77 */   public GamePanel component = null;
/*     */ 
/*  83 */   private GameThread thread = null;
/*     */ 
/*  89 */   private int level = 1;
/*     */ 
/*  95 */   private int score = 0;
/*     */ 
/* 100 */   private Figure figure = null;
/*     */ 
/* 105 */   private Figure nextFigure = null;
/*     */ 
/* 110 */   private int nextRotation = 0;
/*     */ 
/* 116 */   private boolean preview = true;
/*     */ 
/* 123 */   private boolean moveLock = false;
/*     */ 
/*     */   public Game()
/*     */   {
/* 130 */     this(10, 20);
/*     */   }
/*     */ 
/*     */   public Game(int width, int height)
/*     */   {
/* 141 */     this.board = new SquareBoard(width, height);
/* 142 */     this.board.setMessage("Press start");
/* 143 */     this.thread = new GameThread();
/*     */   }
/*     */ 
/*     */   public void quit()
/*     */   {
/* 153 */     this.thread = null;
/*     */   }
/*     */ 
/*     */   public Component getComponent()
/*     */   {
/* 162 */     if (this.component == null) {
/* 163 */       this.component = new GamePanel();
/*     */     }
/* 165 */     return this.component;
/*     */   }
/*     */ 
/*     */   private void handleStart()
/*     */   {
/* 176 */     this.level = 1;
/* 177 */     this.score = 0;
/* 178 */     this.figure = null;
/* 179 */     this.nextFigure = randomFigure();
/* 180 */     this.nextFigure.rotateRandom();
/* 181 */     this.nextRotation = this.nextFigure.getRotation();
/*     */ 
/* 184 */     this.board.setMessage(null);
/* 185 */     this.board.clear();
/* 186 */     this.previewBoard.clear();
/* 187 */     handleLevelModification();
/* 188 */     handleScoreModification();
/* 189 */     this.component.button.setLabel("Pause");
/*     */ 
/* 192 */     this.thread.reset();
/*     */   }
/*     */ 
/*     */   private void handleGameOver()
/*     */   {
/* 202 */     this.thread.setPaused(true);
/*     */ 
/* 205 */     if (this.figure != null) {
/* 206 */       this.figure.detach();
/*     */     }
/* 208 */     this.figure = null;
/* 209 */     if (this.nextFigure != null) {
/* 210 */       this.nextFigure.detach();
/*     */     }
/* 212 */     this.nextFigure = null;
/*     */ 
/* 215 */     this.board.setMessage("Game Over");
/* 216 */     this.component.button.setLabel("Start");
/*     */   }
/*     */ 
/*     */   private void handlePause()
/*     */   {
/* 224 */     this.thread.setPaused(true);
/* 225 */     this.board.setMessage("Paused");
/* 226 */     this.component.button.setLabel("Resume");
/*     */   }
/*     */ 
/*     */   private void handleResume()
/*     */   {
/* 234 */     this.board.setMessage(null);
/* 235 */     this.component.button.setLabel("Pause");
/* 236 */     this.thread.setPaused(false);
/*     */   }
/*     */ 
/*     */   private void handleLevelModification()
/*     */   {
/* 244 */     this.component.levelLabel.setText("Level: " + this.level);
/* 245 */     this.thread.adjustSpeed();
/*     */   }
/*     */ 
/*     */   private void handleScoreModification()
/*     */   {
/* 253 */     this.component.scoreLabel.setText("Score: " + this.score);
/*     */   }
/*     */ 
/*     */   private void handleFigureStart()
/*     */   {
/* 266 */     this.figure = this.nextFigure;
/* 267 */     this.moveLock = false;
/* 268 */     int rotation = this.nextRotation;
/* 269 */     this.nextFigure = randomFigure();
/* 270 */     this.nextFigure.rotateRandom();
/* 271 */     this.nextRotation = this.nextFigure.getRotation();
/*     */ 
/* 274 */     if (this.preview) {
/* 275 */       this.previewBoard.clear();
/* 276 */       this.nextFigure.attach(this.previewBoard, true);
/* 277 */       this.nextFigure.detach();
/*     */     }
/*     */ 
/* 281 */     this.figure.setRotation(rotation);
/* 282 */     if (!this.figure.attach(this.board, false)) {
/* 283 */       this.previewBoard.clear();
/* 284 */       this.figure.attach(this.previewBoard, true);
/* 285 */       this.figure.detach();
/* 286 */       handleGameOver();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void handleFigureLanded()
/*     */   {
/* 300 */     if (this.figure.isAllVisible()) {
/* 301 */       this.score += 10;
/* 302 */       handleScoreModification();
/*     */     } else {
/* 304 */       handleGameOver();
/* 305 */       return;
/*     */     }
/* 307 */     this.figure.detach();
/* 308 */     this.figure = null;
/*     */ 
/* 311 */     if (this.board.hasFullLines()) {
/* 312 */       this.board.removeFullLines();
/* 313 */       if ((this.level < 9) && (this.board.getRemovedLines() / 20 > this.level)) {
/* 314 */         this.level = (this.board.getRemovedLines() / 20);
/* 315 */         handleLevelModification();
/*     */       }
/*     */     } else {
/* 318 */       handleFigureStart();
/*     */     }
/*     */   }
/*     */ 
/*     */   private synchronized void handleTimer()
/*     */   {
/* 330 */     if (this.figure == null)
/* 331 */       handleFigureStart();
/* 332 */     else if (this.figure.hasLanded())
/* 333 */       handleFigureLanded();
/*     */     else
/* 335 */       this.figure.moveDown();
/*     */   }
/*     */ 
/*     */   private synchronized void handleButtonPressed()
/*     */   {
/* 347 */     if (this.nextFigure == null)
/* 348 */       handleStart();
/* 349 */     else if (this.thread.isPaused())
/* 350 */       handleResume();
/*     */     else
/* 352 */       handlePause();
/*     */   }
/*     */ 
   private synchronized void handleKeyEvent(KeyEvent e)
   {
     if (e.getKeyCode() == 80) {
       handleButtonPressed();
       return;
     }
/*     */ 
/* 374 */     if ((this.figure == null) || (this.moveLock) || (this.thread.isPaused())) {
/* 375 */       return;
/*     */     }
/*     */ 
/* 379 */     switch (e.getKeyCode())
/*     */     {
/*     */     case 37:
/* 382 */       this.figure.moveLeft();
/* 383 */       break;
/*     */     case 39:
/* 386 */       this.figure.moveRight();
/* 387 */       break;
/*     */     case 40:
/* 390 */       this.figure.moveAllWayDown();
/* 391 */       this.moveLock = true;
/* 392 */       break;
/*     */     case 32:
/*     */     case 38:
/* 396 */       if (e.isControlDown())
/* 397 */         this.figure.rotateRandom();
/* 398 */       else if (e.isShiftDown())
/* 399 */         this.figure.rotateClockwise();
/*     */       else {
/* 401 */         this.figure.rotateCounterClockwise();
/*     */       }
/* 403 */       break;
/*     */     case 83:
/* 406 */       if (this.level >= 9) break;
/* 407 */       this.level += 1;
/* 408 */       handleLevelModification(); break;
/*     */     case 78:
/* 413 */       this.preview = (!this.preview);
/* 414 */       if ((this.preview) && (this.figure != this.nextFigure)) {
/* 415 */         this.nextFigure.attach(this.previewBoard, true);
/* 416 */         this.nextFigure.detach();
/*     */       } else {
/* 418 */         this.previewBoard.clear();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
private Figure randomFigure()
{
return this.figures[(int)(java.lang.Math.random() * this.figures.length)];
}
/*     */ 
/*     */   public class GamePanel extends Container
/*     */   {
/* 543 */     private Dimension size = null;
/*     */ 
/* 548 */     private Label scoreLabel = new Label("Score: 0");
/*     */ 
/* 553 */     private Label levelLabel = new Label("Level: 1");
/*     */ 
/* 558 */     private Button button = new Button("Start");
/*     */ 
/*     */     public GamePanel()
/*     */     {
/* 566 */       initComponents();
/*     */     }
/*     */ 
/*     */     public void paint(Graphics g)
/*     */     {
/* 577 */       Rectangle rect = g.getClipBounds();
/*     */ 
/* 579 */       if ((this.size == null) || (!this.size.equals(getSize()))) {
/* 580 */         this.size = getSize();
/* 581 */         resizeComponents();
/*     */       }
/* 583 */       g.setColor(getBackground());
/* 584 */       g.fillRect(rect.x, rect.y, rect.width, rect.height);
/* 585 */       super.paint(g);
/*     */     }
/*     */ 
/*     */     private void initComponents()
/*     */     {
/* 596 */       setLayout(new GridBagLayout());
/* 597 */       setBackground(Configuration.getColor("background", "#d4d0c8"));
/*     */ 
/* 600 */       GridBagConstraints c = new GridBagConstraints();
/* 601 */       c.gridx = 0;
/* 602 */       c.gridy = 0;
/* 603 */       c.gridheight = 4;
/* 604 */       c.weightx = 1.0D;
/* 605 */       c.weighty = 1.0D;
/* 606 */       c.fill = 1;
/* 607 */       add(Game.this.board.getComponent(), c);
/*     */ 
/* 610 */       c = new GridBagConstraints();
/* 611 */       c.gridx = 1;
/* 612 */       c.gridy = 0;
/* 613 */       c.weightx = 0.2D;
/* 614 */       c.weighty = 0.18D;
/* 615 */       c.fill = 1;
/* 616 */       c.insets = new Insets(15, 15, 0, 15);
/* 617 */       add(Game.this.previewBoard.getComponent(), c);
/*     */ 
/* 620 */       this.scoreLabel.setForeground(Configuration.getColor("label", "#000000"));
/*     */ 
/* 622 */       this.scoreLabel.setAlignment(1);
/* 623 */       c = new GridBagConstraints();
/* 624 */       c.gridx = 1;
/* 625 */       c.gridy = 1;
/* 626 */       c.weightx = 0.3D;
/* 627 */       c.weighty = 0.05D;
/* 628 */       c.anchor = 10;
/* 629 */       c.fill = 1;
/* 630 */       c.insets = new Insets(0, 15, 0, 15);
/* 631 */       add(this.scoreLabel, c);
/*     */ 
/* 634 */       this.levelLabel.setForeground(Configuration.getColor("label", "#000000"));
/*     */ 
/* 636 */       this.levelLabel.setAlignment(1);
/* 637 */       c = new GridBagConstraints();
/* 638 */       c.gridx = 1;
/* 639 */       c.gridy = 2;
/* 640 */       c.weightx = 0.3D;
/* 641 */       c.weighty = 0.05D;
/* 642 */       c.anchor = 10;
/* 643 */       c.fill = 1;
/* 644 */       c.insets = new Insets(0, 15, 0, 15);
/* 645 */       add(this.levelLabel, c);
/*     */ 
/* 648 */       this.button.setBackground(Configuration.getColor("button", "#d4d0c8"));
/* 649 */       c = new GridBagConstraints();
/* 650 */       c.gridx = 1;
/* 651 */       c.gridy = 3;
/* 652 */       c.weightx = 0.3D;
/* 653 */       c.weighty = 1.0D;
/* 654 */       c.anchor = 11;
/* 655 */       c.fill = 2;
/* 656 */       c.insets = new Insets(15, 15, 15, 15);
/* 657 */       add(this.button, c);
/*     */ 
/* 660 */       enableEvents(8L);
/* 661 */    //   addKeyListener(new Game.1(this));
/*     */  
/* 666 */    //   this.button.addActionListener(new Game.2(this));
/*     */     }
/*     */ 
/*     */     private void resizeComponents()
/*     */     {
/* 679 */       Dimension size = this.scoreLabel.getSize();
/*     */ 
/* 684 */       size = Game.this.board.getComponent().getSize();
/* 685 */       size.width /= Game.this.board.getBoardWidth();
/* 686 */       size.height /= Game.this.board.getBoardHeight();
/*     */       int unitSize;
/* 687 */       if (size.width > size.height)
/* 688 */         unitSize = size.height;
/*     */       else {
/* 690 */         unitSize = size.width;
/*     */       }
/*     */ 
/* 694 */       Font font = new Font("SansSerif", 1, 3 + (int)(unitSize / 1.8D));
/*     */ 
/* 697 */       this.scoreLabel.setFont(font);
/* 698 */       this.levelLabel.setFont(font);
/* 699 */       font = new Font("SansSerif", 0, 2 + unitSize / 2);
/*     */ 
/* 702 */       this.button.setFont(font);
/*     */ 
/* 705 */       this.scoreLabel.invalidate();
/* 706 */       this.levelLabel.invalidate();
/* 707 */       this.button.invalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class GameThread extends Thread
/*     */   {
/* 447 */     private boolean paused = true;
/*     */ 
/* 453 */     private int sleepTime = 500;
/*     */ 
/*     */     public GameThread()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void reset()
/*     */     {
/* 466 */       adjustSpeed();
/* 467 */       setPaused(false);
/* 468 */       if (!isAlive())
/* 469 */         start();
/*     */     }
/*     */ 
/*     */     public boolean isPaused()
/*     */     {
/* 480 */       return this.paused;
/*     */     }
/*     */ 
/*     */     public void setPaused(boolean paused)
/*     */     {
/* 489 */       this.paused = paused;
/*     */     }
/*     */ 
/*     */     public void adjustSpeed()
/*     */     {
/* 499 */       this.sleepTime = (4500 / (Game.this.level + 5) - 250);
/* 500 */       if (this.sleepTime < 50)
/* 501 */         this.sleepTime = 50;
/*     */     }
/*     */ 
/*     */     public void run()
/*     */     {
/* 509 */       while (Game.this.thread == this)
/*     */       {
/* 511 */         Game.this.handleTimer();
/*     */         try
/*     */         {
/* 515 */           Thread.sleep(this.sleepTime);
/*     */         }
/*     */         catch (InterruptedException ignore)
/*     */         {
/*     */         }
/*     */ 
/* 521 */         while ((this.paused) && (Game.this.thread == this))
/*     */           try {
/* 523 */             Thread.sleep(1000L);
/*     */           }
/*     */           catch (InterruptedException ignore)
/*     */           {
/*     */           }
/*     */       }
}
 }
/*     */ class Game1 extends KeyAdapter
/*     */ {
/*     */   private final Game.GamePanel this$1;
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 663 */     Game.access$900(Game.GamePanel.access$800(this.this$1), e);
/*     */   }
/*     */ }

/*     */ class Game2
/*     */   implements ActionListener
/*     */ {
/*     */   private final Game.GamePanel this$1;
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 668 */     Game.access$1000(Game.GamePanel.access$800(this.this$1));
/* 669 */     Game.access$1100(Game.GamePanel.access$800(this.this$1)).requestFocus();
/*     */   }
/*     */ }



}
