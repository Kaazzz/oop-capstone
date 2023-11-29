package ProtoType;

public class AnotherSquare extends Shape {

    public AnotherSquare(){
        type = "This is a clone Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside AnotherSquare::draw() method.");
    }
}
