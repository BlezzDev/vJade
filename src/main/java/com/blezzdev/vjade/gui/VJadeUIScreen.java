package com.blezzdev.vjade.gui;

import com.blezzdev.vjade.util.VJadeUIScreenProvider;
import com.blezzdev.vjade.util.VJadeVector2;

import java.util.List;

public class VJadeUIScreen {
    private VJadeVector2 position;
    private final int id;
    private boolean visible;
    private List<VJadeUIPanel> nodes;

    public VJadeUIScreen(boolean visible) {
        this.visible = visible;
        id = VJadeUIScreenProvider.register(this);
    }

    public void addNode(VJadeUIPanel element) { nodes.add(element); }

    public void removeNode(VJadeUIPanel element) { nodes.remove(element); }
    public void removeNode(int index) { nodes.remove(index); }

    public VJadeVector2 getPosition() { return position; }
    public boolean getVisible() { return visible; }
    public int getId() { return id; }
    public List<VJadeUIPanel> getNodes() { return nodes; }
    public VJadeUIPanel getNode(int index) { return nodes.get(index); }

    public void setPosition(VJadeVector2 position) { this.position = position; }
    public void setVisible(boolean visible) { this.visible = visible; }
}
