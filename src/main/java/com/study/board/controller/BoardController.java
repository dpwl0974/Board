package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;  //boardservice가 뭔지 알려줌 -> pro에서 사용 가능

    @GetMapping("/board/write")
    public String boardwriteForm() {
        return "boardwrite";  //어떤 html 보낼건지 입력
    }

    @PostMapping("/board/writepro")
    public String boardwritePro(Board board, Model model) {

        boardService.write(board);

        /*모델에 담겨서 이동*/
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardlist(Model model) {

        model.addAttribute("list", boardService.boardlist());

        return "boardlist";

    }

    @GetMapping("/board/view")  //localhost:8080/board/view?id=1  => get 방식 id에 1
    public String boardView(Model model, Integer id) {  //다시 넘길 때 매개변수에 model

        model.addAttribute("board", boardService.boardview(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id, Model model) {

        boardService.boardDelete(id);
        model.addAttribute("message", "글 삭제 완료");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
        /*return "redirect:/board/list";*/
    }

    @GetMapping("/board/modify/{id}")  //인식된id가 id로 들어감
    public String boardModify(@PathVariable("id") Integer id, Model model) {  //pathvariable 사용시 url이 깔끔해짐 ex)view/8 (id)

        model.addAttribute("board", boardService.boardview(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id,Board board, Model model){

        Board boardTemp = boardService.boardview(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";


        /*return "redirect:/board/list";*/
    }
}
