package org.acme.entity;

import javax.persistence.*;

@Entity
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(nullable = false,updatable = false)
    private Long id;

    private String name;
    private String legalName;
    private String ABN;
    private String ACN;
    private Boolean active;

    public Business(Long id, String name, String legalName, String ABN, String ACN, Boolean active) {
        this.id = id;
        this.name = name;
        this.legalName = legalName;
        this.ABN = ABN;
        this.ACN = ACN;
        this.active = active;
    }

    public Business() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getABN() {
        return ABN;
    }

    public void setABN(String ABN) {
        this.ABN = ABN;
    }

    public String getACN() {
        return ACN;
    }

    public void setACN(String ACN) {
        this.ACN = ACN;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", legalName='" + legalName + '\'' +
                ", ABN='" + ABN + '\'' +
                ", ACN='" + ACN + '\'' +
                ", active=" + active +
                '}';
    }
}
