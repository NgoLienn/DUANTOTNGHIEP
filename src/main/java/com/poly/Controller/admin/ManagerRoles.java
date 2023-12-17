package com.poly.Controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.poly.Entity.Roles;
import com.poly.Reponsitory.RoleRespontory;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.poly.Entity.Categories;
import com.poly.Entity.Image_product;
import com.poly.Entity.Products;
import com.poly.Reponsitory.CategoryRepository;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CategoryService;

@Controller
@RequestMapping("/admin")
public class ManagerRoles {
    @Autowired
    RoleRespontory roleRespontory;
    @GetMapping("/managerRoles")
    public String ViewRoles(Model model) {

        List<Roles> roles = roleRespontory.findAll();

        model.addAttribute("newRoles", new Roles());
        model.addAttribute("roles", roles);

        return "admin/Roles";
    }


    @PostMapping("/managerRoles/add")
    public String addProduct(@ModelAttribute("newRoles") Roles roles,
                             Model model) {
        Roles findrole = roleRespontory.findByidandname(roles.getId(), roles.getName());
        if(findrole==null){
            Roles roles2 = new Roles();
            roles2.setId(roles.getId());
            roles2.setName(roles.getName());
            roleRespontory.save(roles2);
            return "redirect:/admin/managerRoles" ;
        }else{
            model.addAttribute("message", "Roles đã được sử dụng");

            List<Roles> roless = roleRespontory.findAll();
            model.addAttribute("newRoles", new Roles());
            model.addAttribute("roles", roless);

            return "admin/Roles";
        }
    }


    @GetMapping("/managerRoles/edit")
    public String edit(Model model, @RequestParam("id") String id) {
        Roles roles1 = roleRespontory.findByid(id);
        List<Roles> roles = roleRespontory.findAll();
        model.addAttribute("newRoles",roles1);
        model.addAttribute("roles", roles);

        model.addAttribute("roless", id);


        return "admin/Roles";
    }

    @PostMapping("/managerRoles/update")
    public String update(@ModelAttribute("newRoles") Roles roles,
                             Model model, @RequestParam("Roleid") String id) {

        Roles roles2 = roleRespontory.findByid(id);
        roles2.setName(roles.getName());
        roleRespontory.save(roles2);
        return "redirect:/admin/managerRoles" ;

    }

    @PostMapping("/managerRoles/delete/{roleId}")
    public String delete(@PathVariable("roleId") String roleId) {
        roleRespontory.deleteById(roleId);
        return "redirect:/admin/managerRoles";
    }




}
