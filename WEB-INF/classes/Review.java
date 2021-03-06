public class Review{
	private String productName;
	private String userName;
	private String productType;
	private String productMaker;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	private String retailerZip;

	public Review(String productName, String userName, String retailerZip, String productType, String productMaker, String reviewRating, String reviewDate, String reviewText){
		this.productName=productName;
		this.userName=userName;
		this.retailerZip=retailerZip;
		this.productType=productType;
		this.productMaker=productMaker;
		this.reviewRating=reviewRating;
		this.reviewDate=reviewDate;
		this.reviewText=reviewText;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductName(String productName){
		this.productName=productName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName=userName;
	}

	public String getRetailerZip(){
		return this.retailerZip;
	}

	public void setRetailerZip(String retailerZip){
		this.retailerZip=retailerZip;
	}

	public String getProductType(){
		return productType;
	}

	public void setProductType(String productType){
		this.productType=productType;
	}

	public String getProductMaker(){
		return productMaker;
	}

	public void setProductMaker(String productMaker){
		this.productMaker=productMaker;
	}

	public String getReviewRating(){
		return reviewRating;
	}

	public void setReviewRaing(String reviewRating){
		this.reviewRating=reviewRating;
	}

	public String getReviewDate(){
		return reviewDate;
	}

	public void setReviewDate(String reviewDate){
		this.reviewDate=reviewDate;
	}

	public String getReviewText(){
		return reviewText;
	}

	public void setReviewText(String reviewText){
		this.reviewText=reviewText;
	}

}