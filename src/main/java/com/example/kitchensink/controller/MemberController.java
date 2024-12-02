package com.example.kitchensink.controller;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.model.UserEntity;
import com.example.kitchensink.security.SecurityUtil;
import com.example.kitchensink.service.MemberService;
import com.example.kitchensink.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String listMembers(Model model) {
        System.out.println("ListMembers");
        UserEntity user = new UserEntity();
        String roleName = "";
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            // Check if the user has any roles to avoid IndexOutOfBoundsException
            if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                roleName = user.getRoles().get(0).getName();
            }
        }
        model.addAttribute("roleName", roleName);
        model.addAttribute("members", memberService.getAllUsers());
        return "index";
    }

    @GetMapping("/member")
    public String listAllMembers(Model model) {
        System.out.println("ListMembers");
        UserEntity user = new UserEntity();
        String roleName = "";
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            roleName = user.getRoles().get(0).getName();
        }
        System.out.println("User : "+ user.getRoles().get(0).getName());
        model.addAttribute("roleName", roleName);
        model.addAttribute("members", memberService.getAllUsers());
        return "index";
    }

    @GetMapping("/members/new")
    public String showCreateForm(Model model) {
        model.addAttribute("member", new Member());
        return "user-form";
    }

    @PostMapping("/members")
    public String createMember(@Valid @ModelAttribute Member user) { //ModelAttribute
        memberService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/members/{id}")
    public String showMemberDetails(@PathVariable String id, Model model) {
        model.addAttribute("member", memberService.getUserById(id));
        return "user-details";
    }

    @GetMapping("/members/{id}/edit")
    public String editMemberForm(@PathVariable("id") String id, Model model) {
        Member member = memberService.getUserById(id);
        model.addAttribute("member", member);
        return "members-edit";
    }

    @PostMapping("/members/{id}/edit")
    public String updateMember(@PathVariable("id") String id,
                             @Valid @ModelAttribute("member") Member member, Model model) throws IllegalAccessException {
        member.setId(id);
        memberService.updateMember(member);
        return "redirect:/member";
    }


    @GetMapping("/members/{id}/delete")
    public String deleteClub(@PathVariable("id")String id) {
        memberService.deleteUser(id);
        return "redirect:/member";
    }

    @GetMapping("/error")
    public String handleError() {
        return "index";
    }
}
