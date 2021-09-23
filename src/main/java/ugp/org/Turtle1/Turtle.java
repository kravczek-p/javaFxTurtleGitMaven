package ugp.org.Turtle1;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Turtle extends Pen {
    protected Image turtle = new Image("ugp/org/Turtle1/turtle.png");
    protected int angle = 0;
    protected int speed = 8;
    protected Rectangle sprite;
    protected List<Runnable> actions = new CopyOnWriteArrayList();
    private double lastX;
    private double lastY;
    protected AnimationTimer loop = new AnimationTimer() {
        public void handle(long now) {
            if (!Turtle.this.isSleeping()) {
                Turtle.this.PaintLine(Turtle.this.lastX, Turtle.this.lastY);
                Turtle.this.sprite.toFront();
                Turtle.this.lastX = Turtle.this.X;
                Turtle.this.lastY = Turtle.this.Y;
            }

            Turtle.this.sprite.setX(Turtle.this.X - 10.0D);
            Turtle.this.sprite.setY(Turtle.this.Y - 10.0D);
            Turtle.this.sprite.setRotate((double)Turtle.this.angle);
            Turtle.this.sprite.setEffect(new DropShadow(5.0D, Turtle.this.color));
        }
    };

    public Turtle(Group g, double x, double y) {
        super(x, y, (Group)null);
        this.lastX = x;
        this.lastY = y;
        (this.group = g).getChildren().add(this.sprite = new Rectangle(20.0D, 20.0D));
        this.sprite.setFill(new ImagePattern(this.turtle));
        this.loop.start();
        this.penDown();
    }

    public void penUp() {
        this.Action(() -> {
            super.penUp();
        });
    }

    public void penDown() {
        this.Action(() -> {
            super.penDown();
        });
    }

    public void setPenColor(Color newColor) {
        this.Action(() -> {
            super.setPenColor(newColor);
        });
    }

    public void setPenEffect(Effect effect) {
        this.Action(() -> {
            super.setPenEffect(effect);
        });
    }

    public void setPenSize(double newSize) {
        this.Action(() -> {
            super.setPenSize(newSize);
        });
    }

    public void setX(double newX) {
        this.Action(() -> {
            super.setX(newX);
        });
    }

    public void setY(double newY) {
        this.Action(() -> {
            super.setY(newY);
        });
    }

    public void setSpeed(int newSpeed) {
        this.Action(() -> {
            this.speed = newSpeed > 10 ? 10 : newSpeed;
        });
    }

    public void setAngle(int newAngleInDegrees) {
        this.Action(() -> {
            this.angle = newAngleInDegrees;
        });
    }

    public void TeleportTo(double newX, double newY) {
        this.Action(() -> {
            super.setX(newX);
            super.setY(newY);
        });
    }

    public void AddAngle(int degrees) {
        if (degrees > 0) {
            this.Right((double)degrees);
        } else {
            this.Left((double)Math.abs(degrees));
        }

    }

    public void Right(double degrees) {
        this.Action(() -> {
            for(int y = 0; (double)y < degrees; ++y) {
                ++this.angle;

                try {
                    Thread.sleep((long)(11 - this.speed));
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }
            }

        });
    }

    public void Left(double degrees) {
        this.Action(() -> {
            for(int y = 0; (double)y < degrees; ++y) {
                --this.angle;

                try {
                    Thread.sleep((long)(11 - this.speed));
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }
            }

        });
    }

    public void Forward(int distance) {
        this.Action(() -> {
            for(int y = 0; y <= distance; ++y) {
                this.X += Math.sin(Math.toRadians((double)this.angle));
                this.Y -= Math.cos(Math.toRadians((double)this.angle));

                try {
                    Thread.sleep((long)(11 - this.speed));
                } catch (InterruptedException var4) {
                    var4.printStackTrace();
                }
            }

        });
    }

    public void Back(int distance) {
        this.Action(() -> {
            for(int y = 0; y <= distance; ++y) {
                this.X -= Math.sin(Math.toRadians((double)this.angle));
                this.Y += Math.cos(Math.toRadians((double)this.angle));

                try {
                    Thread.sleep((long)(11 - this.speed));
                } catch (InterruptedException var4) {
                    var4.printStackTrace();
                }
            }

        });
    }

    public void Sleep(int millis) {
        this.Action(() -> {
            try {
                Thread.sleep((long)millis);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

        });
    }

    public void Action(Runnable action) {
        this.actions.add(action);
    }

    public void DoActions() {
        (new Thread(() -> {
            Iterator var2 = this.getActions().iterator();

            while(var2.hasNext()) {
                Runnable r = (Runnable)var2.next();

                try {
                    Thread.sleep(40L);
                    r.run();
                    this.actions.remove(r);
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
            }

        })).start();
    }

    public List<Runnable> getActions() {
        return this.actions;
    }

    public boolean isSleeping() {
        return this.lastX == this.X && this.lastY == this.Y;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getAngle() {
        return this.angle;
    }
}

