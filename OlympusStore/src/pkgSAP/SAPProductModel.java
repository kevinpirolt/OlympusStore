package pkgSAP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;

public class SAPProductModel {
	
	private JCO.Client mConnection = null;
	private JCO.Repository mRepository = null;
	
	String userName = null;
	String password = null;
	String ipAddress = null;

	public SAPProductModel() {}
	
	public SAPProductModel(String userName, String password, String ipAddress) {
		this.userName = userName;
		this.password = password;
		this.ipAddress = ipAddress;
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
	
	public void insertProduct(final String pname, final float price, final int initialQuantity) throws Exception {
		this.createConnection();
		JCO.Function insert = this.getFunction("ZBAPI_OLYMPUS_INSERTPRODUCT");
		JCO.Function commit = this.getCommitFunction();
		ArrayList<Object> values = this.prepareArrayList(pname, price, initialQuantity);
		ArrayList<String> names = this.prepareStringArrayList("INSNAME", "INSPRICE", "INSQTY");
		SAPProductModel.setInsertParameter(insert, values, names);
		this.executeFunction(insert);
		this.executeFunction(commit);
		this.disconnect();
	}
	
	private ArrayList<Object> prepareArrayList(Object... vals) {
		ArrayList<Object> values = new ArrayList<>();
		for(Object o : vals)
			values.add(o);
		return values;
	}
	
	private ArrayList<String> prepareStringArrayList(String... vals) {
		ArrayList<String> values = new ArrayList<>();
		for(String o : vals)
			values.add(o);
		return values;
	}

	private Function getCommitFunction() throws Exception {
		return this.getFunction("BAPI_TRANSACTION_COMMI");
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
	
	private static void setInsertParameter(JCO.Function fun, ArrayList<Object> values,
			ArrayList<String> names) throws SAPException {
		if(values.size() != names.size())
			throw new SAPException("Count of Function-Parameters and Function-Parameter-Names"
					+ " are not equal");
		for(int i = 0; i<values.size(); i++) {
			fun.getImportParameterList().setValue(values.get(i), names.get(i));
		}
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
