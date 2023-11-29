package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
}
