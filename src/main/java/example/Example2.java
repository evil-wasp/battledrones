package example;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

/**
 * Created by dron on 06.11.15.
 */
public class Example2 extends JFrame {
    private static final long serialVersionUID = 5663760293144882635L;
    public static final double SCALE = 45.0;
    public static final double NANO_TO_BASE = 1.0e9;
    public static final int WIDTH = 1800;
    public static final int HEIGHT = 1000;

    public static class GameObject extends Body {
        protected Color color;

        public GameObject() {
            this.color = new Color((float) Math.random() * 0.5f + 0.5f, (float) Math.random() * 0.5f + 0.5f, (float) Math.random() * 0.5f + 0.5f);
        }

        public void render(Graphics2D g) {
            AffineTransform ot = g.getTransform();
            AffineTransform lt = new AffineTransform();
            lt.translate(this.transform.getTranslationX() * SCALE, this.transform.getTranslationY() * SCALE);
            lt.rotate(this.transform.getRotation());
            g.transform(lt);
            for (BodyFixture fixture : this.fixtures) {
                Convex convex = fixture.getShape();
                Graphics2DRenderer.render(g, convex, SCALE, color);
            }
            g.setTransform(ot);
        }
    }

    protected Canvas canvas;
    protected World world;
    protected boolean stopped;
    protected long last;

    protected GameObject ship;

    public Example2() {
        super("Graphics2D Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
                super.windowClosing(e);
            }
        });
        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(size);
        this.canvas.setMinimumSize(size);
        this.canvas.setMaximumSize(size);
        this.canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Canvas, mouse clicked");
                //System.out.println("ship getWorldCenter " + ship.getWorldCenter());
                //System.out.println("ship getLocalCenter " + ship.getLocalCenter());

                Vector2 mv = new Vector2(e.getX() / SCALE - 20, (HEIGHT - e.getY()) / SCALE - 10);
                System.out.println("mv: " + mv);
                Vector2 sv = ship.getWorldCenter();
                System.out.println("sv: " + sv);
                System.out.println("sv diff mv: " + sv.difference(mv));
                System.out.println("mv diff sv: " + mv.difference(sv));
                System.out.println("sv dist mv: " + sv.distance(mv));
                System.out.println("mv dist sv: " + mv.distance(sv));
//                if (e.getButton() == MouseEvent.BUTTON1) {
//                    //ship.applyForce(new Vector2(1, 1));
//                    ship.applyImpulse(new Vector2(1, 1));
//                } else {
//                    //ship.applyForce(new Vector2(-1, -1));
//                    ship.applyImpulse(new Vector2(-1, -1));
//                }

//                org.dyn4j.geometry.Polygon polyShape = Geometry.createUnitCirclePolygon(4, 1.0);
//                GameObject polygon = new GameObject();
//                polygon.addFixture(polyShape);
//                polygon.setMass();
//                polygon.translate(-2.5, 2.0);
//                polygon.setAngularVelocity(Math.toRadians(-2000.0));
//                world.addBody(polygon);
                super.mouseClicked(e);
            }
        });
        this.canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("mouseDragged x:" + e.getX() + " y:" + e.getY());
                Vector2 mv = new Vector2(e.getX() / SCALE - 20, (HEIGHT - e.getY()) / SCALE - 10);
                System.out.println("mv: " + mv);
                Vector2 sv = ship.getWorldCenter();
                System.out.println("sv: " + sv);
                System.out.println("sv diff mv: " + sv.difference(mv));
                System.out.println("mv diff sv: " + mv.difference(sv));
                ship.applyForce(new Vector2(e.getX() / SCALE - 20 - sv.getXComponent().getMagnitude(), (HEIGHT - e.getY()) / SCALE - 10 - sv.getYComponent().getMagnitude()));
                //ship.applyForce(mv.difference(sv).getXComponent(), sv.difference(mv).getYComponent());

                //ship.getWorldVector()
                //ship.applyForce()
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //System.out.println("mouseMoved x:" + e.getX() + " y:" + e.getY());
                ship.clearForce();
                super.mouseMoved(e);
            }
        });
        this.add(this.canvas);
        this.setResizable(false);
        this.pack();
        this.stopped = false;
        this.initializeWorld();
    }

    protected void initializeWorld() {
        this.world = new World();
        this.world.setGravity(World.ZERO_GRAVITY);

        Circle cirShape = new Circle(0.5);
        this.ship = new GameObject();
        this.ship.addFixture(cirShape);
        this.ship.setMass();
        this.ship.translate(2.0, 2.0);
        // test adding some force
        //this.ship.applyForce(new Vector2(-100.0, 0.0));
        // set some linear damping to simulate rolling friction
        this.ship.setLinearDamping(0.05);

//        this.ship = new GameObject();
//        this.ship.addFixture(triShape);
//        this.ship.setMass();
//        this.ship.translate(-1.0, 2.0);
        // test having a velocity
        this.ship.getLinearVelocity().set(-1.0, -1.0);
        this.world.addBody(this.ship);
    }

    public void start() {
        this.last = System.nanoTime();
        this.canvas.setIgnoreRepaint(true);
        this.canvas.createBufferStrategy(2);
        Thread thread = new Thread() {
            public void run() {
                while (!isStopped()) {
                    gameLoop();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * The method calling the necessary methods to update
     * the game, graphics, and poll for input.
     */
    protected void gameLoop() {
        Graphics2D g = (Graphics2D) this.canvas.getBufferStrategy().getDrawGraphics();
        AffineTransform yFlip = AffineTransform.getScaleInstance(1, -1);
        AffineTransform move = AffineTransform.getTranslateInstance(WIDTH / 2, -HEIGHT / 2);
        g.transform(yFlip);
        g.transform(move);
        this.render(g);
        g.dispose();
        BufferStrategy strategy = this.canvas.getBufferStrategy();
        if (!strategy.contentsLost()) {
            strategy.show();
        }
        Toolkit.getDefaultToolkit().sync();
        long time = System.nanoTime();
        // get the elapsed time from the last iteration
        long diff = time - this.last;
        // set the last time
        this.last = time;
        // convert from nanoseconds to seconds
        double elapsedTime = (double) diff / NANO_TO_BASE;
        // update the world with the elapsed time
        this.world.update(elapsedTime);
    }

    /**
     * Renders the example.
     *
     * @param g the graphics object to render to
     */
    protected void render(Graphics2D g) {
        // lets draw over everything with a white background
        g.setColor(Color.WHITE);
        g.fillRect(-WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT);

        // lets move the view up some
        g.translate(0.0, -1.0 * SCALE);

        // draw all the objects in the world
        for (int i = 0; i < this.world.getBodyCount(); i++) {
            // get the object
            GameObject go = (GameObject) this.world.getBody(i);
            // draw the object
            go.render(g);
        }
    }

    /**
     * Stops the example.
     */
    public synchronized void stop() {
        this.stopped = true;
    }

    /**
     * Returns true if the example is stopped.
     *
     * @return boolean true if stopped
     */
    public synchronized boolean isStopped() {
        return this.stopped;
    }

    /**
     * Entry point for the example application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // set the look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // create the example JFrame
        Example2 window = new Example2();

        // show it
        window.setVisible(true);

        // start it
        window.start();
    }
}
