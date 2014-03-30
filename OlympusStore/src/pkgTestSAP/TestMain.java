package pkgTestSAP;

import pkgSAP.SAPProductModel;

public class TestMain {

	public TestMain() {}

	public static void main(String[] args) {
		SAPProductModel spm = new SAPProductModel("bcuser","minisap","192.168.16.18");
		try {
			spm.insertProduct("Cookies", 5.30f, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
