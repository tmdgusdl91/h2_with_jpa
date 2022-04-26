package com.h2.jpa.study.service;

import com.h2.jpa.study.domain.Board;
import com.h2.jpa.study.domain.CreateBoardDto;
import com.h2.jpa.study.domain.ResponseBoardDto;
import com.h2.jpa.study.domain.UpdateBoardDto;
import com.h2.jpa.study.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public Long createBoard(CreateBoardDto createBoardDto) {
        Board board = Board.builder()
                .title(createBoardDto.getTitle())
                .content(createBoardDto.getContent())
                .build();

        Long boardId = boardRepository.save(board).getId();
        return boardId;
    }

    @Transactional(readOnly = true)
    public ResponseBoardDto readBoard(Long boardId) throws IllegalAccessException {
        Board board = boardRepository.findById(boardId).orElseThrow(IllegalAccessException::new);
        return ResponseBoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    @Transactional
    public Board updateBoard(UpdateBoardDto updateBoardDto) throws IllegalAccessException {
        Board board = boardRepository.findById(updateBoardDto.getId()).orElseThrow(IllegalAccessException::new);
        board.setTitle(updateBoardDto.getTitle());
        board.setContent(updateBoardDto.getContent());
        return board;
    }

    @Transactional
    public Long deleteBoard(Long boardId) throws IllegalAccessException {
        Board board = boardRepository.findById(boardId).orElseThrow(IllegalAccessException::new);
        boardRepository.delete(board);
        return board.getId();
    }
}
