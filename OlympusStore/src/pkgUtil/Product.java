package pkgUtil;

import java.util.Date;

public class Product {

	private int id;
	private float price;
	private int quantity;
	private Date releaseDate;
	private String interpret;
	private String genre;
	private String description;
	private String image;

	public Product() {
	}

	public Product(int id, float price, int quantity, Date releaseDate,
			String interpret, String genre, String description, String image) {
		super();
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.releaseDate = releaseDate;
		this.interpret = interpret;
		this.genre = genre;
		this.description = description;
		this.image = image;
	}

	// -----------------------------------------------------------------------------------------------------
	public int getId() {
		return id;
	}

	public float getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getInterpret() {
		return interpret;
	}

	public String getGenre() {
		return genre;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
