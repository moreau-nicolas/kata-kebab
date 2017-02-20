package com.github.moreaunicolas.katas.kebab;

import java.util.Collection;
import java.util.stream.Stream;

import static com.github.moreaunicolas.katas.kebab.Categories.*;
import static java.util.stream.Collectors.toSet;

public enum Diets implements Diet {

    VEGETARIAN /* can't eat */ (MEAT, FISH, CRUSTACEAN, SHELLFISH),
    PESCETARIAN /* can't eat */ (MEAT),
    ;

    private final Collection<Category> forbiddenCategories;

    Diets(Category... forbiddenCategories) {
        this.forbiddenCategories = Stream.of(forbiddenCategories)
                .collect(toSet());
    }

    @Override
    public boolean forbids(Category category) {
        return forbiddenCategories.contains(category);
    }
}
