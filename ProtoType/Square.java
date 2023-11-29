package ProtoType;

public class Square extends Shape {

    public Square(){
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }

    @Override
    public Object clone() {
        Square square = new Square();
        square.setId(this.getId()); // Copy the id field
        return square;
    }
}
