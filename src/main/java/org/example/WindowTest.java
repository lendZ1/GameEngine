package main.java.org.example;// Simple LWJGL GLFW window test
// Requires LWJGL (lwjgl, lwjgl-glfw, lwjgl-opengl) on the classpath and native libraries available.

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class WindowTest {
    public static void main(String[] args) {
        // Set up an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            System.err.println("Failed to initialize GLFW");
            System.exit(-1);
        }

        // Configure window hints if needed
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        long window = GLFW.glfwCreateWindow(640, 480, "LWJGL Window Test", MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL) {
            System.err.println("Failed to create GLFW window");
            GLFW.glfwTerminate();
            System.exit(-1);
        }

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window);
        // Enable v-sync
        GLFW.glfwSwapInterval(1);
        // Make the window visible
        GLFW.glfwShowWindow(window);

        // Create OpenGL capabilities after making context current
        GL.createCapabilities();

        // Set up 2D projection
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glOrtho(0, 640, 480, 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        // Main loop
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Clear the screen to a color
            GL11.glClearColor(0.1f, 0.2f, 0.3f, 0.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            // Draw a rectangle
            GL11.glColor3f(1.0f, 0.0f, 0.0f); // Red color
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(150, 100);  // Top-left
            GL11.glVertex2f(490, 100);  // Top-right
            GL11.glVertex2f(490, 380);  // Bottom-right
            GL11.glVertex2f(150, 380);  // Bottom-left
            GL11.glEnd();

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        // Cleanup
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }
}
