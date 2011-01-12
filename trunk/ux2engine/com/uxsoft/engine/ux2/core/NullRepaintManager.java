
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package com.uxsoft.engine.ux2.core;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JComponent;
import javax.swing.RepaintManager;

/**
 *
 * @author David
 */
public class NullRepaintManager extends RepaintManager {

    /**
     *   Installs the NullRepaintManager.
     */
    public static void installManager() {
        RepaintManager repaintManager = new NullRepaintManager();

        repaintManager.setDoubleBufferingEnabled(false);
        RepaintManager.setCurrentManager(repaintManager);
    }

    @Override
    public void addInvalidComponent(JComponent c) {

        // do nothing
    }

    @Override
    public void addDirtyRegion(JComponent c, int x, int y, int w, int h) {

        // do nothing
    }

    @Override
    public void markCompletelyDirty(JComponent c) {

        // do nothing
    }

    @Override
    public void paintDirtyRegions() {

        // do nothing
    }
}
