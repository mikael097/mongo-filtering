package com.rogue.search.collections;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Document
@Getter
@Setter
@Builder
public class User {
  @Id
  private String id;
//  @Field("first_name")
  private String firstName;
  private String lastName;
  private Integer phNo;
  private String email;
  private Address address;
  private List<Integer> marks;
  private Map<String, String> properties;
}
