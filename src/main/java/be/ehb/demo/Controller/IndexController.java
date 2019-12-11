package be.ehb.demo.Controller;

import be.ehb.demo.Model.Blog;
import be.ehb.demo.Model.Blogdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    Blogdao dao;

    @ModelAttribute(value = "alleBlogs")
        public Iterable<Blog>getAllBlogs(){
        return dao.findAll();
    }
    @ModelAttribute(value = "nieuweBlog")
        public Blog blogToSave(){
        return new Blog();
    }
    @RequestMapping(value = {"","/","/index"},method = RequestMethod.GET)
        public String showIndex(ModelMap map){
        return "index";
    }
    @RequestMapping(value = {"","/","/index"},method = RequestMethod.POST)
    public String saveBlog(@ModelAttribute(name = "nieuweBlog") @Valid Blog nieuweBlog, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "index";
        dao.save(nieuweBlog);
        return "redirect:/index";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteBlog(@PathVariable(value = "id") int id){
        dao.deleteById(id);
        return "redirect:/index";
}}
