package com.khtn.healthcare.view.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class DataResponse {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("alle")
    @Expose
    private String alle;
    @SerializedName("insu")
    @Expose
    private String insu;
    @SerializedName("mec_his")
    @Expose
    private ArrayList<MedHisResponse> mecHis = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlle() {
        return alle;
    }

    public void setAlle(String alle) {
        this.alle = alle;
    }

    public String getInsu() {
        return insu;
    }

    public void setInsu(String insu) {
        this.insu = insu;
    }

    public ArrayList<MedHisResponse> getMecHis() {
        return mecHis;
    }

    public void setMecHis(ArrayList<MedHisResponse> mecHis) {
        this.mecHis = mecHis;
    }
}
