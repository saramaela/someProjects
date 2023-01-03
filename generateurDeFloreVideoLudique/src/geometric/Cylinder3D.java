package geometric;
import javafx.scene.shape.Cylinder;
import javafx.geometry.Point3D;
import javafx.scene.paint.PhongMaterial;
import javafx.geometry.*;
import javafx.scene.transform.Translate;
import javafx.scene.transform.Rotate;
import javafx.scene.paint.*;

/**
 * Cette classe permet de créer et gérer un cylindre3D
 */
public class Cylinder3D {
    
    private Point3D startingPoint, endingPoint;
    private double angle;
    private int radius, height_branch, divisions;

    /**
     * Unique constructeur Cylinder3D
     */
    public Cylinder3D() {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.radius = radius;
        this.divisions = divisions;
    }

    /**
     * Sert à créer un Cylindre avec les paramètres du constructeur
     */
    public Cylinder createCylinder3D() {
        Cylinder cylinder = new Cylinder(radius, height_branch, divisions);
        cylinder.setTranslateX(startingPoint.getX());
        cylinder.setTranslateY(startingPoint.getY());
        cylinder.setTranslateZ(startingPoint.getZ());
        cylinder.setRotate(angle + 90);
        this.endingPoint = new Point3D(getStartingPoint().getX(), getStartingPoint().getY() - getHeight() + 250, getStartingPoint().getZ());
        return cylinder;
    }
    
    //source: https://netzwerg.ch/blog/2015/03/22/javafx-3d-line/
    /**
     * Créer une ligne entre deux points (Cette ligne est un objet Cylinder)
     * @param startingPoint
     * @param endingPoint
     * @param RADIUS_BRANCH
     * @param div
     * @return Cylinder
     */
    public static Cylinder createConnection(Point3D startingPoint, Point3D endingPoint, int RADIUS_BRANCH , int div) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = endingPoint.subtract(startingPoint);
        double height = diff.magnitude();
    
        Point3D mid = endingPoint.midpoint(startingPoint);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());
    
        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);
    
        Cylinder line = new Cylinder(RADIUS_BRANCH, height);
        Material couleurCylinder = new PhongMaterial(javafx.scene.paint.Color.GREEN);
        line.setMaterial(couleurCylinder);
    
        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
        return line;
    }

    //source: https://netzwerg.ch/blog/2015/03/22/javafx-3d-line/
    /**
     * Créer une ligne entre deux points (Cette ligne est un objet Cylinder)
     * @param startingPoint
     * @param endingPoint
     * @param RADIUS_BRANCH
     * @param div
     * @return Cyllinder
     */
    public Cylinder createConnection() {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = endingPoint.subtract(startingPoint);
        double height = diff.magnitude();
    
        Point3D mid = endingPoint.midpoint(startingPoint);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());
    
        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);
    
        Cylinder line = new Cylinder(radius, height);
    
        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
        return line;
    }
    
    /**
     * Donne la hauteur de la branche
     * @return
     */
    public double getHeight() {
        return this.height_branch;
    }

    /**
     * Donne le point de départ de la branche
     * @return
     */
    public Point3D getStartingPoint() {
        return startingPoint;
    }

    /**
     * Donne le point d'arrivée de la branche
     * @return
     */
    public Point3D getEndingPoint() {
        return endingPoint;
    }

    /**
     * Refinit le point de départ de la branche
     * @return
     */
    public void setStartingPoint(Point3D newPoint) {
        this.startingPoint = newPoint;
    }

    /**
     * Refinit le point d'arrivée de la branche
     * @param newPoint
     */
    public void setEndingPoint(Point3D newPoint) {
        this.endingPoint = newPoint;
    }
    

    @Override
    public String toString() {
        return "\nCylinder[hauteur = " + getHeight() + "startingPoint :" + getStartingPoint().toString() + ", endingpoint :" + getEndingPoint().toString();
    }
    

}
