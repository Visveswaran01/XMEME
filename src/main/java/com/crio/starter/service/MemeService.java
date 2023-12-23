package com.crio.starter.service;

import lombok.RequiredArgsConstructor;


import java.util.List;
import java.util.Optional;
import com.crio.starter.Exception.DuplicatePostException;
import com.crio.starter.Exception.MemeNotFoundException;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.GetParticularMemeResponse;
import com.crio.starter.exchange.PostMemeResponse;
import com.crio.starter.repositoryservices.MemeRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemeService {

    @Autowired
    private MemeRepositoryService memeRepositoryService;


    public PostMemeResponse postMeme(String name ,String caption,String url ) {

        PostMemeResponse postMemeResponse = new PostMemeResponse();

        //call storeMeme to store in DB
        String id = memeRepositoryService.storeMeme(name,caption,url);

       
        Optional<Meme> meme = memeRepositoryService.findMemeById(id);

        
        if(meme.isPresent()) {
            postMemeResponse.setId(meme.get().getMemeid());
        } else {

            throw new DuplicatePostException();
        }
        
        return postMemeResponse;
    }

    public GetParticularMemeResponse findMeme(String id) {

        GetParticularMemeResponse  getResponse = new GetParticularMemeResponse();

        Optional<Meme> meme = memeRepositoryService.findMemeById(id);

        if(meme.isPresent()) {
            Meme gMeme = meme.get();
            getResponse.setId(gMeme.getMemeid());
            getResponse.setName(gMeme.getPersonName());
            getResponse.setCaption(gMeme.getMemeCaption());
            getResponse.setUrl(gMeme.getImageUrl());
        } else {
            throw new MemeNotFoundException();
        }

        return getResponse;
    }
    
    public List<Meme> getMemes() {

        List<Meme> memes = memeRepositoryService.getAllMemes();
        if(memes.isEmpty()) {
            return memes;
        }
        
        int memeCount = memes.size();
        
        if(memeCount > 100) {
            memes = memes.subList(memeCount-100,memeCount);
        } 
        
        return memes;  
    }
    
}