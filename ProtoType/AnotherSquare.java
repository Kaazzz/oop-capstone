package ProtoType;

public class AnotherSquare extends Shape {

    public AnotherSquare(){
        type = "Cloned Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside AnotherSquare::draw() method.");
    }

    @Override
    public Object clone() {
        AnotherSquare anotherSquare = new AnotherSquare();
        anotherSquare.setId(this.getId()); // Copy the id field
        return anotherSquare;
    }
}
