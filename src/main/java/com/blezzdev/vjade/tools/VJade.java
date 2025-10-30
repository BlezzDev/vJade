package com.blezzdev.vjade.tools;

import com.blezzdev.vjade.core.window.Engine;

/// The VJade class manages the static values of a program.
///
/// The context keeps track of the window that is currently running.
///
/// The view manages the program's point of view.

public class VJade {
    private static Engine context;

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

    public static final String DEFAULT_VERTEX_SHADER = """
                #version 330 core
                layout (location = 0) in vec3 vjPos;
                layout (location = 1) in vec2 vjTexCoord;
                layout (location = 2) in vec4 vjColor;
                
                uniform mat4 vjTransform;
                
                out vec2 TexCoord;
                out vec4 Modulate;
                
                void main()
                {
                    gl_Position = vjTransform * vec4(vjPos, 1.0);
                    TexCoord = vjTexCoord;
                    Modulate = vjColor;
                }
                """;

    public static final String DEFAULT_FRAGMENT_SHADER = """
                #version 330 core
                
                out vec4 FragColor;
                in vec2 TexCoord;
                in vec4 Modulate;
                
                uniform sampler2D vjDiffuseTex;
                uniform bool vjUseTexture;
                
                void main() {
                    if (vjUseTexture) {
                        FragColor = texture(vjDiffuseTex, TexCoord) * Modulate;
                    } else {
                        FragColor = Modulate;
                    }
                }
                """;

    public static final short MAX_TEXTURE_CAPACITY = 1024;
    public static final byte VERTEX_SIZE = 9;
    public static final byte VERTICES_PER_TEXTURE = 4;
    public static final byte INDICES_PER_TEXTURE = 6;
}