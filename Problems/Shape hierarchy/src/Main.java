abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}
class Triangle extends Shape{
    private double firstSide;
    private double secondSide;
    private double threeSide;

    public Triangle(double firstSide, double secondSide, double threeSide){
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.threeSide=threeSide;
    }


    @Override
    double getPerimeter() {
        return firstSide+secondSide+threeSide;
    }

    @Override
    double getArea() {
        double p = 0.5*getPerimeter();
        return Math.sqrt(p*(p-firstSide)*(p-secondSide)*(p-threeSide));
    }
}

class Rectangle extends Shape{
    private double firstSide;
    private double secondSide;

    public Rectangle(double firstSide, double secondSide){
        this.firstSide = firstSide;
        this.secondSide = secondSide;
    }

    @Override
    double getPerimeter() {
        return (firstSide+secondSide)*2;
    }

    @Override
    double getArea() {
        return firstSide*secondSide;
    }
}

class Circle extends Shape{
    private double radius;

    public Circle(double radius){
        this.radius=radius;
    }

    @Override
    double getPerimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    double getArea() {
        return Math.PI*Math.pow(radius,2);
    }
}