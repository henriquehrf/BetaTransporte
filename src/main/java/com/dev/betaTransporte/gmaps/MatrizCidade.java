/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.betaTransporte.gmaps;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import util.Mask;
import util.Util;

/**
 *
 * @author Henrique
 */
public class MatrizCidade {

    public int map(String Origem, String Destino) throws Exception {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAUHQnTgWQRnt3XrkdCwOpyugF5aovWuJA")
                .build();

        String origins = Origem + ", BRAZIL";

        String destinations = Destino + ", BRAZIL";

        DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                .origins(origins)
                .destinations(destinations)
                .mode(TravelMode.DRIVING)
                .language("pt-BR")
                .avoid(DirectionsApi.RouteRestriction.TOLLS)
                .units(Unit.METRIC)
                .await();

        GMapsMatrix parse = new GMapsMatrix();

        parse.setDistance(matrix.rows[0].elements[0].distance.humanReadable);
        parse.setDuration(matrix.rows[0].elements[0].duration.humanReadable);

        String txt = parse.distance;
        Mask mask = new Mask();
        
        return Integer.parseInt(mask.OnlyInt(txt));

    }
}
