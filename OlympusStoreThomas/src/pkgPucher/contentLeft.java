package pkgPucher;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
/**
 * Bean for contentLeft.xhtml (in template folder)
 */
public class contentLeft implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//@ManagedProperty(value="#{database}")
	
	//private Database database = null;
	
	private String name = "";
	private String password = "";
	private int productsInCart = 0;
	private String visibilityform = "";
	private String visibilityloggedin = "display:none;";

	public contentLeft() {
	}

	//Getter & Setter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getProductsInCart() {
		return productsInCart;
	}

	public void setProductsInCart(int productsInCart) {
		this.productsInCart = productsInCart;
	}
	
	public String getVisibilityform() {
		return visibilityform;
	}

	public void setVisibilityform(String visibilityform) {
		this.visibilityform = visibilityform;
	}

	public String getVisibilityloggedin() {
		return visibilityloggedin;
	}

	public void setVisibilityloggedin(String visibilityloggedin) {
		this.visibilityloggedin = visibilityloggedin;
	}
	
	//Getter & Setter

	
	
	public void checkLogin() {
		
		this.setVisibilityform("display:none;");
		this.setVisibilityloggedin("");
		
	}
	
	public void logOut() {
		
		System.out.println("asdf");
		this.setVisibilityform("");
		this.setVisibilityloggedin("display:none;");
		
	}

	
}