package pkgSAP;

import java.text.SimpleDateFormat;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Client;
import com.sap.mw.jco.JCO.Connection;
import com.sap.mw.jco.JCO.Function;

public class SAPProductModel {
	
	private JCO.Client mConnection = null;
	private JCO.Repository mRepository = null;
	
	String userName;
	String password;
	String ipAddress;

	public SAPProductModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void createConnection() throws Exception {
		String mandant = "000";
		String language = "EN";
		String systemNumber = "00";
				
		mConnection = JCO.createClient(mandant, userName, password, language, ipAddress, systemNumber);
		mConnection.connect();
	}
	
	public void disconnect() {
		if(mConnection != null) {
			mConnection.disconnect();
			mConnection = null;
		}
	}
	
	public void insertProduct(String newPMotivation, String newPName,
			String newPDate, String newDNo) throws Exception {
		JCO.Function insert = this.getFunction("ZBAPI_DEPARTMENT_INSERTPUPIL");
		JCO.Function commit = this.getFunction("BAPI_TRANSACTION_COMMIT");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Date d = sdf.parse(newPDate);
		//SAPConnection.setInsertPupilParameter(insert, newPName, newPMotivation, newPDate, newDNo);
		this.executeFunction(insert);
		this.executeFunction(commit);
		System.out.println("X");
		this.disconnect();
	}

	private static void setInsertPupilParameter(Function insert, String newPName,
			String newPMotivation, String newPDate, String newDNo) {
		insert.getImportParameterList().setValue(newDNo, "IDNO");
		insert.getImportParameterList().setValue(newPName, "IPNAME");
		insert.getImportParameterList().setValue(newPMotivation, "IMOTIV");
		insert.getImportParameterList().setValue(newPDate, "IPDATE");
	}
	
	private JCO.Function getFunction(String nameOfFunction) throws Exception {
		mRepository = new JCO.Repository("MyRepository", mConnection);
		IFunctionTemplate ft = mRepository.getFunctionTemplate(nameOfFunction.toUpperCase());
		if (ft == null)
			throw new Exception("function not found in SAP Repository.");
		return ft.getFunction();
	}
	
	private void executeFunction (JCO.Function _sapFunction) throws Exception {
		this.mConnection.execute(_sapFunction);
	}

}
