package com.system;

import java.io.File;
import java.io.IOException;
import java.util.List;
public class RecommendationEngine {
	public static <DataModel> void main(String[] args) throws Exception {
        // Load user-item ratings
        DataModel model = new FileDataModel(new File("recommendation-system/pom.xml"));

        // Compute user similarity
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // Define user neighborhood
        UserNeighborhood neighborhood = new org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood(0.1, similarity, model);

        // Build recommender
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        // Get recommendations for user 1
        List<RecommendedItem> recommendations = recommender.recommend(1, 2);

        // Display recommendations
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Recommended Item ID: " + recommendation.getItemID() + ", Predicted Rating: " + recommendation.getValue());
        }
    }

}
