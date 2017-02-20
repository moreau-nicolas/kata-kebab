package com.github.moreaunicolas.katas.kebab;

import org.junit.Test;

import static com.github.moreaunicolas.katas.kebab.Categories.FISH;
import static com.github.moreaunicolas.katas.kebab.Categories.MEAT;
import static org.assertj.core.api.Assertions.assertThat;

public class DietTests {

    @Test
    public void a_restricting_diet_enforces_its_restrictions() {
        assertThat(Diets.PESCETARIAN.forbids(MEAT))
                .withFailMessage("Meat should be forbidden in a pescetarian diet")
            .isTrue();
    }

    @Test
    public void a_restricting_diet_allows_non_restricted_ingredients() {
        assertThat(Diets.PESCETARIAN.forbids(FISH))
                .withFailMessage("Fish should not be forbidden in a pescetarian diet")
            .isFalse();
    }
}
