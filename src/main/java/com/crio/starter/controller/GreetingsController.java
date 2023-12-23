package com.crio.starter.controller;

import com.crio.starter.Exception.NullPostException;
import com.crio.starter.dto.EmptyJsonBody;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.GetAllMemeRequest;
import com.crio.starter.exchange.GetAllMemeResponse;
import com.crio.starter.exchange.GetParticularMemeRequest;
import com.crio.starter.exchange.GetParticularMemeResponse;
import com.crio.starter.exchange.PostMemeRequest;
import com.crio.starter.exchange.PostMemeResponse;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import com.crio.starter.service.MemeService;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsController {

  private final GreetingsService greetingsService;

  private final MemeService memeService;
  
  public static final String endPoint1 = "/memes/";
  public static final String endPoint2 = "/memes/{id}";


  @GetMapping("/say-hello")
  public ResponseDto sayHello(@RequestParam String messageId) {
    return greetingsService.getMessage(messageId);
  }

  @PostMapping(value = endPoint1 ,headers = {"content-type=application/json" },consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PostMemeResponse> setPost(@Valid @RequestBody PostMemeRequest postReq) {

    //extract data from 
    String name = postReq.getName();
    String caption = postReq.getCaption();
    String url = postReq.getUrl();
    
    //data is null throw exception
    if(name == null || url == null || caption == null){
      throw new NullPostException();
    }
    
    //if data is not null proceed to store data
    PostMemeResponse postMemeResponse = memeService.postMeme(name,caption,url);

    return new ResponseEntity<>(postMemeResponse,HttpStatus.OK);

  }

  @GetMapping(endPoint1)
  public ResponseEntity getAllMemes(GetAllMemeRequest getAllMemeRequest) {

    List<GetAllMemeResponse> getAllMemeResponse = new ArrayList<>();
     List<Meme> memes = memeService.getMemes(); 

     if(memes.isEmpty()){
      return new ResponseEntity<>(new EmptyJsonBody(),HttpStatus.OK);
     }

     //reverse the collection to pass last test case
     Collections.reverse(memes);
     
     for(Meme meme : memes) {

      GetAllMemeResponse singleResponse = new GetAllMemeResponse();

      singleResponse.setId(meme.getMemeid());
      singleResponse.setName(meme.getPersonName());
      singleResponse.setCaption(meme.getMemeCaption());
      singleResponse.setUrl(meme.getImageUrl());
      
      getAllMemeResponse.add(singleResponse);

     }

     return new ResponseEntity<>(getAllMemeResponse,HttpStatus.OK);
  }

  @GetMapping(endPoint2)
  public ResponseEntity<GetParticularMemeResponse> getThatMeme(GetParticularMemeRequest 
     getParticularMemeRequest) {
       
        GetParticularMemeResponse getParticularMemeResponse  =  
                          memeService.findMeme(getParticularMemeRequest.getId());
        
      return new ResponseEntity<>(getParticularMemeResponse,HttpStatus.OK);

  }

}
