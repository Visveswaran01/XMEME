package com.crio.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Objects;

import com.crio.starter.Exception.SequenceException;
import com.crio.starter.model.AutoIncrementer;

//This class is used for Generating sequence for memeid in MemeEntity

@Service
@RequiredArgsConstructor
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperation;

    public long getSequenceNumber(String seqId) {
      
      //get sequence id
      Query query = new Query(Criteria.where("_id").is(seqId));
      
      //update the seqNo by 1
      Update update = new Update().inc("seqNo", 1);

      //return new increased id
      FindAndModifyOptions options = new FindAndModifyOptions();
      options.returnNew(true).upsert(true);

      //modify the collection 'counter'
      AutoIncrementer sequnceGenerated = mongoOperation.findAndModify(query, update, options, 
                                                  AutoIncrementer.class);
                                                  
                                                  
      //if sequnceGenerated is null return 1 
      long seqNo = !Objects.isNull(sequnceGenerated) ? sequnceGenerated.getSeqNo() : 1;
      
       return seqNo;
    }
    
}