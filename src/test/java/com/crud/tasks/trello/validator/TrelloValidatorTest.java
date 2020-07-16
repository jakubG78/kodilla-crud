package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void shouldValidateEmptyListOfBoards() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();

        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertNotNull(filteredBoards);
        assertEquals(0, filteredBoards.size());
    }

    @Test
    public void shouldValidateBoards() {
        //Given
        TrelloList trelloList1 = new TrelloList("32", "test list 1", true);
        TrelloList trelloList2 = new TrelloList("33", "Teest list 2", false);
        TrelloList trelloList3 = new TrelloList("33", "Other list 3", false);
        List<TrelloList> trelloLists1 = new ArrayList<>();
        trelloLists1.add(trelloList1);
        trelloLists1.add(trelloList2);
        trelloLists1.add(trelloList3);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "Test", trelloLists1));
        trelloBoards.add(new TrelloBoard("2", "Board for tests", trelloLists1));
        trelloBoards.add(new TrelloBoard("3", "Other board", trelloLists1));
        trelloBoards.add(new TrelloBoard("4", "Another board", trelloLists1));

        //When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertNotNull(filteredBoards);
        assertEquals(3, filteredBoards.size());
    }
}