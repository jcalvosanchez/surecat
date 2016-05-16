package com.htic.hticarq.poi.model;

/**
 * -1 values means null, although we should use Integer instead of int
 */
public class ExcelCoordinates {

	private int sheetIndex;
	/** Excel starts counting rows from 0 */
	private int row;
	/** Excel starts counting columns from 0 */
	private int column;


	public ExcelCoordinates(){
		this.setSheetIndex(-1);
		this.setRow(-1);
		this.setColumn(-1);
	}
	public ExcelCoordinates(int sheetIndex, int row, int column) {
		this.setSheetIndex(sheetIndex);
		this.setRow(row);
		this.setColumn(column);
	}


	//Getters && Setters
	public int getSheetIndex() {
		return sheetIndex;
	}
	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
}