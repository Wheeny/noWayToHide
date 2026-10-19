
    package com.lastma.data.models;

    public class Vehicle {
        private int id;
        private String registrationNumber;
        private String make;
        private String model;
        private String colour;
        private int driverId;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getRegistrationNumber() { return registrationNumber; }
        public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

        public String getMake() { return make; }
        public void setMake(String make) { this.make = make; }

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }

        public String getColour() { return colour; }
        public void setColour(String colour) { this.colour = colour; }

        public int getDriverId() { return driverId; }
        public void setDriverId(int motoristId) { this.driverId = driverId; }
    }


