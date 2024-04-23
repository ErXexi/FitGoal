package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.DTO.Session.DtoSession;
import com.es.iesmz.FitGoal.DTO.Session.DtoSessionAddExercice;
import com.es.iesmz.FitGoal.DTO.Session.DtoSessionResponse;
import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.domain.Session;
import com.es.iesmz.FitGoal.domain.User;
import com.es.iesmz.FitGoal.repository.ExerciceRepository;
import com.es.iesmz.FitGoal.repository.SessionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciceRepository exerciceRepository;
    @Autowired
    private ModelMapper modelMapper;

    //region Repos
    @Override
    public Set<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Optional<Session> findById(Long id) {
        return sessionRepository.findById(id);
    }

    @Override
    public Set<Session> findByCreatorId(Long id) {
        return sessionRepository.findByCreatorId(id);
    }

    @Override
    public DtoSessionResponse addSession(DtoSession data, Long userId) {
        DtoSessionResponse response = new DtoSessionResponse();
        response.setSuccess(false);
        Optional<User> user = userService.findById(userId);
        if(user.isEmpty()){
            response.setMessage("User not found");
            return response;
        }
        Session newSession = new Session();
        newSession.setCreator(user.get());
        modelMapper.map(data, newSession);
        sessionRepository.save(newSession);
        response.setSuccess(true);
        response.setMessage("Session created successfully");
        return response;
    }

    @Override
    @Transactional
    public DtoSessionResponse addExerciceToSession(DtoSessionAddExercice data) {
        DtoSessionResponse response = new DtoSessionResponse();
        response.setSuccess(false);

        Optional<Session> optionalSession = sessionRepository.findById(data.getSessionId());
        if (optionalSession.isEmpty()) {
            response.setMessage("Session not found");
            return response;
        }

        Session session = optionalSession.get();
        Optional<Exercice> optionalExercice = exerciceRepository.findById(data.getExerciceId());
        if(optionalExercice.isEmpty()){
            response.setMessage("Exercice not found");
            return response;
        }

        if(checkExerciceInSession(data)){
            response.setMessage("Exercice in session already");
            return response;
        }

        Exercice exercice = optionalExercice.get();
        session.getExercices().add(exercice);
        sessionRepository.save(session);
        response.setMessage("Exercice added to session");
        response.setSuccess(true);

        return response;
    }


    @Override
    public DtoSessionResponse modifySession(Long id, DtoSession data) {
        DtoSessionResponse response = new DtoSessionResponse();
        response.setSuccess(false);

        Optional<Session> optionalSession = sessionRepository.findById(id);
        if (optionalSession.isEmpty()) {
            response.setMessage("Session not found");
            return response;
        }

        Session sessionToUpdate = optionalSession.get();
        modelMapper.map(data, sessionToUpdate);

        sessionRepository.save(sessionToUpdate);
        response.setSuccess(true);
        response.setMessage("Session updated successfully");
        return response;
    }

    @Override
    public DtoSessionResponse deleteSession(Long id) {
        DtoSessionResponse response = new DtoSessionResponse();
        response.setSuccess(false);
        if(sessionRepository.findById(id).isEmpty()){
            response.setMessage("Session not found");
            return response;
        }
        sessionRepository.deleteById(id);
        response.setSuccess(true);
        response.setMessage("Session deleted successfully");
        return response;
    }
    //endregion

    //Region PrivateMethods
    public boolean checkExerciceInSession(DtoSessionAddExercice data){
        Session session = sessionRepository.findById(data.sessionId).get();
        Exercice exercice = exerciceRepository.findById(data.exerciceId).get();
        List<Exercice> exerciceList = session.getExercices();
        return exerciceList.contains(exercice);
    }
    //EndRegion
}