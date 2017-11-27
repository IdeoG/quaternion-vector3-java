package com.example.ideog.myapplication;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by ideog on 20.11.2017.
 */


public class Quaternion {
    public float x, y, z, w;
    public Vector3 e = new Vector3();

    public Quaternion(float w, float x, float y, float z) {
        this.set(w, x, y, z);
    }

    public Quaternion() {
        this.set(1f, 0f, 0f, 0f);
    }

    public Quaternion(Vector3 v) {
        e.x = v.x;
        e.y = v.y;
        e.z = v.z;

        ETQ();
    }

    public Quaternion(Quaternion q) {
        this.set(q);
    }

    public Quaternion set(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Quaternion set(Quaternion q) {
        this.w = q.w;
        this.x = q.x;
        this.y = q.y;
        this.z = q.z;
        return this;
    }

    private void ETQ() {
        float t0 = (float) cos(e.z * 0.5f);
        float t1 = (float) sin(e.z * 0.5f);
        float t2 = (float) cos(e.x * 0.5f);
        float t3 = (float) sin(e.x * 0.5f);
        float t4 = (float) cos(e.y * 0.5f);
        float t5 = (float) sin(e.y * 0.5f);

        System.out.println("Debug\n" + e.toString());
        System.out.println();

        w = t0 * t2 * t4 + t1 * t3 * t5;
        x = t0 * t3 * t4 - t1 * t2 * t5;
        y = t0 * t2 * t5 + t1 * t3 * t4;
        z = t1 * t2 * t4 - t0 * t3 * t5;
    }

    public Quaternion normalize() {
        float norm = (float) Math.sqrt(x * x + y * y + z * z + w * w);
        w = w / norm;
        x = x / norm;
        y = y / norm;
        z = z / norm;
        return this;
    }

    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    public Vector3 eulerAngles() {
        Vector3 eulerAngles = new Vector3();

        float sqw = w * w;
        float sqx = x * x;
        float sqy = y * y;
        float sqz = z * z;

        eulerAngles.x = (float) Math.atan2(2.0 * (x * y + z * w), (sqx - sqy - sqz + sqw));
        eulerAngles.y = (float) Math.atan2(2.0 * (y * z + x * w), (-sqx - sqy + sqz + sqw));
        eulerAngles.z = (float) Math.asin(-2.0 * (x * z - y * w));

        return eulerAngles;
    }

    public Quaternion cross(Quaternion q) {
        Quaternion q1 = new Quaternion(this);
        Quaternion q2 = new Quaternion(q);

        q.w = q1.w * q2.w - q1.x * q2.x - q1.y * q2.y - q1.z * q2.z;
        q.x = q1.w * q2.x + q1.x * q2.w + q1.y * q2.z - q1.z * q2.y;
        q.y = q1.w * q2.y + q1.y * q2.w + q1.z * q2.x - q1.x * q2.z;
        q.z = q1.w * q2.z + q1.z * q2.w + q1.x * q2.y - q1.y * q2.x;

        return q;
    }

    public Vector3 rotateVector(Vector3 v) {
        Quaternion q = new Quaternion(0, v.x, v.y, v.z);
        System.out.println("Debug\n" + q.toString());
        System.out.println();
        Quaternion inverse_self = conjugate();
        Quaternion cross_product = cross(q);
        q = cross_product.cross(inverse_self);
        v = new Vector3(q.x, q.y, q.z);
        return v;
    }

    public Quaternion calculateTwist(Vector3 direction) {
        Vector3 ra = new Vector3(x, y, z);
        Vector3 p = ra.proj(direction);
        Quaternion twist = new Quaternion(w, p.x, p.y, p.z);
        twist = twist.normalize();
        return twist;
    }

    public String toString() {
        return "qw," + String.valueOf(w) + ",\nqx," + String.valueOf(x) +
                ",\nqy," + String.valueOf(y) + ",\nqz," + String.valueOf(z);
    }

}