/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.gmaps;

/**
 *
 * @author Henrique
 */
public class GMapsMatrix {

    public GMapsMatrix() {
        distance = null;
        duration = null;
    }

    public String getDistance() {

        return distance;

    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    String distance;
    String duration;
}
