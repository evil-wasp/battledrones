/*
 * Copyright (c) 2010-2015 William Bittle  http://www.dyn4j.org/
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice, this list of conditions 
 *     and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *     and the following disclaimer in the documentation and/or other materials provided with the 
 *     distribution.
 *   * Neither the name of dyn4j nor the names of its contributors may be used to endorse or 
 *     promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package example;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;

/**
 * Class used to show a simple example of using the dyn4j project using
 * Java2D for rendering.
 * <p>
 * This class can be used as a starting point for projects.
 * @author William Bittle
 * @version 3.2.0
 * @since 3.0.0
 */
public class Example3 extends JFrame {
    /** The serial version id */
    private static final long serialVersionUID = 5663760293144882635L;

    /** The scale 45 pixels per meter */
    public static final double SCALE = 45.0;

    /** The conversion factor from nano to base */
    public static final double NANO_TO_BASE = 1.0e9;
    public static final int WIDTH = 1800;
    public static final int HEIGHT = 1000;

    /**
     * Custom Body class to add drawing functionality.
     * @author William Bittle
     * @version 3.0.2
     * @since 3.0.0
     */
    public static class GameObject extends Body {
        /** The color of the object */
        protected Color color;
        double orientation;

        /**
         * Default constructor.
         */
        public GameObject(){
            orientation = 0;
            // randomly generate the color
            this.color = new Color(
                    (float)Math.random() * 0.5f + 0.5f,
                    (float)Math.random() * 0.5f + 0.5f,
                    (float)Math.random() * 0.5f + 0.5f);


        }

        public double getOrientation() {
            return orientation;
        }

        public void setOrientation(double orientation){
            this.orientation = orientation;
            this.rotate(this.orientation - orientation);
        }

        /**
         * Draws the body.
         * <p>
         * Only coded for polygons and circles.
         * @param g the graphics object to render to
         */
        public void render(Graphics2D g) {
            // save the original transform
            AffineTransform ot = g.getTransform();

            // transform the coordinate system from world coordinates to local coordinates
            AffineTransform lt = new AffineTransform();
            lt.translate(this.transform.getTranslationX() * SCALE, this.transform.getTranslationY() * SCALE);
            lt.rotate(this.transform.getRotation());

            // apply the transform
            g.transform(lt);

            // loop over all the body fixtures for this body
            for (BodyFixture fixture : this.fixtures) {
                // get the shape on the fixture
                Convex convex = fixture.getShape();
                Graphics2DRenderer.render(g, convex, SCALE, color);
            }

            // set the original transform
            g.setTransform(ot);
        }
    }
    //The moving ship
    GameObject ship = new GameObject();

    private final Set<Integer> pressed = new HashSet<>();

    /** The canvas to draw to */
    protected Canvas canvas;

    /** The dynamics engine */
    protected World world;

    /** Wether the example is stopped or not */
    protected boolean stopped;

    /** The time stamp for the last iteration */
    protected long last;

