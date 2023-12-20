package com.ITS.ITS.Entity;

public class JwtResponse {


    private String token;
    private String email;

    // Private constructor to force usage of the builder
    private JwtResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

//     Builder class for JwtResponse
    public static class Builder {
        private String token;
        private String email;

        public Builder jwtToken(String token) {
            this.token = token;
            return this;
        }

        public Builder username(String email) {
            this.email = email;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(this.token, this.email);
        }
    }
}