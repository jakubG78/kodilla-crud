package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("23", "Test list 1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("24", "Test list 2", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "Test1", trelloListsDto));
        //When
        List<TrelloBoard> mappedtBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        Assert.assertEquals(1, mappedtBoards.size());
        Assert.assertEquals(2, mappedtBoards.get(0).getLists().size());
        Assert.assertEquals("Test list 2", mappedtBoards.get(0).getLists().get(1).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("32", "Test list 1", true);
        TrelloList trelloList2 = new TrelloList("33", "Test list 2", false);
        TrelloList trelloList3 = new TrelloList("33", "Test list 3", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "Test1", trelloLists));
        //When
        List<TrelloBoardDto> mappedBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        Assert.assertEquals(1, mappedBoardsDto.size());
        Assert.assertEquals(3, mappedBoardsDto.get(0).getLists().size());
        Assert.assertEquals(false, mappedBoardsDto.get(0).getLists().get(2).isClosed());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test card 1", "Card for tests", "23", "32");

        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertNotNull(mappedCard);
        Assert.assertEquals("32", mappedCard.getListId());
    }

    @Test
    public void testMapToCarDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test card 2", "Card for tests", "44", "63");

        //When
        TrelloCardDto mappedCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertNotNull(mappedCardDto);
        Assert.assertEquals("44", mappedCardDto.getPos());
    }

}
