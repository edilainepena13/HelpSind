package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.domain.Account;
import com.ufop.HelpSind.enums.BankAccountType;
import com.ufop.HelpSind.service.AccountService;
import com.ufop.HelpSind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("trustee/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;
    @NotNull
    private String type;

    @ModelAttribute("ativo")
    public String[] ativo() {
        return new String[]{"finance", "accounts"};
    }

    @ModelAttribute("types")
    public BankAccountType[] bankAccountTypes() {
        return BankAccountType.values();
    }

    @GetMapping({"", "/", "/lista"})
    public ModelAndView getAccounts(@RequestParam("account") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size, ModelMap model) {
        model.addAttribute("account",
                accountService.listPage(PageRequest.of(page.orElse(1) - 1, size.orElse(20))));
        model.addAttribute("content", "accountList");
        return new ModelAndView("layouts/trustee", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView getAccountRegister(@ModelAttribute("account") Account account, ModelMap model) {
        account.setInitialBalance(BigDecimal.ZERO);
        model.addAttribute("type", "");
        model.addAttribute("content", "accountRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PostMapping("/cadastro")
    public ModelAndView postAccountsRegister(@Valid @ModelAttribute("account") Account account, BindingResult validation) {
        account.setCondominium(userService.logged().getCondominium());

        if (validation.hasErrors()) {
            account.setIdAccount(null);
            return new ModelAndView("layouts/trustee", "content", "accountRegister");
        }

        accountService.save(account);
        return new ModelAndView("redirect:/trustee/account");
    }

    @GetMapping("/{idAccount}/cadastro")
    public ModelAndView getAccountsRegister(@PathVariable("idAccount") Long idAccount, ModelMap model) {
        model.addAttribute("account", accountService.read(idAccount));
        model.addAttribute("content", "accountRegister");
        return new ModelAndView("layouts/trustee", model);
    }

    @PutMapping("/cadastro")
    public ModelAndView putAccountsRegister(@Valid @ModelAttribute("idAccount") Account account, BindingResult validation,
                                            ModelMap model) {
        accountService.validate(account, validation);
        if (validation.hasErrors()) {
            model.addAttribute("content", "accountRegister");
            return new ModelAndView("layouts/trustee", model);
        }
        accountService.update(account);
        return new ModelAndView("redirect:/trustee/account");
    }

    @DeleteMapping("/excluir")
    public ModelAndView deleteAccountRegister(@RequestParam("idObj") Long idObj) {
        accountService.delete(accountService.read(idObj));
        return new ModelAndView("redirect:/trustee/account");
    }

}
