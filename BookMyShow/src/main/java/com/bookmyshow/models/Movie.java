package com.bookmyshow.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
	private String name;
	private Date releaseDate;
	@Enumerated(EnumType.ORDINAL)
	@ElementCollection
	private List<Feature> features;

}
