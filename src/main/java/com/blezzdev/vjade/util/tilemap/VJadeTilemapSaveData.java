package com.blezzdev.vjade.util.tilemap;

import java.io.File;

public class VJadeTilemapSaveData {
    public static int VJMappedIdentifier = 0;

    public VJadeTilemapSaveData(String dir) { instanceFile(dir); }

    private void instanceFile(String dir) {
        VJMappedIdentifier++;
        String path = String.format(dir + "/0x%08X.vjmapped", VJMappedIdentifier);
        File f = new File(path);
        if (f.exists()) {
            instanceFile(dir);
            return;
        }
    }
}
