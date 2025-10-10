package com.blezzdev.vjade.objects.build;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

public class Shader {
    private String vertexShader;
    private String fragmentShader;
    private String geometryShader;
    private int programId;
    private int vertexShaderId;
    private int fragmentShaderId;
    private int geometryShaderId;
    private boolean loaded = false;

    public enum Uniform {
        INT_1,
        FLOAT_1,
        FLOAT_2,
        FLOAT_3,
        FLOAT_4,
        MATRIX_4,
        BOOL
    }

    public Shader() {}

    public Shader(String vertexShaderPath, String fragmentShaderPath) {
        this.vertexShader = loadShaderFromFile(vertexShaderPath);
        this.fragmentShader = loadShaderFromFile(fragmentShaderPath);
    }

    public Shader(String vertexShaderPath, String fragmentShaderPath, String geometryShaderPath) {
        this.vertexShader = loadShaderFromFile(vertexShaderPath);
        this.fragmentShader = loadShaderFromFile(fragmentShaderPath);
        this.geometryShader = loadShaderFromFile(geometryShaderPath);
    }

    public void load() {
        if (vertexShader == null || fragmentShader == null) {
            throw new IllegalStateException("Vertex and Fragment shaders must be set before loading");
        }

        programId = GL20.glCreateProgram();

        vertexShaderId = compileShader(vertexShader, GL20.GL_VERTEX_SHADER);
        GL20.glAttachShader(programId, vertexShaderId);

        fragmentShaderId = compileShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);
        GL20.glAttachShader(programId, fragmentShaderId);

        if (geometryShader != null) {
            geometryShaderId = compileShader(geometryShader, GL32.GL_GEOMETRY_SHADER);
            GL20.glAttachShader(programId, geometryShaderId);
        }

        GL20.glLinkProgram(programId);

        if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == 0) {
            throw new RuntimeException("Error linking Shader code: " + GL20.glGetProgramInfoLog(programId, 1024));
        }

        GL20.glValidateProgram(programId);
        if (GL20.glGetProgrami(programId, GL20.GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + GL20.glGetProgramInfoLog(programId, 1024));
        }

        loaded = true;
    }

    private int compileShader(String shaderCode, int shaderType) {
        int ShaderId = GL20.glCreateShader(shaderType);
        if (ShaderId == 0) {
            throw new RuntimeException("Error creating shader. Type: " + shaderType);
        }

        GL20.glShaderSource(ShaderId, shaderCode);
        GL20.glCompileShader(ShaderId);

        if (GL20.glGetShaderi(ShaderId, GL20.GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException("Error compiling Shader code: " + GL20.glGetShaderInfoLog(ShaderId, 1024));
        }

        return ShaderId;
    }

    private String loadShaderFromFile(String filePath) {
        if (filePath == null) return null;

        StringBuilder shaderSource = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read shader file: " + filePath, e);
        }
        return shaderSource.toString();
    }

    public void bind() {
        if (!loaded) {
            throw new IllegalStateException("Shader must be loaded before binding");
        }
        GL20.glUseProgram(programId);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if (programId != 0) {
            if (vertexShaderId != 0) {
                GL20.glDetachShader(programId, vertexShaderId);
            }
            if (fragmentShaderId != 0) {
                GL20.glDetachShader(programId, fragmentShaderId);
            }
            if (geometryShaderId != 0) {
                GL20.glDetachShader(programId, geometryShaderId);
            }

            if (vertexShaderId != 0) {
                GL20.glDeleteShader(vertexShaderId);
            }
            if (fragmentShaderId != 0) {
                GL20.glDeleteShader(fragmentShaderId);
            }
            if (geometryShaderId != 0) {
                GL20.glDeleteShader(geometryShaderId);
            }

            GL20.glDeleteProgram(programId);
        }
        loaded = false;
    }

    public void setUniform(Uniform type, String name, Object... value) {
        int location = GL20.glGetUniformLocation(programId, name);
        if (location != -1) {
            switch (type) {
                case BOOL -> GL20.glUniform1i(location, (boolean) value[0] ? 1 : 0);
                case INT_1 -> GL20.glUniform1i(location, (int) value[0]);
                case FLOAT_1 -> GL20.glUniform1f(location, (float) value[0]);
                case FLOAT_2 -> GL20.glUniform2f(location, (float) value[0], (float) value[1]);
                case FLOAT_3 -> GL20.glUniform3f(location, (float) value[0], (float) value[1], (float) value[2]);
                case FLOAT_4 -> GL20.glUniform4f(location, (float) value[0], (float) value[1], (float) value[2], (float) value[3]);
                case MATRIX_4 -> GL20.glUniformMatrix4fv(location, false, (float[]) value[0]);
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }
        }
    }

    public void setVertexShader(String vertexShader) {
        this.vertexShader = vertexShader;
    }

    public void setFragmentShader(String fragmentShader) {
        this.fragmentShader = fragmentShader;
    }

    public void setGeometryShader(String geometryShader) {
        this.geometryShader = geometryShader;
    }

    public void setVertexShaderFromFile(String filePath) {
        this.vertexShader = loadShaderFromFile(filePath);
    }

    public void setFragmentShaderFromFile(String filePath) {
        this.fragmentShader = loadShaderFromFile(filePath);
    }

    public void setGeometryShaderFromFile(String filePath) {
        this.geometryShader = loadShaderFromFile(filePath);
    }

    public String getVertexShader() {
        return vertexShader;
    }

    public String getFragmentShader() {
        return fragmentShader;
    }

    public String getGeometryShader() {
        return geometryShader;
    }

    public int getprogramId() {
        return programId;
    }

    public boolean isLoaded() {
        return loaded;
    }
}
