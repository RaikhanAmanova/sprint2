package bitlab.g1.sprint2.controller;

import bitlab.g1.sprint2.entity.ApplicationRequest;
import bitlab.g1.sprint2.service.ApplicationRequestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

  @Autowired
  private ApplicationRequestService applicationRequestService;

  @GetMapping("/")
  public String homePage(Model model) {
    List<ApplicationRequest> applicationRequests = applicationRequestService.getApplicationRequests();
    model.addAttribute("zayavki", applicationRequests);
    return "home";
  }

  @GetMapping("/app-req/handled")
  public String handledApplicationRequests(Model model) {
    List<ApplicationRequest> applicationRequests = applicationRequestService.getHandledAppRequests();
    model.addAttribute("zayavki", applicationRequests);
    return "home";
  }

  @PostMapping("/add")
  public String addApplicationRequest(ApplicationRequest applicationRequest) {
    applicationRequestService.addApplicationRequest(applicationRequest);
    return "redirect:/";
  }

  @GetMapping("/applicationRequest/{id}")
  public String applicationRequestDetails(@PathVariable Long id, Model model) {
    ApplicationRequest applicationRequest = applicationRequestService.getApplicationRequestById(id);
    if (applicationRequest == null) {
      return "redirect:/";
    }
    model.addAttribute("applicationRequest", applicationRequest);
    return "details";
  }

  @PostMapping("/delete/{id}")
  public String deleteApplicationRequest(@PathVariable Long id) {
    applicationRequestService.deleteApplicationRequest(id);
    return "redirect:/";
  }

  @PostMapping("/handle/{id}")
  public String handleApplicationRequest(@PathVariable Long id, Model model) {
    applicationRequestService.processApplicationRequest(id);
    ApplicationRequest applicationRequest = applicationRequestService.getApplicationRequestById(id);
    model.addAttribute("applicationRequest", applicationRequest);
    return "handle";

  }

}

