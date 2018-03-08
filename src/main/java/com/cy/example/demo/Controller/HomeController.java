package com.cy.example.demo.Controller;


import org.springframework.security.core.Authentication;
import com.cy.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


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



    @RequestMapping("/entertainment")
    public String showindexent(Model model){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://newsapi.org/v2/top-headlines?sources=entertainment-weekly&apiKey=f4fbdc20b9334e948008b6056897a516";

        News news =restTemplate.getForObject(url, News.class);

        //return news.getArticles().get(0).getTitle();

        model.addAttribute("articles",news.getArticles());
        System.out.println("before calling listnews ");

        return "listnews";
    }

    @RequestMapping("/")
    public String showindex(Model model){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://newsapi.org/v2/everything?domains=wsj.com&apiKey=f4fbdc20b9334e948008b6056897a516";

       News news =restTemplate.getForObject(url, News.class);

        //return news.getArticles().get(0).getTitle();

       model.addAttribute("articles",news.getArticles());
       System.out.println("before calling listnews ");

        return "listnews";
    }


    @RequestMapping("/testapi")
    public @ResponseBody String showapi(){
        RestTemplate restTemplate = new RestTemplate();

        News news =restTemplate.getForObject("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=f4fbdc20b9334e948008b6056897a516", News.class);

        return news.getArticles().get(10).getTitle();
    }




   /* @RequestMapping("/")
    public String mainpage(Model model)
    {
        return "mainpage";
    }
*/

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
        if(result.hasErrors())
        {
            return "registration";
        }

        if(request.getParameter("isAdmin")!=null)
            user.addRole(appRoleRepository.findAppRoleByRoleName("ADMIN"));
        else
            user.addRole(appRoleRepository.findAppRoleByRoleName("USER"));

        appUserRepository.save(user);
        return "redirect:/login";
    }

  /*  @RequestMapping("/listnews")
    public String listnews(Model model, Authentication auth)
    {
        AppUser user = appUserRepository.findAppUserByUsername(auth.getName());
        model.addAttribute("newss", newsRepository.findNewsByC(user));
        return "listnews";
    }
/*
    @RequestMapping("/addcategory")
    public String addcategory(Model model)
    {
        ReportItem reportItem  = new ReportItem();
        reportitemRepository.save(reportItem);

        model.addAttribute("reportitem", reportItem);
        model.addAttribute("users",userRepository.findAll());

        return "addcategory";
    }

    @PostMapping("/addcategory")
    public String addreportitemadm(HttpServletRequest request,@Valid @ModelAttribute("reportitem") ReportItem reportItem, Authentication auth, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "addreportitemadm";
        }

        String userid = request.getParameter("userid");
        AppUser appuser = userRepository.findOne(new Long(userid));
        reportItem.addUsertoReport(appuser);

        reportitemRepository.save(reportItem);
        model.addAttribute("reportlist",reportitemRepository.findAll());
        return "redirect:/";
    }
*/
}