    /**
     * Default constructor for the window
     */
    public Example3() {
        super("Graphics2D Example");
        // setup the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add a window listener
        this.addWindowListener(new WindowAdapter() {
            /* (non-Javadoc)
             * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
             */
            @Override
            public void windowClosing(WindowEvent e) {
                // before we stop the JVM stop the example
                stop();
                super.windowClosing(e);
            }
        });

        // create the size of the window
        Dimension size = new Dimension(WIDTH, HEIGHT);

        // create a canvas to paint to
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(size);
        this.canvas.setMinimumSize(size);
        this.canvas.setMaximumSize(size);

        //add a keaboard listner



        this.canvas.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    GameObject bullet = new GameObject();
                    bullet.setBullet(true);
                    bullet.addFixture(Geometry.createCircle(0.05));
                    bullet.setMass(MassType.NORMAL);
                    bullet.setLinearDamping(0.2);
                    bullet.setAngularDamping(2);
                    bullet.setGravityScale(0);
                    bullet.setOrientation(0);

                    Vector2 v = new Vector2(
                            Math.cos(ship.getTransform().getRotation() + Math.PI / 2),
                            Math.sin(ship.getTransform().getRotation() + Math.PI / 2));

                    bullet.setLinearVelocity(v.copy().multiply(20));
                    world.addBody(bullet);
                    bullet.translate(ship.getWorldCenter().add(v));

                }
                else
                pressed.add(e.getKeyCode());

            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressed.remove(e.getKeyCode());
            }
        });

        // add the canvas to the JFrame
        this.add(this.canvas);

        // make the JFrame not resizable
        // (this way I dont have to worry about resize events)
        this.setResizable(false);

        // size everything
        this.pack();

        // make sure we are not stopped
        this.stopped = false;

        // setup the world
        this.initializeWorld();
    }

    /**
     * Creates game objects and adds them to the world.
     * <p>
     * Basically the same shapes from the Shapes test in
     * the TestBed.
     */
    protected void initializeWorld() {
        // create the world
        this.world = new World();

        GameObject enemy = new GameObject();
        enemy.addFixture(Geometry.createCircle(3.0));
        enemy.setMass(MassType.NORMAL);
        enemy.setLinearDamping(2);
        enemy.setAngularDamping(2);
        enemy.setGravityScale(0);
        enemy.setOrientation(0);
        enemy.translate(-3, -4);
        this.world.addBody(enemy);

        ship.addFixture(Geometry.createIsoscelesTriangle(0.5, 1.0));
        ship.setMass(MassType.NORMAL);
        ship.setLinearDamping(2);
        ship.setAngularDamping(2);
        ship.setGravityScale(0);
        ship.setOrientation(0);
        this.world.addBody(ship);
    }

    /**
     * Start active rendering the example.
     * <p>
     * This should be called after the JFrame has been shown.
     */
    public void start() {
        // initialize the last update time
        this.last = System.nanoTime();
        // don't allow AWT to paint the canvas since we are
        this.canvas.setIgnoreRepaint(true);
        // enable double buffering (the JFrame has to be
        // visible before this can be done)
        this.canvas.createBufferStrategy(2);
        // run a separate thread to do active rendering
        // because we don't want to do it on the EDT
        Thread thread = new Thread() {
            public void run() {
                // perform an infinite loop stopped
                // render as fast as possible
                while (!isStopped()) {
                    gameLoop();
                    // you could add a Thread.yield(); or
                    // Thread.sleep(long) here to give the
                    // CPU some breathing room
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        // set the game loop thread to a daemon thread so that
        // it cannot stop the JVM from exiting
        thread.setDaemon(true);
        // start the game loop
        thread.start();
    }

    /**
     * The method calling the necessary methods to update
     * the game, graphics, and poll for input.
     */
    protected void gameLoop() {


for(int i: pressed) {
    if (i == KeyEvent.VK_UP) {
       // System.out.println("space key was pressed");
        ship.applyForce(new Vector2(Math.cos(ship.getTransform().getRotation() + Math.PI / 2), Math.sin(ship.getTransform().getRotation() + Math.PI / 2)));
    }
    if (i == KeyEvent.VK_LEFT) {
        //System.out.println("left key was pressed");
        ship.applyTorque(0.05);
    }
    if (i == KeyEvent.VK_RIGHT) {
        //System.out.println("left key was pressed");
        ship.applyTorque(-0.05);
    }

}

        //calculate orientation

        ship.orientation -= ship.getChangeInOrientation();

        // get the graphics object to render to
        Graphics2D g = (Graphics2D)this.canvas.getBufferStrategy().getDrawGraphics();

        // before we render everything im going to flip the y axis and move the
        // origin to the center (instead of it being in the top left corner)
        AffineTransform yFlip = AffineTransform.getScaleInstance(1, -1);
        AffineTransform move = AffineTransform.getTranslateInstance(WIDTH/2, -HEIGHT/2);
        g.transform(yFlip);
        g.transform(move);

        // now (0, 0) is in the center of the screen with the positive x axis
        // pointing right and the positive y axis pointing up

        // render anything about the Example (will render the World objects)
        this.render(g);

        // dispose of the graphics object
        g.dispose();

        // blit/flip the buffer
        BufferStrategy strategy = this.canvas.getBufferStrategy();
        if (!strategy.contentsLost()) {
            strategy.show();
        }

        // Sync the display on some systems.
        // (on Linux, this fixes event queue problems)
        Toolkit.getDefaultToolkit().sync();

        // update the World

        // get the current time
        long time = System.nanoTime();
        // get the elapsed time from the last iteration
        long diff = time - this.last;
        // set the last time
        this.last = time;
        // convert from nanoseconds to seconds
        double elapsedTime = diff / NANO_TO_BASE;
        // update the world with the elapsed time
        try {
            this.world.update(elapsedTime);
        }
        catch(Throwable e){

        }
    }

    /**
     * Renders the example.
     * @param g the graphics object to render to
     */
    protected void render(Graphics2D g) {
        // lets draw over everything with a white background
        g.setColor(Color.WHITE);
        g.fillRect(-WIDTH/2, -HEIGHT/2, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.drawString("Ship orientation" + ship.orientation, 20, 20);
        g.drawString("Transform orientation" + ship.getTransform().getRotation(), 20, 40);

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
     * @return boolean true if stopped
     */
    public synchronized boolean isStopped() {
        return this.stopped;
    }

    /**
     * Entry point for the example application.
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
        Example3 window = new Example3();

        // show it
        window.setVisible(true);

        // start it
        window.start();
    }
}
