package kr.infonation.controller.board;

import io.swagger.annotations.ApiOperation;
import kr.infonation.common.dto.ResponseDto;
import kr.infonation.domain.board.Board;
import kr.infonation.dto.board.BoardDto;
import kr.infonation.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping
    public ResponseDto<BoardDto.CreateResponse> createBoard(@RequestBody BoardDto.CreateRequest createRequest){

        Board board = boardService.createBoard(createRequest);

        BoardDto.CreateResponse createResponse = new BoardDto.CreateResponse(board);

        return ResponseDto.SuccessResponse(createResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseDto<BoardDto.UpdateResponse> updateBoard(@PathVariable Long id, @RequestBody BoardDto.UpdateRequest request) {
        Board board = boardService.updateBoard(id, request);

        BoardDto.UpdateResponse response = new BoardDto.UpdateResponse(board);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

   //@ApiOperation(value = "게시판 리스트 조회")
   //@GetMapping
   //public ResponseDto<List<BoardDto.Response>> boardList(){
   //    List<BoardDto.Response> boards = boardService.findBoardList();
   //    return ResponseDto.SuccessResponse(boards, HttpStatus.OK);
   //}
/*    @GetMapping
    public ResponseEntity hello(){
        String test = "TEST";
        return new ResponseEntity(test, HttpStatus.OK);
    }*/
}
