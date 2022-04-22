package com.cargue.loadData.model.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class LoadData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentId;
    private String idDevice;
    private String subscriptionId;
    private String modelId;
    private String uUIDCode;
    private String serialSimCard;
    private String operativeSystem;
    private String versionOP;
    private String userStatus;
    private String userSTI;
    private LocalDateTime creationDate;
    private String updateUser;
    private LocalDateTime updateDate;
    private String modelerIndicator;
    private String typeDocumentCode;
    private String accountStatus;
    private String descriptionAccountStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getuUIDCode() {
        return uUIDCode;
    }

    public void setuUIDCode(String uUIDCode) {
        this.uUIDCode = uUIDCode;
    }

    public String getSerialSimCard() {
        return serialSimCard;
    }

    public void setSerialSimCard(String serialSimCard) {
        this.serialSimCard = serialSimCard;
    }

    public String getOperativeSystem() {
        return operativeSystem;
    }

    public void setOperativeSystem(String operativeSystem) {
        this.operativeSystem = operativeSystem;
    }

    public String getVersionOP() {
        return versionOP;
    }

    public void setVersionOP(String versionOP) {
        this.versionOP = versionOP;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserSTI() {
        return userSTI;
    }

    public void setUserSTI(String userSTI) {
        this.userSTI = userSTI;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getModelerIndicator() {
        return modelerIndicator;
    }

    public void setModelerIndicator(String modelerIndicator) {
        this.modelerIndicator = modelerIndicator;
    }

    public String getTypeDocumentCode() {
        return typeDocumentCode;
    }

    public void setTypeDocumentCode(String typeDocumentCode) {
        this.typeDocumentCode = typeDocumentCode;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getDescriptionAccountStatus() {
        return descriptionAccountStatus;
    }

    public void setDescriptionAccountStatus(String descriptionAccountStatus) {
        this.descriptionAccountStatus = descriptionAccountStatus;
    }
}

