/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.scene.ext;

import com.uxsoft.engine.ux2.core.IGameObject;
import com.uxsoft.engine.ux2.graphics.RenderSpace;
import com.uxsoft.engine.ux2.scene.ObjectIterator;
import com.uxsoft.engine.ux2.scene.ObjectList;
import com.uxsoft.engine.ux2.scene.SceneRenderer;

/**
 * A scene subclass which implements many of the base functions.
 * @author David
 */
public abstract class AbstractScene
	extends BaseScene {

	/**
	 * Game Object List
	 */
	protected ObjectList objList;

	public AbstractScene() {
		initializeScene();
	}

	protected void initializeScene() {
		objList = new LinkedObjectList();
	}

	public ObjectList getRenderList() {
		return objList;
	}

	public void addObject(IGameObject obj) {
		objList.add(obj);
		/*ObjectIterator itr = objList.iterator();
		IGameObject curr = itr.next();
		System.err.println("TRACE");
		while (curr != null) {
			System.err.println("AddObject: " + obj.getDepth() + " [ " + obj.getClass().getName() + "]");
			curr = itr.next();
		}*/
	}

	public void removeObject(IGameObject obj) {
		objList.remove(obj);
	}

	public int numObjects() {
		return objList.size();
	}

	/**
	 * Installs the default renderer, if this scene doesn't already have one.
	 */
	public void installDefaultRenderer() {
		if (getRenderer() == null) {
			setRenderer(new Renderer());
		}
	}

	public void renderScene(RenderSpace rs) {
		ObjectIterator itr = objList.iterator();
		while (itr.hasNext()) {
			IGameObject curr = itr.next();
			curr.doRender(rs);
		}
	}

	public void updateObjects(long time) {
		ObjectIterator itr = objList.iterator();
		while (itr.hasNext()) {
			IGameObject curr = itr.next();
			curr.doUpdate(time);
		}
	}

	public void doUpdate(long elapsedMillis) {
		updateObjects(elapsedMillis);
	}

	public class Renderer
			implements SceneRenderer {
		public void doRender(RenderSpace rs) {
			AbstractScene.this.renderScene(rs);
		}
	}
}
