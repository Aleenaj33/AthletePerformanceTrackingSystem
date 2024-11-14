package com.ustcapstone.trainingsession.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustcapstone.trainingsession.model.TrainingSession;
import com.ustcapstone.trainingsession.repository.TrainingSessionRepository;

import java.util.List;

@Service
public class TrainingSessionService {

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;
    
    private int sessionId = 1; // Initial session ID, could be persisted in DB for production

    // Method to get the current sessionId and increment it
    public synchronized int getNextSessionId() {
        return sessionId++; // Atomically return and increment sessionId
    }
    
    public TrainingSession createTrainingSession(TrainingSession session) {
        return trainingSessionRepository.save(session);
    }

    public TrainingSession updateTrainingSession(TrainingSession session) {
        return trainingSessionRepository.save(session);
    }
    public List<TrainingSession> getTrainingSessionsByPlayerId(int playerId) {
        return trainingSessionRepository.findByPlayerIdsContaining(playerId);
    }

    public List<TrainingSession> getTrainingSessionsByCoachId(int coachId) {
        return trainingSessionRepository.findByCoachId(coachId);
    }
    public void deleteTrainingSession(int sessionId) {
        trainingSessionRepository.deleteById(sessionId);
    }
}
