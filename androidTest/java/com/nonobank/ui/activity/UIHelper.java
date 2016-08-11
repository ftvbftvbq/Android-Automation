package com.nonobank.ui.activity;

//import com.nonobank.ui.activity.elements.RechargeElements;
//import com.nonobank.ui.activity.elements.RegisterElements;

import com.nonobank.ui.activity.elements.loginElements;
import com.robotium.solo.Solo;

//import com.nonobank.ui.activity.elements.takeCashElements;

public class UIHelper {
	private Solo solo = null;
	private loginElements elementsLoginActivity;
//	private RechargeElements elementsRechargeActivity;
//	private takeCashElements elementstakeCashActivity;


	public UIHelper(Solo solo) {
		this.solo = solo;
	}

	public Solo getSolo() {
		return solo;
	}

	public loginElements getElementsLoginActivity() {
		elementsLoginActivity = new loginElements(solo);
		return elementsLoginActivity;

	}

//	public RechargeElements getElementsRechargeActivity() {
//		elementsRechargeActivity = new RechargeElements(solo);
//		return elementsRechargeActivity;
//
//	}
//
//	public takeCashElements getElementsTakeCashActivity() {
//		elementstakeCashActivity = new takeCashElements(solo);
//		return elementstakeCashActivity;
//
//	}

}
