package pkgTestSAP;

import java.text.SimpleDateFormat;

import pkgSAP.SAPProductModel;

public class TestMain {

	public TestMain() {}

	public static void main(String[] args) {
		SAPProductModel spm = new SAPProductModel("BCUSER","MINISAP","10.0.0.8");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			spm.insertProduct("Cookies", 5.30f, 100, sdf.parse("19950302"), "Eminem", "CD", "RAP", "is em", "path");
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
