package com.lotusverify.lotusapp.service;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TextAnalyticsService {

    @Autowired
    private TextAnalyticsClient textAnalyticsClient;

    public List<Map<String, String>> extractEntities(String text) {
        List<Map<String, String>> entitiesList = new ArrayList<>();
        textAnalyticsClient.recognizeEntities(text).forEach(entity -> {
            Map<String, String> entityData = new HashMap<>();
            entityData.put("Entity", entity.getText());
            entityData.put("Category", String.valueOf(entity.getCategory()));
            entitiesList.add(entityData);
        });
        return entitiesList;
    }

    public List<String> extractKeyPhrases(String text) {
        List<String> keyPhrases = new ArrayList<>();
        textAnalyticsClient.extractKeyPhrases(text).forEach(keyPhrases::add);
        return keyPhrases;
    }

    public Map<String, Object> extractEntitiesAndKeyPhrases(String text) {
        Map<String, Object> result = new HashMap<>();
        result.put("Entities", extractEntities(text));
        result.put("KeyPhrases", extractKeyPhrases(text));
        return result;
    }
}
