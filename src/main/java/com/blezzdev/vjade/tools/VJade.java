package com.blezzdev.vjade.tools;

import com.blezzdev.vjade.core.window.Engine;
import com.blezzdev.vjade.tools.data.render.View;

/// The VJade class manages the static values of a program.
///
/// The context keeps track of the window that is currently running.
///
/// The view manages the program's point of view.

public class VJade {
    private static Engine context;
    private static View view;

    public static void setContext(Engine context) {
        VJade.context = context;
    }

    public static void setView(View view) {
        VJade.view = view;
    }

    public static Engine getContext() {
        if (!existContext()) {
            throw new RuntimeException("The context has not yet been defined.");
        }
        return VJade.context;
    }

    public static View getView() {
        return view;
    }

    public static boolean existContext() {
        return context != null;
    }

    public static final String DEFAULT_VERTEX_SHADER = """
                #version 330 core
                layout (location = 0) in vec3 vjPos;
                layout (location = 1) in vec2 vjTexCoord;
                
                uniform mat4 vjTransform;
                
                out vec2 TexCoord;
                
                void main()
                {
                    gl_Position = vjTransform * vec4(vjPos, 1.0);
                    TexCoord = vjTexCoord;
                }
                """;

    public static final String DEFAULT_FRAGMENT_SHADER = """
                #version 330 core
                
                out vec4 vjFragColor;
                in vec2 TexCoord;
                
                uniform sampler2D vjDiffuseTex;
                uniform vec4 vjModulate;
                uniform bool vjUseTexture;
                
                void main() {
                    if (vjUseTexture) {
                        vjFragColor = texture(vjDiffuseTex, TexCoord) * vjModulate;
                    } else {
                        vjFragColor = vjModulate;
                    }
                }
                """;

    public static final short MAX_TEXTURE_CAPACITY = 1024;
    public static final byte VERTEX_SIZE = 5;
}