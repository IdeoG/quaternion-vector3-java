package com.example.ideog.myapplication;

/**
 * Created by ideog on 20.11.2017.
 */


public class Vector5 {
    public int addr = 0;
    public Vector3 v = new Vector3();
    public float t = 0;

    public Vector5(int addr, float x, float y, float z, float t) {
        this.set(addr, x, y, z, t);
    }

    public Vector5(int addr, Vector3 v, float t) {
        this.set(addr, v.x, v.y, v.z, t);
    }

    public Vector5() {
        this.set(0, 0, 0, 0, 0);
    }

    public Vector5 set(int addr, float x, float y, float z, float t) {
        this.addr = addr;
        this.v.set(x, y, z);
        this.t = t;
        return this;
    }

    public Vector5 set(int addr, Vector3 v, float t) {
        this.addr = addr;
        this.v.set(v);
        this.t = t;
        return this;
    }

    public Vector5 set(Vector5 v5) {
        this.addr = v5.addr;
        this.v.set(v5.v);
        this.t = v5.t;
        return this;
    }

    public Vector5 normalize() {
        v.normalize();
        return this;
    }

    public String toString() {
        return "addr," + String.valueOf(addr) + "," + v.toString() + ",t," + String.valueOf(t);
    }
}