package com.rogue.search.dto.request;

import com.rogue.search.collections.Address;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CreateUserRequest {
  private String firstName;
  private String lastName;
  private String email;
  private Integer phNo;
  private Address address;
  private List<Integer> marks;
  private Map<String, String> properties;
}
