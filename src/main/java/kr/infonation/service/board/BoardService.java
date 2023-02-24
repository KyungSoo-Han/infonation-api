package kr.infonation.service.board;

import kr.infonation.domain.board.Board;
import kr.infonation.domain.users.Users;
import kr.infonation.dto.board.BoardDto;
import kr.infonation.repository.board.BoardRepository;
import kr.infonation.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UsersService usersService;

    public Optional<Board> findById(Long id){
       return boardRepository.findById(id);
    }

    @Transactional
    public Board createBoard (BoardDto.CreateRequest createRequest){
        Users user = usersService.getReferenceById(createRequest.getUserId());

        return boardRepository.save(createRequest.toEntity(user));
    }

    @Transactional
    public Board updateBoard(Long id, BoardDto.UpdateRequest request){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        board.update(request);

        return board;
    }

    //public List<BoardDto.Response> findBoardList(){
    //    List<BoardDto.Response> boardDtos = boardRepository.findBoard();
    //    return boardDtos;
    //}
}
