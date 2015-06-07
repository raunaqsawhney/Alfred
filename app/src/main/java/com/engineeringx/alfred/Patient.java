package com.engineeringx.alfred;

/**
 * Created by raunaqsawhney on 6/7/15.
 */
public class Patient {

    private String patientID;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private int heartRate;
    private int bodyTemperature;

    Patient(String patientID, String firstName, String lastName, int age, String gender,
            int heartRate, int bodyTemperature) {

        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.heartRate = heartRate;
        this.bodyTemperature = bodyTemperature;
    }

    /*
    List of all the getter methods for the Patient class
     */

    public String getPatientID() {
        return patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender(){
        return gender;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public int getBodyTemperature() {
        return bodyTemperature;
    }

    /*
    List of all the setter methods for the Patient class
     */
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void setBodyTemperature(int bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
