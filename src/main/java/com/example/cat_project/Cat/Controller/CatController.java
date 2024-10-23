package com.example.cat_project.Cat.Controller;

import com.example.cat_project.Cat.DTO.CatFormDTO;
import com.example.cat_project.Cat.Entity.Cat;
import com.example.cat_project.Cat.Exception.CatNotFoundException;
import com.example.cat_project.Cat.Service.CatService;
import com.example.cat_project.Core.Controller.ApiController;
import com.example.cat_project.Core.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cats")
@Validated
public class CatController extends ApiController {

    private final CatService catService;

    @Autowired
    public CatController(
            CatService catService
    ) {
        this.catService = catService;
    }

    @GetMapping
    @Operation(summary = "Get all cats")
    public Page<Cat> getAllCats(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "limit",defaultValue = "5") Integer limit,
            @RequestParam(name = "order", defaultValue = "id") String order
    ) {
        Sort sort = null;
        if (order != null && !order.isEmpty()) {
            sort = this.getSort(order);
        }
        Pageable pageable = (sort == null) ?
                PageRequest.of(page, limit) :
                PageRequest.of(page, limit, sort);

        return this.catService.getAllCats(pageable);
    }

    @Operation(summary = "Get a single cat by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cat", content = {
                    @Content(schema = @Schema(implementation = Cat.class))
            }),
            @ApiResponse(responseCode = "404", description = "Cat not found", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getCat(@PathVariable Long id) {
        try {
            Cat cat = this.catService.getCatById(id);
            return ResponseEntity.ok(cat);
        }
        catch (CatNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping
    @Operation(summary = "Create a cat", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully created a cat", content = {
                    @Content(schema = @Schema(implementation = Cat.class))
            }),
            @ApiResponse(responseCode = "400", description = "Issues in form", content = {
                    @Content(mediaType = "application/json", schema = @Schema(
                            example = "{ \"name\": \"Name cannot be empty\" }"
                    ))
            })
    })
    public ResponseEntity<?> createCat(@Valid @RequestBody CatFormDTO catDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Cat cat = this.catService.saveFromDTOToEntity(catDTO, new Cat());
        return ResponseEntity.ok(cat);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit a cat by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a cat", content = {
                    @Content(schema = @Schema(implementation = Cat.class))
            }),
            @ApiResponse(responseCode = "400", description = "Issues in form", content = {
                    @Content(mediaType = "application/json", schema = @Schema(
                            example = "{ \"name\": \"Name cannot be empty\" }"
                    ))
            }),
            @ApiResponse(responseCode = "404", description = "Cat not found", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public ResponseEntity<?> updateCat(
            @PathVariable Long id,
            @Valid @RequestBody CatFormDTO catDTO,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                System.out.println(result.getAllErrors());
                return ResponseEntity.badRequest().body(result.getAllErrors());
            }
            Cat cat = this.catService.getCatById(id);
            this.catService.saveFromDTOToEntity(catDTO, cat);
            return ResponseEntity.ok(cat);
        }
        catch (CatNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cat by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted a cat", content = {
                    @Content(mediaType = "application/json", schema = @Schema(
                            example = "Successfully deleted cat"
                    ))
            }),
            @ApiResponse(responseCode = "404", description = "Cat not found", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public ResponseEntity<?> deleteCat(@PathVariable Long id) {
        try {
            this.catService.deleteCatById(id);
            return ResponseEntity.ok("Successfully deleted");
        }
        catch (CatNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all cats", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted all cat", content = {
                    @Content(mediaType = "application/json", schema = @Schema(
                            example = "Successfully deleted all cats"
                    ))
            })
    })
    public ResponseEntity<?> deleteAllCats() {
        this.catService.deleteAllCats();
        return ResponseEntity.ok("Successfully deleted all cats");
    }

}
