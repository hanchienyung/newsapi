package com.cy.example.demo.Controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.cy.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;


@Controller
public class HomeController {


    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String mainpage(Model model){
        return "mainpage";
    }

    @RequestMapping("/top")
    public String showindex(Model model){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://newsapi.org/v2/everything?domains=wsj.com&apiKey=f4fbdc20b9334e948008b6056897a516";

        News news =restTemplate.getForObject(url, News.class);

        //return news.getArticles().get(0).getTitle();

        model.addAttribute("articles",news.getArticles());
        System.out.println("before calling listnews ");

        return "listnews";
    }

    @RequestMapping("/entertainment")
    public String showindexent(Model model){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://newsapi.org/v2/top-headlines?sources=entertainment-weekly&apiKey=f4fbdc20b9334e948008b6056897a516";

        News news =restTemplate.getForObject(url, News.class);

        //return news.getArticles().get(0).getTitle();

        model.addAttribute("articles",news.getArticles());
        return "listnews";
    }

    @RequestMapping("/business")
    public String showindexbus(Model model){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://newsapi.org/v2/top-headlines?sources=business-insider&apiKey=f4fbdc20b9334e948008b6056897a516";

        News news =restTemplate.getForObject(url, News.class);

        //return news.getArticles().get(0).getTitle();

        model.addAttribute("articles",news.getArticles());
        return "listnews";
    }

    @RequestMapping("/sports")
    public String showindexspo(Model model){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=f4fbdc20b9334e948008b6056897a516";

        News news =restTemplate.getForObject(url, News.class);

        //return news.getArticles().get(0).getTitle();

        model.addAttribute("articles",news.getArticles());
        return "listnews";
    }

    @RequestMapping("/everything")
    public String showall(Model model){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=f4fbdc20b9334e948008b6056897a516";

        News news =restTemplate.getForObject(url, News.class);

        //return news.getArticles().get(0).getTitle();

        model.addAttribute("articles",news.getArticles());
        return "listnews";
    }


    @RequestMapping("/testapi")
    public @ResponseBody String showapi(){
        RestTemplate restTemplate = new RestTemplate();

        News news =restTemplate.getForObject("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=f4fbdc20b9334e948008b6056897a516", News.class);

        return news.getArticles().get(10).getTitle();
    }


    @RequestMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(Model model)
    {
        return "mainpage";
    }

    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("user",new AppUser());
        return "registration";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("user") AppUser user, BindingResult result, HttpServletRequest request)
    {
        String thePassword = user.getPassword();

        if(result.hasErrors())
        {
            return "registration";
        }

        if(request.getParameter("isAdmin")!=null)
            user.addRole(appRoleRepository.findAppRoleByRoleName("ADMIN"));
        else
            user.addRole(appRoleRepository.findAppRoleByRoleName("USER"));

        user.setPassword(passwordEncoder.encode(thePassword));
        appUserRepository.save(user);
        return "redirect:/login";
    }


    @RequestMapping("/addtopic")
    public String addtopic(HttpServletRequest request, @ModelAttribute("userProfile") UserProfile userProfile,BindingResult result,Model model)
    {
        return "topicform";
    }

    @PostMapping("/addtopic")
    public String addtopic(HttpServletRequest request, Model model, Authentication auth)
    {
        String searchstr = request.getParameter("topic");
        String username = auth.getName();
        AppUser appuser = appUserRepository.findAppUserByUsername(username);

        //model.addAttribute("appuser", appuser);
        /*  UserProfile userProfile = userProfileRepository.findUserProfileByUser(appuser);
        userProfile.setTopic(searchstr);
        userProfileRepository.save(userProfile);
        */
        HashSet <UserProfile> userProfile = userProfileRepository.findByAppusersIn(appuser);

        for (UserProfile up: userProfile) {
            up.setTopic(searchstr);
            userProfileRepository.save(up);
        }




      //  RestTemplate restTemplate = new RestTemplate();
     //   String url = "https://newsapi.org/v2/everything?apiKey=f4fbdc20b9334e948008b6056897a516&q=" + searchstr;
      //  News news =restTemplate.getForObject(url, News.class);

        //return news.getArticles().get(0).getTitle();

     //   model.addAttribute("articles",news.getArticles());
        return "mainpage";


     /*   https://newsapi.org/v2/everything?q=bitcoin&apiKey=f4fbdc20b9334e948008b6056897a516
        String userid = request.getParameter("userid");
        AppUser appuser = userRepository.findOne(new Long(userid));
        reportItem.addUsertoReport(appuser);

        reportitemRepository.save(reportItem);
        model.addAttribute("reportlist",reportitemRepository.findAll());
        return "redirect:/";
        */
    }

}
