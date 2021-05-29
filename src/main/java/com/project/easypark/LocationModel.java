package com.project.easypark;

import com.google.android.gms.maps.model.LatLng;

public class LocationModel {

    private int id;
    private String timestamp;
    private LatLng latLng;
    private String address;

    public LocationModel(int id, String timestamp, LatLng latLng, String address) {
        this.id = id;
        this.timestamp = timestamp;
        this.latLng = latLng;
        this.address = address;
    }

    public LocationModel() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", latLng=" + latLng +
                ", address='" + address + '\'' +
                '}';
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getAddress() {
        return address;
    }

    public String getLat(){return ""+latLng.latitude;}
    public String getLong(){return ""+latLng.longitude;}
    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
