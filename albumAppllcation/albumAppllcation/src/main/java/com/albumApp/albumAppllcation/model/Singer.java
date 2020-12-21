package com.albumApp.albumAppllcation.model;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long singerId;
    @Column(nullable = false, length = 200)
    @Pattern(regexp = "^[\\w ]*$", message = "Enter a valid name")
    private String singerName;
    @Min(value = 1, message = "Enter a valid age")
    @Max(value = 110, message = "Enter a valid age")
    private int age;
    @Column(nullable = false, length = 200)
    @Pattern(regexp = "^[\\w\\s]*$", message = "Enter a valid country")
    private String country;
    @NotNull
    private Gender gender;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Enter a valid Debut Date")
    private Date debut;
    @Min(value = 0, message = "Invalid count")
    @Max(value = 1000000, message = "Invalid Count")
    private int awardsReceived;
    @NotNull
    private String hobby;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "Album_Singer", joinColumns = {@JoinColumn(name = "singerId")}, inverseJoinColumns = {@JoinColumn(name = "albumId")})
    @JsonBackReference
    private Set<Album> listOfAlbums;

    public enum Gender {
        M, F
    }

    public Singer() {
    }

    public long getSingerId() {
        return singerId;
    }

    public void setSingerId(long singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public int getAwardsReceived() {
        return awardsReceived;
    }

    public void setAwardsReceived(int awardsReceived) {
        this.awardsReceived = awardsReceived;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }


    public Set<Album> getListOfAlbums() {
        return listOfAlbums;
    }

    public void setListOfAlbums(Set<Album> listOfAlbums) {
        this.listOfAlbums = listOfAlbums;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "singerId=" + singerId +
                ", singerName='" + singerName + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", gender=" + gender +
                ", debut=" + debut +
                ", awardsReceived=" + awardsReceived +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Singer)) return false;
        Singer singer = (Singer) o;
        return getSingerId() == singer.getSingerId() && getAge() == singer.getAge() && getAwardsReceived() == singer.getAwardsReceived() && getSingerName().equals(singer.getSingerName()) && getCountry().equals(singer.getCountry()) && getGender() == singer.getGender() && getDebut().equals(singer.getDebut()) && getHobby().equals(singer.getHobby()) && getListOfAlbums().equals(singer.getListOfAlbums());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSingerId(), getSingerName(), getAge(), getCountry(), getGender(), getDebut(), getAwardsReceived(), getHobby());
    }
}
