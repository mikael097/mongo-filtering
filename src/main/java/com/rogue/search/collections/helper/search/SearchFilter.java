package com.rogue.search.collections.helper.search;

import lombok.Data;

import java.util.List;

@Data
public class SearchFilter {
  private List<SearchCriteria> fields;
  private String op;
  private List<String> requiredFields;
}
