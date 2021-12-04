
package electricalsinventorysystem;

/**
 * @author SILK
 *
 */
public class Item {
	private String itemID;
	private String itemDesc;
	private int itemQty;
	private double itemPrice;
	
	public Item() {
		
	}

	/**
	 * @param itemID
	 * @param itemDesc
	 * @param itemQty
	 * @param itemPrice
	 */
	public Item(String itemID, String itemDesc, int itemQty, double itemPrice) {
		
		this.itemID = itemID;
		this.itemDesc = itemDesc;
		this.itemQty = itemQty;
		this.itemPrice = itemPrice;
	}
	
	@Override
	public String toString() {
		return String.format("%-12d %-30s %-10d %.2f%n", itemID,itemDesc, itemQty, itemPrice);
	}

	
	
}
