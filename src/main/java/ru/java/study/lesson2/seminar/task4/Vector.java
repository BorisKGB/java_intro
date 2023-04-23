package ru.java.study.lesson2.seminar.task4;

/**
 * <p>Задача на ООП (пригодится для дз и итогового проекта):<br>
 * 1) Проектирование и создание класса, описывающего вектор<br>
 * Задача:<br>
 * Создайте класс, который описывает вектор (в трёхмерном пространстве).<br>
 * У него должны быть:<br>
 *     • конструктор с параметрами в виде списка координат x, y, z<br>
 *     • метод, вычисляющий длину вектора. Корень можно посчитать с помощью Math.sqrt()<br>
 *     • метод, вычисляющий скалярное произведение<br>
 *     • метод, вычисляющий векторное произведение с другим вектором<br>
 *     • метод, вычисляющий угол между векторами (или косинус угла): косинус угла между векторами равен скалярному произведению векторов, деленному на произведение модулей (длин) векторов<br>
 *     • методы для суммы и разности
 * </p>
 */
public class Vector {
    double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector() {
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * @return vector length
     */
    public double vecrorLength(){
        return Math.sqrt(x*x+y*y+z*z);
    }

    /**
     * @param secondVector second vector to count scalar product
     * @return scalar product
     */
    public double scalar(Vector secondVector){
        return x * secondVector.x + y * secondVector.y + z * secondVector.z;
    }

    public Vector vectorPr(Vector vector) {
        return new Vector(
                y * vector.z - z * vector.y,
                z * vector.x - x * vector.z,
                x * vector.y - y * vector.x
        );
    }

    public double vectorCos(Vector vector) {
        return scalar(vector)/(vecrorLength() * vector.vecrorLength());
    }
}
