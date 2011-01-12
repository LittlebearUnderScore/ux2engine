/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.paths;

import java.util.List;
import com.uxsoft.engine.ux2.math.Vector2D;

/**
 * A path defines a list of points that a game object will follow.
 * @author David
 */
public interface Path {
	Vector2D [] getPointsAsArray();

	List<Vector2D> getPointsAsList();
}
