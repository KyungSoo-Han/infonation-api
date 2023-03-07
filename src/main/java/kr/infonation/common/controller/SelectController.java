package kr.infonation.common.controller;

import kr.infonation.common.dto.SelectDto;
import kr.infonation.common.service.SelectService;
import kr.infonation.config.CustomException;
import kr.infonation.service.cust.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/select")
public class SelectController {

    private final SelectService selectService;

    @GetMapping("/{gbn}")
    public List<SelectDto> selectCustomerData (@PathVariable String gbn ,@RequestParam(required = false) Long parentId , @RequestParam(required = false) Long codeId
                                                                        , @RequestParam(required = false) String codeName) throws CustomException {
        System.out.println("gbn = " + gbn);
        System.out.println("codeId = " + codeId);
        System.out.println("codeName = " + codeName);
        return selectService.selectTableData(gbn, parentId, codeId, codeName);
    }

}
