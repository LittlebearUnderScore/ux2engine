/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.ext;

import com.uxsoft.engine.ux2.core.IGameObject;
import com.uxsoft.engine.ux2.math.Size2D;

/**
 * A view port is a rectangle which displays a portion of a whole game.
 * The view port can "follow" an object, which is typically the main character.
 * @author David
 */
public class ViewPort {
	private int xPos;
	private int yPos;
	private IGameObject followed;
	private Size2D vpSize;
	private Size2D gameSize;

	public ViewPort(IGameObject followed, Size2D vpSize, Size2D gameSize) {
		this.followed = followed;
		this.vpSize = vpSize;
		this.gameSize = gameSize;
	}

	public IGameObject getFollowed() {
		return followed;
	}

	public void setFollowed(IGameObject followed) {
		this.followed = followed;
	}

	public void update() {
		xPos = Math.min(Math.max(0, (int) followed.getPosition().x - vpSize.width/2 + followed.getArea().width/2),
				gameSize.width - vpSize.width);
		yPos = Math.min(Math.max(0, (int) followed.getPosition().y - vpSize.height/2 + followed.getArea().height/2),
				gameSize.height - vpSize.height);
	}

	public int viewPortX() {
		return xPos;
	}

	public int viewPortY() {
		return yPos;
	}

	public void scrollTo(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void scrollX(int nx) {
		xPos = nx;
	}

	public void scrollY(int ny) {
		yPos = ny;
	}
}
