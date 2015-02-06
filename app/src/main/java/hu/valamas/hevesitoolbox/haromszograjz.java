package hu.valamas.hevesitoolbox;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.example.valamas.hevesitoolbox.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class haromszograjz extends Activity {

    public static float ax;
    public static float ay;
    public static float bx;
    public static float by;
    public static float cx;
    public static float cy;

    private GLSurfaceView glView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haromszograjz);

        Bundle extras = getIntent().getExtras();
        double[] message = extras.getDoubleArray("numbers");

        double a=message[0];
        double b=message[1];
        double c=message[2];
        ax= -1.0f;
        ay= -1.0f;
        bx= -1.0f;

        double szog = Math.acos((Math.pow(c, 2) + Math.pow(b, 2) - Math.pow(a, 2)) / (2 * c * b));

        float cx_d =(float) (b * Math.cos(szog));
        float cy_d =(float) (b * Math.sin(szog));

        if (cy_d<c)
        {
            cx= -1 + (       (cy_d / ((float) (c)))*2 ) ;
            cy= -1 + (       (cx_d / ((float) (c)))*2 ) ;
            by = 1.0f ;
        }   else if (cy_d>c)        {
            cx = 1.0f;
            cy =-1 + (       (cy_d /  cx_d)*2 ) ;
            by =-1 + (       ( ((float)c) / ( (cx_d)))*2 ) ;
        }


        glView = new GLSurfaceView(this);
        glView.setRenderer(new MyGLRenderer(this));
        this.setContentView(glView);
    }
    /*protected void onPause() {
        super.onPause();
        glView.onPause();    }
    protected void onResume() {
        super.onResume();
        glView.onResume();    } */

    public static class MyGLRenderer implements GLSurfaceView.Renderer {
        Context context;
        Triangle triangle;

        public MyGLRenderer(Context context) {
            this.context = context;
            triangle = new Triangle();
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // Set color's clear-value to black
            gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
            gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
            gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
            gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
            gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            if (height == 0) height = 1;   // To prevent divide by zero

            if (height>width)
            {
                height = width;
            }
            else
            {
                width = height;
            }

            float aspect = (float)width / height;
            gl.glViewport(0, 0, width, height);

            // Setup perspective projection, with aspect ratio matches viewport
            gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
            gl.glLoadIdentity();                 // Reset projection matrix
            // Use perspective projection
            //GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

            gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
            gl.glLoadIdentity();                 // Reset

            // You OpenGL|ES display re-sizing code here
            // ......
        }

        // Call back to draw the current frame.
        public void onDrawFrame(GL10 gl) {
            // Clear color and depth buffers using clear-value set earlier
            // You OpenGL|ES rendering code here
            // Clear color and depth buffers using clear-values set earlier
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

            gl.glLoadIdentity();                 // Reset model-view matrix ( NEW )
           // gl.glTranslatef(-1.5f, 0.0f, -6.0f); // Translate left and into the screen ( NEW )
            triangle.draw(gl);                   // Draw triangle ( NEW )
        }
    }
    public static class Triangle {
        private FloatBuffer vertexBuffer;  // Buffer for vertex-array
        private ByteBuffer indexBuffer;    // Buffer for index-array

        private float[] vertices = {  // Vertices of the triangle
                cy,  cx, 0.0f, // 0. top
                ay,  ax, 0.0f, // 1. left-bottom
                by,  bx, 0.0f  // 2. right-bottom

        };
        private byte[] indices = { 0, 1, 2 }; // Indices to above vertices (in CCW)

        // Constructor - Setup the data-array buffers
        public Triangle() {
            // Setup vertex-array buffer. Vertices in float. A float has 4 bytes.
            ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
            vbb.order(ByteOrder.nativeOrder()); // Use native byte order
            vertexBuffer = vbb.asFloatBuffer(); // Convert byte buffer to float
            vertexBuffer.put(vertices);         // Copy data into buffer
            vertexBuffer.position(0);           // Rewind

            // Setup index-array buffer. Indices in byte.
            indexBuffer = ByteBuffer.allocateDirect(indices.length);
            indexBuffer.put(indices);
            indexBuffer.position(0);
        }

        // Render this shape
        public void draw(GL10 gl) {
            // Enable vertex-array and define the buffers
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

            // Draw the primitives via index-array
            gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        }
    }
}

