package sprint_Four;

public class Tax extends Square {
  
  private String nameOfTaxSite;
  protected int taxAmount;
  
  public Tax(String nameOfTaxSite, int type, int taxAmount)
  {
    super(nameOfTaxSite, type);
    
    this.nameOfTaxSite = nameOfTaxSite;
    this.taxAmount = taxAmount;
  }
  
  public String taxSite()
  {
    return nameOfTaxSite;
  }
  
  public int getTaxAmount()
  {
    return taxAmount;
  }

}
