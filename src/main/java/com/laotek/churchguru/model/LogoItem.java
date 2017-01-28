package com.laotek.churchguru.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.laotek.churchguru.model.shared.enums.LogoItemType;

@Entity
public class LogoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "data", length = 16777215)
    private String base64Data;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "filename")
    private String filename;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private LogoItemType logoItemType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    public Date getCreatedDate() {
	return createdDate;
    }

    public Date getLastUpdatedDate() {
	return lastUpdatedDate;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
	this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getContentType() {
	return contentType;
    }

    public String getFilename() {
	return filename;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

    public void setFilename(String filename) {
	this.filename = filename;
    }

    public String getBase64Data() {
	return base64Data;
    }

    public void setBase64Data(String base64Data) {
	this.base64Data = base64Data;
    }

    public LogoItemType getLogoItemType() {
	return logoItemType;
    }

    public void setLogoItemType(LogoItemType logoItemType) {
	this.logoItemType = logoItemType;
    }
}
