package com.example.ideog.myapplication;

/**
 * Created by ideog on 20.11.2017.
 */

public class Main {
    public static void main(String[] args) {
        test_vec3_to_quat();
    }

    public static void test_rotation() {
        Vector3 v = new Vector3(0, 0, 9.85f);
        Quaternion q = new Quaternion(0.5f, 0.5f, -0.5f, 0.5f);
        q = q.conjugate();
        Vector3 rot_v = q.rotateVector(v);

        System.out.println("Euler angles\n" + q.eulerAngles().mul1d(180f/3.14f).toString());
        System.out.println();
        System.out.println("Before\n" + v.toString());
        System.out.println();
        System.out.println("After\n" + rot_v.toString());
    }

    public static void test_projection() {
        Vector3 v = new Vector3(2, 1, 1);
        Vector3 direction = new Vector3(1, 0, 0);

        System.out.println("Proj\n" + v.proj(direction).toString());
    }

    public static void test_twist() {
        Quaternion q = new Quaternion(0.5f, 0.5f, -0.5f, 0.5f);
        Vector3 direction = new Vector3(0, 0, 1);

        System.out.println("Twist\n" + q.calculateTwist(direction).toString());
    }

    public static void test_vec3_to_quat() {
        Vector3 v = new Vector3((float) (Math.PI/2), 0, 0);
        Quaternion q0 = new Quaternion(0.707f,0, 0, 0.707f);
        Quaternion q1 = new Quaternion(v);
        Quaternion q = q0.cross(q1);
        Vector3 rotate = new Vector3();

        System.out.println("Q0\n" + q0.toString());
        System.out.println();
        System.out.println("Cross product\n" + q.toString());
    }
}
