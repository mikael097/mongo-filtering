package com.rogue.search.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rogue.search.collections.helper.search.SearchCriteria;
import com.rogue.search.collections.helper.search.SearchFilter;
import com.rogue.search.collections.helper.search.SearchOperator;
import com.rogue.search.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.ObjectUtils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MongoFilter {
  private static final String REGEX_LIKE = "^.*%s.*$";

  public static Query createSearchCriteria(String filters) {
    final Query query = new Query();
    //TODO: use Utility
    if (!ObjectUtils.isEmpty(filters)) {
      try {
        SearchFilter searchFilter = JsonUtils.readValue(URLDecoder.decode(filters, StandardCharsets.UTF_8), SearchFilter.class);
        var op = handleEnum(searchFilter.getOp());
        query.fields().include(searchFilter.getRequiredFields().toArray(new String[0]));
        final List<Criteria> criteriaList = new ArrayList<>();
        for (SearchCriteria searchCriteria : searchFilter.getFields()) {
          addCriteria(searchCriteria, criteriaList);
        }
        if (!criteriaList.isEmpty()) {
          if (op == SearchOperator.AND) {
            query.addCriteria(new Criteria().andOperator(criteriaList));
          } else if (op == SearchOperator.OR) {
            query.addCriteria(new Criteria().orOperator(criteriaList));
          }
        }
      } catch (JsonProcessingException e) {
        log.error("Incorrect Filter or Encoding", e);
      }
    }
    return query;
  }

  private static void addCriteria(SearchCriteria searchCriteria, List<Criteria> criteria) {
    var op = handleEnum(searchCriteria.getOp());
    switch (op) {
      case EQ -> criteria.add(Criteria.where(searchCriteria.getField()).is(searchCriteria.getValues().get(0)));
      case NE -> criteria.add(Criteria.where(searchCriteria.getField()).ne(searchCriteria.getValues().get(0)));
      case ANY -> criteria.add(Criteria.where(searchCriteria.getField()).in(searchCriteria.getValues()));
      case GT -> criteria.add(Criteria.where(searchCriteria.getField()).gt(searchCriteria.getValues().get(0)));
      case GOE -> criteria.add(Criteria.where(searchCriteria.getField()).gte(searchCriteria.getValues().get(0)));
      case LT -> criteria.add(Criteria.where(searchCriteria.getField()).lt(searchCriteria.getValues().get(0)));
      case LOE -> criteria.add(Criteria.where(searchCriteria.getField()).lte(searchCriteria.getValues().get(0)));
      case LIKE ->
              criteria.add(Criteria.where(searchCriteria.getField()).regex(String.format(REGEX_LIKE, searchCriteria.getValues().get(0).toString())));
      default -> throw new RuntimeException("temp");
    }
  }

  private static SearchOperator handleEnum(String op) {
    return SearchOperator.valueOf(op);
  }
}
