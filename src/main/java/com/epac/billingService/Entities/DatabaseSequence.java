package com.epac.billingService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
@Document(collection = "database_sequences")
@Component

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabaseSequence {
         @Id
        private String id;
        private int seqVal;

        public DatabaseSequence() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    public int getSeqVal() {
        return seqVal;
    }
    public void setSeqVal(int seqVal) {
        this.seqVal = seqVal;
    }
}


