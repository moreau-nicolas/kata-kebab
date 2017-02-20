package com.github.moreaunicolas.katas.kebab;

public interface Diet {

    String name();
    boolean forbids(Category category);
}
