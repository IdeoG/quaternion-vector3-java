package com.example.ideog.myapplication;

/**
 * Created by ideog on 20.11.2017.
 */


public class Vector3 {
    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z) {
        this.set(x, y, z);
    }

    public Vector3(Vector3 v) {
        this.set(v);
    }

    public Vector3() {
        this.set(0, 0, 0);
    }

    public Vector3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3 set(Vector3 v) {
        x = v.x;
        y = v.y;
        z = v.z;
        return this;
    }

    public Vector3 normalize() {
        float norm = norm();
        x = x / norm;
        y = y / norm;
        z = z / norm;
        return this;
    }

    public Vector3 add(Vector3 v) {
        Vector3 v0 = new Vector3();
        Vector3 v1 = this;
        Vector3 v2 = v;
        v0.x = v1.x + v2.x;
        v0.y = v1.y + v2.y;
        v0.z = v1.z + v2.z;
        return v0;
    }

    public Vector3 minus(Vector3 v) {
        Vector3 v0 = new Vector3();
        Vector3 v1 = this;
        Vector3 v2 = v;
        v0.x = v1.x - v2.x;
        v0.y = v1.y - v2.y;
        v0.z = v1.z - v2.z;
        return v0;
    }

    public Vector3 mul1d(float a) {
        Vector3 v = this;
        v.x *= a;
        v.y *= a;
        v.z *= a;
        return v;
    }

    public Vector3 cross(Vector3 v) {
        Vector3 v1 = this;
        Vector3 v2 = v;
        v.x = v1.y * v2.z - v1.z * v2.y;
        v.y = v1.z * v2.x - v1.x * v2.z;
        v.z = v1.x * v2.y - v1.y * v2.x;
        return v;
    }

    public float dot(Vector3 v) {
        Vector3 v1 = this;
        Vector3 v2 = v;
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public Quaternion calculate_vector_rotation_quaternion(Vector3 v) {
        Vector3 v1 = this;
        Vector3 v2 = v;
        v = v1.cross(v2);
        Quaternion q = new Quaternion(v.x, v.y, v.z, 0);
        q.w = v1.norm() * v2.norm() + v1.dot(v2);
        q.normalize();
        return q;
    }

    public Vector3 proj(Vector3 direction) {
        float dot_product = dot(direction);
        float norm = direction.norm();
        Vector3 v = new Vector3(direction.mul1d(dot_product/norm));
        return v;
    }

    public float norm() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public String toString() {
        return "vx," + String.valueOf(x) + ",\nvy," + String.valueOf(y) + ",\nvz," + String.valueOf(z);
    }
}