package com.rakesh.reactivespring.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Value {
    private int id;
    private String joke;
    private String[] categories;

}
