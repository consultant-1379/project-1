package com.ericsson.gladiators.itemsservice.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ItemEntityTests {

    @ParameterizedTest
    @MethodSource("idDescriptionCategoryBoardIDProvider")
    void testValuesOfItemsConstructorInitiated(String description, String category, int boardID, int creatorId) {
        ItemEntity item = new ItemEntity(description, category, boardID, creatorId);
        assertAll(
                () -> assertThat(item.getId(), is(notNullValue())),
                () -> assertThat(item.getDescription(), equalTo(description)),
                () -> assertThat(item.getCategory(), equalTo(category)),
                () -> assertThat(item.getBoardId(), equalTo(boardID)),
                () -> assertThat(item.getVote(), equalTo(0)),
                () -> assertThat(item.getCreatorId(), equalTo(creatorId)),
                () -> assertThat(item.toString(), either(containsString("backend"))
                        .or(containsString("breaking"))
                        .or(containsString("threading")))
        );
    }

    static Stream<Arguments> idDescriptionCategoryBoardIDProvider() {
        return Stream.of(
                Arguments.of("Nice work on the backend.", "glad", 123, 1 ),
                Arguments.of("Functional tests keep breaking", "sad", 1234, 2 ),
                Arguments.of("Improve speed by threading", "mad", 1236, 3 )
        );
    }

}
