/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Task;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
private static final char ADD_ICON_NAME = FontImage.MATERIAL_ADD_CIRCLE_OUTLINE;
    private static final char LIST_ICON_NAME = FontImage.MATERIAL_LIST_ALT;
    private static final char UPDATE_ICON_NAME = FontImage.MATERIAL_CREATE;
    private static final char DELETE_ICON_NAME = FontImage.MATERIAL_DELETE;
    private static final char CONSULT_ICON_NAME = FontImage.MATERIAL_PAGEVIEW;

    public HomeForm() {
        Task t = new Task();
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Ajouter Covoiturage");
        btnAddTask.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        Button btnListTasks = new Button("List Covoiturages");
                        btnListTasks.setIcon(FontImage.createMaterial(CONSULT_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));

        Button btnDeleteTasks = new Button("Delete Covoiturage");
                btnDeleteTasks.setIcon(FontImage.createMaterial(DELETE_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));

        btnAddTask.addActionListener(e-> new AddTaskForm(this).show());
        btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnDeleteTasks.addActionListener(e -> new deleteTaskForm(this).show());
        addAll(btnAddTask,btnListTasks,btnDeleteTasks);
        
        
    }
    
    
}
