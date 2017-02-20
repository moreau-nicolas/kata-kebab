package com.github.moreaunicolas.katas.kebab;

import org.junit.Test;

import static com.github.moreaunicolas.katas.kebab.Categories.*;
import static com.github.moreaunicolas.katas.kebab.Ingredients.*;
import static com.github.moreaunicolas.katas.kebab.Diets.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KebabTests {

    @Test
    public void can_prepare_a_kebab_without_ingredients() {
        Kebab kebab = Kebab.prepare();

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .isEmpty();
    }

    @Test
    public void can_prepare_a_kebab_with_a_single_ingredient() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(LETTUCE);

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .containsExactly(LETTUCE);
    }

    @Test
    public void can_prepare_a_kebab_with_several_ingredients() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(ONION)
                .addLayerOf(LETTUCE);

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .containsExactlyInAnyOrder(LETTUCE, ONION);
    }

    @Test
    public void ingredient_order_is_important() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(ONION)
                .addLayerOf(LETTUCE);

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .containsExactly(ONION, LETTUCE);
    }

    @Test
    public void can_remove_onions() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(TOMATO)
                .addLayerOf(ONION)
                .addLayerOf(LETTUCE)
                .removeAllOf(ONION);

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .containsExactly(TOMATO, LETTUCE);
    }

    @Test
    public void removing_an_ingredient_remove_all_occurrences() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(ONION)
                .addLayerOf(TOMATO)
                .addLayerOf(ONION)
                .addLayerOf(LETTUCE)
                .removeAllOf(ONION);

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .containsExactly(TOMATO, LETTUCE);
    }

    @Test
    public void can_double_cheese() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(CHEDDAR)
                .doubleAll(CHEESE);

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .containsExactly(CHEDDAR, CHEDDAR);
    }

    @Test
    public void can_double_cheese_when_empty() {
        Kebab kebab = Kebab.prepare()
                .doubleAll(CHEESE);

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .isEmpty();
    }

    @Test
    public void can_double_cheese_when_no_cheese() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(LETTUCE)
                .doubleAll(CHEESE);

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .containsExactly(LETTUCE);
    }

    @Test
    public void double_all_cheese_occurrences() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(LETTUCE)
                .addLayerOf(CHEDDAR)
                .addLayerOf(TOMATO)
                .addLayerOf(CHEDDAR)
                .doubleAll(CHEESE);

        assertThat(kebab.ingredients()).as("Kebab ingredients")
                .containsExactly(LETTUCE, CHEDDAR, CHEDDAR, TOMATO, CHEDDAR, CHEDDAR);
    }

    @Test
    public void a_vegetable_only_kebab_is_suitable_for_a_vegetarian_diet() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(LETTUCE)
                .addLayerOf(TOMATO)
                .addLayerOf(ONION);

        assertThat(kebab.isSuitableFor(VEGETARIAN))
                .withFailMessage("A kebab with vegetables only should be suitable for a vegetarian diet")
                .isTrue();
    }

    @Test
    public void a_meat_kebab_is_not_suitable_for_a_vegetarian_diet() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(LAMB);

        assertThat(kebab.isSuitableFor(VEGETARIAN))
                .withFailMessage("A kebab with meat should not be suitable for a vegetarian diet")
                .isFalse();
    }

    @Test
    public void a_cod_kebab_is_suitable_for_a_pescetarian_diet() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(COD);

        assertThat(kebab.isSuitableFor(PESCETARIAN))
                .withFailMessage("A kebab with fish should be suitable for a pescetarian diet")
                .isTrue();
    }

    @Test
    public void a_meat_kebab_is_not_suitable_for_a_pescetarian_diet() {
        Kebab kebab = Kebab.prepare()
                .addLayerOf(LAMB);

        assertThat(kebab.isSuitableFor(PESCETARIAN))
                .withFailMessage("A kebab with meat should not be suitable for a pescetarian diet")
                .isFalse();
    }
}
