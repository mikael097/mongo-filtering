package com.rogue.search.collections;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
  private String firstAddress;
  private String lastAddress;
}
