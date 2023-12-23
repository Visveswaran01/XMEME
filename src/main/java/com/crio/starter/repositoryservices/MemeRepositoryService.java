package com.crio.starter.repositoryservices;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.crio.starter.model.MemeEntity;
import lombok.RequiredArgsConstructor;
import com.crio.starter.repository.MemeRepository;
import com.crio.starter.service.SequenceGeneratorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.starter.Exception.NullPostException;
import com.crio.starter.dto.Meme;

@Service
@RequiredArgsConstructor
public class MemeRepositoryService {
    
    @Autowired
    private SequenceGeneratorService sequenceService;

    @Autowired
    private MongoTemplate mongotemplate;

    @Autowired
    private final MemeRepository memeRepository;

    public String storeMeme(String name ,String caption, String url) {
      
     if(name == null || url == null || caption == null) {
       throw new NullPostException();
     }
     
      //filter condition 
      Criteria criteria = new Criteria();
      criteria.andOperator(Criteria.where("personName").is(name)
            ,Criteria.where("memeCaption").is(caption),Criteria.where("imageUrl").is(url));

      //add filter condition to query
      Query query = new Query();
      query.addCriteria(criteria);

      //extract the result of query
      MemeEntity memeEntityExist = mongotemplate.findOne(query, MemeEntity.class);

      if(memeEntityExist != null) {
        return null;
      }
      
      //create new Meme Entity
      MemeEntity memeEntity = new MemeEntity();

      //get and set id for memeEntity
      long id = sequenceService.getSequenceNumber(MemeEntity.SEQUENCE_NAME);
      String mId = String.valueOf(id);
      memeEntity.setMemeid(mId);
      
      //set other attributes of meme Entity
      memeEntity.setPersonName(name);
      memeEntity.setMemeCaption(caption);
      memeEntity.setImageUrl(url);

      //save the created meme
      memeEntity = memeRepository.insert(memeEntity);

      return memeEntity.getMemeid();
    }
  
    public Optional<Meme> findMemeById(String memeId) {

        Meme meme = null;
        //extract meme
        MemeEntity memeEntity = memeRepository.findBymemeid(memeId);
      
        if(memeEntity != null)
        {
          ModelMapper modelMapper = new ModelMapper();
          meme = modelMapper.map(memeEntity,Meme.class);
        }
      
        //the result of meme may or may not be null
        Optional<Meme> foundMeme = Optional.ofNullable(meme);

        return foundMeme;
    } 

    public List<Meme> getAllMemes() {

      List<Meme> getmemes = new ArrayList<>();

      List<MemeEntity> memeEntity = memeRepository.findAll();
      
      for(MemeEntity meme : memeEntity){
        ModelMapper modelMapper = new ModelMapper();
        Meme getMeme = modelMapper.map(meme,Meme.class);
        getmemes.add(getMeme);
      }
      
      return getmemes;

    }
    
}