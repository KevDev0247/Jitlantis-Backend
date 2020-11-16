package com.jitlantis.backend.API.dto;

import com.jitlantis.backend.API.model.Contact;
import com.jitlantis.backend.API.model.Product;
import com.jitlantis.backend.API.model.Project;
import com.jitlantis.backend.API.model.Repair;

public class RepairDto extends Repair {

    private String contactName;

    private Contact contact;

    private String  projectName;

    private Project project;

    private String  productName;

    private Product product;

    public String getContactName() {
        return contactName;
    }

    public Contact getContact() {
        return contact;
    }

    public String getProjectName() {
        return projectName;
    }

    public Project getProject() {
        return project;
    }

    public String getProductName() {
        return productName;
    }

    public Product getProduct() {
        return product;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
