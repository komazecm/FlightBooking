/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Neon
 */
public class Avion implements Serializable {
    
    private int kapacitet;

    public Avion() {
    }

    public Avion(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }
    
    
    
}
