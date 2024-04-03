package bitlab.g1.sprint2.service;

import bitlab.g1.sprint2.entity.ApplicationRequest;
import bitlab.g1.sprint2.repository.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationRequestService {
    @Autowired
    private ApplicationRequestRepository applicationRequestRepository;

    public List<ApplicationRequest> getApplicationRequests() {
        return applicationRequestRepository.findAll();
    }

    public List<ApplicationRequest> getHandledAppRequests() {
        return applicationRequestRepository.findAll().stream()
                .filter(ApplicationRequest::isHandled)
                .toList();
    }

    public ApplicationRequest addApplicationRequest(ApplicationRequest applicationRequest) {
        return applicationRequestRepository.save(applicationRequest);
    }

    public ApplicationRequest getApplicationRequestById(Long id) {
        return applicationRequestRepository.findById(id).orElse(null);
    }
    public void deleteApplicationRequest(Long id) {
        applicationRequestRepository.deleteById(id);
    }
    public void processApplicationRequest(Long id) {
        Optional<ApplicationRequest> optionalApplicationRequest = applicationRequestRepository.findById(id);
        if (optionalApplicationRequest.isPresent()) {
            ApplicationRequest applicationRequest = optionalApplicationRequest.get();
            applicationRequest.setHandled(true); // Устанавливаем статус заявки на "обработано"
            applicationRequestRepository.save(applicationRequest); // Сохраняем изменения в репозитории
        } else {
            throw new IllegalArgumentException("Application request with id " + id + " not found.");
        }
    }

}
