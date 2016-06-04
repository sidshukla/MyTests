package com.bmc.analyser.springer.model;

/**
 * Grid location object which can be sorted on the basis of heat value
 * 
 * @author sshukla
 * 
 */
public class GridLocation implements Comparable<GridLocation> {

	private int xLoc;

	private int yLoc;

	private int heat;

	/**
	 * constructor
	 * @param xLoc
	 * @param yLoc
	 * @param heat
	 */
	public GridLocation(int xLoc, int yLoc, int heat) {
		super();
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.heat = heat;
	}
	
	/**
	 * Empty constructor
	 */
	public GridLocation() {
	}

	/**
	 * @return the xLoc
	 */
	public int getxLoc() {
		return xLoc;
	}

	/**
	 * @param xLoc
	 *            the xLoc to set
	 */
	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	/**
	 * @return the yLoc
	 */
	public int getyLoc() {
		return yLoc;
	}

	/**
	 * @param yLoc
	 *            the yLoc to set
	 */
	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	/**
	 * @return the heat
	 */
	public int getHeat() {
		return heat;
	}

	/**
	 * @param heat
	 *            the heat to set
	 */
	public void setHeat(int heat) {
		this.heat = heat;
	}

	@Override
	public int compareTo(GridLocation analysed) {
		int returnVal = 0;
		if (this.heat > analysed.getHeat()) {
			returnVal = -1;
		} else if (this.heat == analysed.getHeat()) {
			returnVal = 0;
		} else if (this.heat < analysed.getHeat()) {
			returnVal = 1;
		}
		return returnVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + heat;
		result = prime * result + xLoc;
		result = prime * result + yLoc;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridLocation other = (GridLocation) obj;
		if (heat != other.heat)
			return false;
		if (xLoc != other.xLoc)
			return false;
		if (yLoc != other.yLoc)
			return false;
		return true;
	}
}