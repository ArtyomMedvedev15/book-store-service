package io.bookstore.controller;

import io.bookstore.domain.Director;
import io.bookstore.service.api.DirectorServiceApi;
import io.bookstore.util.Request.DirectorSaveRequest;
import io.bookstore.util.Request.DirectorUpdateRequest;
import io.bookstore.util.Response.DirectorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/director")
@Slf4j
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorServiceApi directorServiceApi;

    @GetMapping("/all")
    public ResponseEntity<?> getAllDirector() {
        var directorDtoList = directorServiceApi.getAll().stream().map(DirectorResponse::fromDomainToDto).toList();
        log.info("Get all director with endpoint in {}",new Date());
        return ResponseEntity.ok().body(directorDtoList);
    }

    @GetMapping("/{idDirector}")
    public ResponseEntity<?> getDirectorById(@PathVariable("idDirector") Long idDirector) {
        var directorById = directorServiceApi.getById(idDirector);
        if (directorById != null) {
            log.info("Get director with id {} with endpoint in {}",idDirector,new Date());
            return ResponseEntity.ok().body(DirectorResponse.fromDomainToDto(directorById));
        } else {
            log.error("Director with id {} not found with endpoint in {}",idDirector,new Date());
            return ResponseEntity.badRequest().body(String.format("Director with id %s not found", idDirector));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveDirector(@RequestBody DirectorSaveRequest directorSaveRequest) {
        var directorSave = directorServiceApi.saveDirector(DirectorSaveRequest.fromDtoToDomain(directorSaveRequest));
        if(directorSave!=null){
            log.info("Save new director with name {} with endpoint in {}",
                    String.format("%s %s",directorSaveRequest.getSonameDirector(),directorSaveRequest.getNameDirector()),new Date());
            return ResponseEntity.ok().body(DirectorResponse.fromDomainToDto(directorSave));
        }else{
            log.error("Cannot save new director, check connection to db in {}",new Date());
            return ResponseEntity.badRequest().body("Error on server side, try later");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDirector(@RequestBody DirectorUpdateRequest directorUpdateRequest) {
        var directorUpdate = directorServiceApi.updateDirector(DirectorUpdateRequest.fromDtoToDomain(directorUpdateRequest));
        if(directorUpdate!=null){
            log.info("Update director with id {} with endpoint in {}",directorUpdateRequest.getId(),new Date());
            return ResponseEntity.ok().body(DirectorResponse.fromDomainToDto(directorUpdate));
        }else{
            log.error("Cannot update director, check connection to db in {}",new Date());
            return ResponseEntity.badRequest().body("Error on server side, try later");
        }
    }

    @DeleteMapping("/delete/{idDirector}")
    public ResponseEntity<?> updateDirector(@PathVariable("idDirector")Long idDirector) {
        boolean delete_director_result = directorServiceApi.deleteDirector(idDirector);
        if(delete_director_result){
            log.info("Delete director with id {} with endpoint in {}",idDirector,new Date());
            return ResponseEntity.ok().body(String.format("Director with id %s success delete",idDirector));
        }else{
            log.error("Director with id {} doesn't exists in {}",idDirector,new Date());
            return ResponseEntity.badRequest().body(String.format("Director with id %s doens't exists",idDirector));
        }
    }

}
