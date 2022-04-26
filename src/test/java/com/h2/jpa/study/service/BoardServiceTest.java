package com.h2.jpa.study.service;

import com.h2.jpa.study.domain.Board;
import com.h2.jpa.study.domain.CreateBoardDto;
import com.h2.jpa.study.domain.ResponseBoardDto;
import com.h2.jpa.study.domain.UpdateBoardDto;
import com.h2.jpa.study.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    static Board board = Board.builder().id(1L).title("title").content("content").build();

    static Board updateBoard = Board.builder().id(2L).title("update_title").content("update_content").build();

    @Test
    void createBoard() {
        //boardRepository save 메소드에 아무값이나 오면 board 를 리턴 해준다.
        when(boardRepository.save(any())).thenReturn(board);

        //boardRepository 저장하기 위한 객체 생성
        CreateBoardDto createBoardDto = CreateBoardDto.builder().title("title").content("content").build();

        //메소드를 수행하면 boardRepository.save() 메소드가 실행되고 when thenReturn 으로 정의한 board 가 리턴된다 그중 id 값만 따로 가져온다.
        Long boardId = boardService.createBoard(createBoardDto);

        //메소드 실행으로 얻은 값과 기존의 값을 비교하여 맞는지 확인
        assertEquals(boardId, board.getId());
    }

    @Test
    void readBoard() throws IllegalAccessException {

        //boardRepository findById 메소드에 1이라는 값이 들어오면 board 를 리턴 해준다.
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        //메소드를 수행하면 boardRepository.findById() 메소드가 실행되고 when thenReturn 으로 정의한 board 가 리턴된다.
        ResponseBoardDto readBoard = boardService.readBoard(1L);

        //Service 로 반환된 dto 의 title 과 정의한 board 의 title 값이 같은지 확인
        assertEquals(readBoard.getTitle(), board.getTitle());
    }

    @Test
    void updateBoard() throws IllegalAccessException {

        // 2L 이라는 값을 넣으면 updateBoard entity 를 리턴해줌
        when(boardRepository.findById(2L)).thenReturn(Optional.of(updateBoard));

        // updateBoard 메소드를 테스트하기 위해 dto 를 생성함
        UpdateBoardDto updateBoardDto = UpdateBoardDto.builder().id(2L).title("update_title2").content("update_content2").build();

        // boardService.updateBoard() 실행.
        Board returnUpdateBoardDto = boardService.updateBoard(updateBoardDto);

        // 결과값 비교
        assertEquals(returnUpdateBoardDto.getTitle(), updateBoardDto.getTitle());
    }

    @Test
    void deleteBoard() throws IllegalAccessException {

        // 1L을 넣었을때 board 를 return 하게함.
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        // doNothing()을 사용하여 반환값이 void 인 메소드를 정의.
        doNothing().when(boardRepository).delete(board);

        // boardService 의 deleteBoard() 메소드를 실행하여 결과 id 값을 받음.
        Long deleteBoardId = boardService.deleteBoard(1L);

        // findById() 에서 return 받은 값과, deleteBoard()에서 반환되 값을 비교
        assertEquals(board.getId(), deleteBoardId);

    }
}