package kr.infonation.repository.board;

import kr.infonation.domain.board.Board;
import kr.infonation.dto.board.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //@Query(value = "select new kr.infonation.dto.board.BoardDto.Response(b) from Board b join b.users m order by b.id desc ")
    //List<BoardDto.Response> findBoard();
}
