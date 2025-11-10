package com.blezzdev.vjade.core.window;

import org.lwjgl.opengl.GLDebugMessageCallback;

import static org.lwjgl.opengl.GL11C.glEnable;
import static org.lwjgl.opengl.GL11C.glGetInteger;
import static org.lwjgl.opengl.GL30C.GL_CONTEXT_FLAGS;
import static org.lwjgl.opengl.GL43C.*;
import static org.lwjgl.opengl.GL43C.glDebugMessageCallback;

class OpenGLDebugMode {
    OpenGLDebugMode() {
        if (glGetInteger(GL_CONTEXT_FLAGS) == GL_CONTEXT_FLAG_DEBUG_BIT) {
            System.out.println(" > OpenGL Debug context enabled.");
        }

        glEnable(GL_DEBUG_OUTPUT);
        glEnable(GL_DEBUG_OUTPUT_SYNCHRONOUS);

        GLDebugMessageCallback callback = GLDebugMessageCallback.create(
                (source, type, id, severity, length, message, userParam) -> {
                    if (getType(type).equals("Error")) {
                        System.err.println(String.format("""
                             > OpenGL debug inform:
                              
                               | > Source: %s
                               | > Type: %s
                               | > Severity: %s
                               |
                               | > %s
                            """, getSource(source), getType(type), getSeverity(severity),
                                GLDebugMessageCallback.getMessage(length, message)
                        ));
                    } else {
                        System.out.println(String.format("""
                             > OpenGL debug inform:
                              
                               | > Source: %s
                               | > Type: %s
                               | > Severity: %s
                               |
                               | > %s
                            """, getSource(source), getType(type), getSeverity(severity),
                                GLDebugMessageCallback.getMessage(length, message)
                        ));
                    }
                }
        );

        glDebugMessageCallback(callback, 0);
    }

    private static String getSource(int source) {
        return switch (source) {
            case GL_DEBUG_SOURCE_API -> "API";
            case GL_DEBUG_SOURCE_WINDOW_SYSTEM -> "Window System";
            case GL_DEBUG_SOURCE_SHADER_COMPILER -> "Shader Compiler";
            case GL_DEBUG_SOURCE_THIRD_PARTY -> "Third Party";
            case GL_DEBUG_SOURCE_APPLICATION -> "Application";
            case GL_DEBUG_SOURCE_OTHER -> "Other";
            default -> "Unknown";
        };
    }

    private static String getType(int type) {
        return switch (type) {
            case GL_DEBUG_TYPE_ERROR -> "Error";
            case GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR -> "Deprecated Behavior";
            case GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR -> "Undefined Behavior";
            case GL_DEBUG_TYPE_PORTABILITY -> "Portability";
            case GL_DEBUG_TYPE_PERFORMANCE -> "Performance";
            case GL_DEBUG_TYPE_MARKER -> "Marker";
            default -> "Other";
        };
    }

    private static String getSeverity(int severity) {
        return switch (severity) {
            case GL_DEBUG_SEVERITY_HIGH -> "HIGH";
            case GL_DEBUG_SEVERITY_MEDIUM -> "MEDIUM";
            case GL_DEBUG_SEVERITY_LOW -> "LOW";
            case GL_DEBUG_SEVERITY_NOTIFICATION -> "NOTIFICATION";
            default -> "Unknown";
        };
    }
}
