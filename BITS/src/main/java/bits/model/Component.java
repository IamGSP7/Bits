package bits.model;
public class Component {
    private int id, categoryId, quantity; private String name, value, category, location, notes;
    public Component(int id,int categoryId,String name,String value,int quantity,String category,String location,String notes){this.id=id;this.categoryId=categoryId;this.name=name;this.value=value;this.quantity=quantity;this.category=category;this.location=location;this.notes=notes;}
    public int getId(){return id;} public int getCategoryId(){return categoryId;} public String getName(){return name;} public String getValue(){return value;} public int getQuantity(){return quantity;} public String getCategory(){return category;} public String getLocation(){return location;} public String getNotes(){return notes;}
}
