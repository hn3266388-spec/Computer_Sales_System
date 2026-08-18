package com.example.MyProject.PRODUCT.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


public class ProcessPageable {
      public static Pageable createPageable(
              int page,
              int size,
              String sortBy,
              String sortDir
      ){
          Sort sort;

          if (sortDir.equalsIgnoreCase("asc")) {
              sort = Sort.by(sortBy).ascending();
          } else {
              sort = Sort.by(sortBy).descending();
          }
//          Sort sort = sortDir.equalsIgnoreCase("asc")
//                  ? Sort.by(sortBy).ascending()
//                  : Sort.by(sortBy).descending();

          return PageRequest.of(page, size, sort);
      }
}
