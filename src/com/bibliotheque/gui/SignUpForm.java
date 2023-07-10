/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Service.ServiceUtilisateur;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.Vector;

/**
 *
 * @author moham
 */
public class SignUpForm extends BaseForm  {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
       
        setUIID("SignIn");
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField firstname = new TextField("", "firstname", 20, TextField.ANY);
        TextField lastname = new TextField("", "lastname", 20, TextField.ANY);
        TextField address = new TextField("", "address", 20, TextField.ANY);
        TextField telephone = new TextField("", "telephone", 20, TextField.ANY);
        Vector<String> vectorRole;
        vectorRole = new Vector();
        
        vectorRole.add("Patient");
        vectorRole.add("Admin");
        email.setSingleLineTextArea(false);
        ComboBox<String>roles = new ComboBox<>(vectorRole);
        password.setSingleLineTextArea(false);
        firstname.setSingleLineTextArea(false);
        lastname.setSingleLineTextArea(false);
        address.setSingleLineTextArea(false);
        telephone.setSingleLineTextArea(false);
        Button next = new Button("SignUp");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                roles,
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(firstname),
                createLineSeparator(),
                new FloatingHint(lastname),
                createLineSeparator(),
                new FloatingHint(address),
                createLineSeparator(),
                new FloatingHint(telephone),
                createLineSeparator()
        );
        content.setScrollableY(false);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e) -> {
            
            ServiceUtilisateur.getInstance().signup(email, roles, password,firstname,lastname,address,telephone  , res);
            Dialog.show("Success","account is saved","OK",null);
            new SignInForm(res).show();
        });
    }
    
}
