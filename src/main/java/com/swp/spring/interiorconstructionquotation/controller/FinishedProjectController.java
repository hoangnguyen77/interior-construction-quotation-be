package com.swp.spring.interiorconstructionquotation.controller;

import com.swp.spring.interiorconstructionquotation.service.finished.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finished")
public class FinishedProjectController {
    private FinishedProjectService finishedProjectService;
    private IFinishedProjectService iFinishedService;

    public FinishedProjectController(FinishedProjectService finishedProjectService, IFinishedProjectService iFinishedService) {
        this.finishedProjectService = finishedProjectService;
        this.iFinishedService = iFinishedService;
    }

    @GetMapping("/cancel")
    public List<CancelListRequest> getAllCancelList(@RequestParam(value = "keyword") String keyword) {
        return finishedProjectService.findAllCancelList(keyword);
    }

    @GetMapping("/quotation")
    public Page<QuotationDoneRequest> getQuotationsByStatusAndConstruction(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam("isConstructed") boolean isConstructed,
            @PageableDefault(size = 5) Pageable pageable) {
        return finishedProjectService.findAllByStatusAndConstruction(keyword, isConstructed, pageable);
    }
    @GetMapping("/quotation-without-construct")
    public Page<QuotationDoneRequest> getQuotationsByStatus(
            @RequestParam(value = "keyword", required = false) String keyword,
            @PageableDefault(size = 5) Pageable pageable) {
        return finishedProjectService.findAllByStatus(keyword, pageable);
    }

    @PutMapping("/construct/{headerId}")
    public ResponseEntity<?> changeIsConstructedQuotationList(@PathVariable int headerId){
        try{
            return finishedProjectService.updateIsConstruction(headerId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Fail");
        }
    }

    @PostMapping("/create-project")
    public ResponseEntity<?> createProject (@RequestBody FinishedProjectRequest finishedProjectRequest){
        try{
            return iFinishedService.createFinishedProject(finishedProjectRequest);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Fail");
        }
    }

    @PutMapping("/update-project")
    public ResponseEntity<?> updateProject (@RequestBody FinishedProjectRequest finishedProjectRequest){
        try{
            return iFinishedService.updateFinishedProject(finishedProjectRequest);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Fail");
        }
    }
    @GetMapping("/has-finished-project/{listId}")
    public ResponseEntity<Boolean> hasFinishedProject(@PathVariable int listId) {
        try {
            boolean hasFinishedProject = iFinishedService.hasFinishedProject(listId);
            return ResponseEntity.ok(hasFinishedProject);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(false);
        }
    }
}
