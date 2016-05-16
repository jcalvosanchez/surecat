package com.htic.hticarq.core.model;

import com.htic.hticarq.core.model.exception.BadDataFormatException;
import com.htic.hticarq.core.model.exception.BadIntegerFormatException;

public class IntegerVO {

	private Integer number;


	public IntegerVO(String numberInput) {
		if (numberInput == null) {
			throw new NullPointerException();
		} else {
			try {
				new Double (numberInput);
			} catch (NumberFormatException e) {
				throw new BadDataFormatException();
			}
			try {
				while (numberInput.contains(".0")) {
					numberInput = numberInput.replace(".0", ".");
				}
				if (numberInput.endsWith(".")) {
					numberInput = numberInput.substring(0, numberInput.length()-1);
				}

				this.number = new Integer (numberInput);
			} catch (NumberFormatException e) {
				throw new BadIntegerFormatException();
			}
		}
	}
	public IntegerVO(Integer numberInput) {
		this.number = numberInput;
	}
	public IntegerVO(int numberInput) {
		this.number = new Integer (numberInput);
	}


	public Integer getNumber() {
		return this.number;
	}
}