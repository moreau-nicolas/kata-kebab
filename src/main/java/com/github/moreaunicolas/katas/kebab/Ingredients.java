package com.github.moreaunicolas.katas.kebab;

public enum Ingredients implements Ingredient {
    LETTUCE(Categories.VEGETABLE),
    ONION(Categories.VEGETABLE),
    TOMATO(Categories.FRUIT),
    CHEDDAR(Categories.CHEESE),
    LAMB(Categories.MEAT),
    COD(Categories.FISH),
    ;

    private final Category category;

    Ingredients(Category category) {
        this.category = category;
    }

    @Override
    public Category category() {
        return category;
    }
}
