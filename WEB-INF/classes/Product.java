public class Product {
	private String productID;
	private String productName;
	private String productType;
	private double price;
	private String image;
	private String manufacture;

	public Product(String productID,String productName, String productType, double price,String image, String manufacture){
		this.productID=productID;
		this.productName=productName;
		this.productType=productType;
		this.price=price;
		this.image=image;
		this.manufacture=manufacture;
	}

	public String getProductID(){
		return productID;
	}

	public void setProductID(String productID){
		this.productID=productID;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductName(String productName){
		this.productName=productName;
	}

	public String getProductType(){
		return productType;
	}

	public void setProductType(String productType){
		this.productType=productType;
	}

	public double getPrice(){
		return price;
	}

	public void setPrice(double price){
		this.price=price;
	}

	public String getImage(){
		return image;
	}

	public void setImage(String image){
		this.image=image;
	}

	public String getManufacture(){
		return manufacture;
	}

	public void setManufacture(String manufacture){
		this.manufacture=manufacture;
	}
}