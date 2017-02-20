package com.github.moreaunicolas.katas.kebab;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class Kebab {

    private List<Ingredient> ingredients;

    public static Kebab prepare() {
        return new Kebab();
    }

    private Kebab() {
        this.ingredients = new LinkedList<>();
    }

    public Stream<Ingredient> ingredients() {
        return ingredients.stream();
    }

    public Kebab addLayerOf(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public Kebab removeAllOf(Ingredient ingredient) {
        ingredients.removeIf(ingredient::equals);
        return this;
    }

    public Kebab doubleAll(Category category) {
        this.ingredients = ingredients()
                .flatMap(doubleIngredientsMatching(hasCategory(category)))
                .collect(asIngredientList());
        return this;
    }

    public boolean isSuitableFor(Diet diet) {
        return ingredients()
                .noneMatch(isForbiddenIn(diet));
    }

    private static Predicate<Ingredient> isForbiddenIn(Diet diet) {
        return ingredient -> diet.forbids(ingredient.category());
    }

    private static Predicate<Ingredient> hasCategory(Category category) {
        return ingredient -> Objects.equals(ingredient.category(), category);
    }

    private static Function<Ingredient, Stream<Ingredient>> doubleIngredientsMatching(Predicate<Ingredient> shouldDouble) {
        return ingredient -> shouldDouble.test(ingredient)
                ? doubleIngredient(ingredient)
                : singleIngredient(ingredient);
    }

    private static Stream<Ingredient> singleIngredient(Ingredient ingredient) {
        return Stream.of(ingredient);
    }

    private static Stream<Ingredient> doubleIngredient(Ingredient ingredient) {
        return Stream.of(ingredient, ingredient);
    }

    private static Collector<Ingredient, ?, List<Ingredient>> asIngredientList() {
        return toCollection(LinkedList::new);
    }
}
