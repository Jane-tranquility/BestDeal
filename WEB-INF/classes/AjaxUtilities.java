import java.lang.StringBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class AjaxUtilities {
	public static StringBuffer readdata(String searchId){
		StringBuffer sb=new StringBuffer();
		HashMap<String, Product> data=MySQLDataStoreUtilities.getData();

		Iterator it=data.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry pi=(Map.Entry)it.next();
			Product p=(Product)pi.getValue();
			if (p.getProductName().startsWith(searchId)){
				sb.append("<product>"); 
				sb.append("<id>" + p.getProductID() + "</id>"); 
				sb.append("<productName>" + p.getProductName() + "</productName>"); 
				sb.append("</product>");
			}
		}
		return sb;
	}
}