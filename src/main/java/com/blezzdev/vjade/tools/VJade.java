package com.blezzdev.vjade.tools;

import com.blezzdev.vjade.core.window.Engine;
import com.blezzdev.vjade.util.types.log.LogStyle;

/// The VJade class manages the static values of a program.
///
/// The context keeps track of the window that is currently running.
///
/// The view manages the program's point of view.

public class VJade {
    private static Engine context;
    private static boolean debugMode = false;

    public static void setContext(Engine context) {
        VJade.context = context;
    }
    public static Engine getContext() {
        if (!existContext()) {
            throw new RuntimeException("The context has not yet been defined.");
        }
        return VJade.context;
    }
    public static boolean existContext() {
        return context != null;
    }

    public static void debugMode(boolean value) { debugMode = value; }
    public static boolean isDebugMode() { return debugMode; }

    public static final String DEFAULT_VERTEX_SHADER = """
                #version 330 core
                layout (location = 0) in vec3 pos;
                layout (location = 1) in vec2 texCoordinates;
                layout (location = 2) in vec4 color;
                
                uniform mat4 vProjection;
                
                out vec2 fTexCoordinates;
                out vec4 fModulate;
                
                void main()
                {
                    gl_Position = vProjection * vec4(pos, 1.0);
                    fTexCoordinates = texCoordinates;
                    fModulate = color;
                }
                """;

    public static final String DEFAULT_FRAGMENT_SHADER = """
                #version 330 core
                
                out vec4 FragColor;
                
                in vec2 fTexCoordinates;
                in vec4 fModulate;
                
                uniform sampler2D fDiffuseTex;
                uniform bool fUseTexture;
                
                void main() {
                    if (fUseTexture) {
                        FragColor = texture(fDiffuseTex, fTexCoordinates) * fModulate;
                    } else {
                        FragColor = fModulate;
                    }
                }
                """;

    public static final short MAX_TEXTURE_CAPACITY = 1024;
    public static final byte VERTEX_SIZE = 9;
    public static final byte VERTICES_PER_TEXTURE = 4;
    public static final byte INDICES_PER_TEXTURE = 6;
}