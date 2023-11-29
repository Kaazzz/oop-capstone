package ProtoType;

public class PrototypePatternDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape3.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape4 = (Shape) ShapeCache.getShape("4");
        System.out.println("Shape : " + clonedShape4.getType());

        Square originalSquare = new Square();
        System.out.println("Original Square: " + System.identityHashCode(originalSquare));

        Square clonedSquare = (Square) ShapeCache.getShape("3");
        System.out.println("Clone Square: " + System.identityHashCode(clonedSquare));



    }
}
