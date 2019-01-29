package org.jing.web.db.object.item.dto;

import org.jing.web.db.bm.user.dto.UserDto;

/**
 * Description: <br>
 *
 * @author: bks <br>
 * @createDate: 2019-01-29 <br>
 */
public class ItemDto {
    private int id;

    private String name;

    private String author;

    private String org;

    private int seq;

    private String party;

    private String origLanguage;

    private String language;

    private String spec;

    private String event;

    private String state;

    private String crtDate;

    private UserDto crtUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getOrigLanguage() {
        return origLanguage;
    }

    public void setOrigLanguage(String origLanguage) {
        this.origLanguage = origLanguage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(String crtDate) {
        this.crtDate = crtDate;
    }

    public UserDto getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(UserDto crtUser) {
        this.crtUser = crtUser;
    }
}
