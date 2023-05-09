/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oubeid Mezni
 */
public class ServiceTask {
    
    public ArrayList<Task> tasks;
    
    public static ServiceTask instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ServiceTask() {
        req = new ConnectionRequest();
    }
    
    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }
    
    public boolean addTask(Task t) {
        
        String name = t.getName();
        String description = t.getDescription();
        int priority = t.getPriority();
        boolean state = t.isState();
        int child = t.getChild();
        int parent = t.getParent();
        System.out.println(t);

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/addTaskJSON/new?name=" + name + "&description=" + description + "&state=" + state + "&priority=" + priority + "&child=" + child;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
    
    public ArrayList<Task> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Task t = new Task();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setPriority(((int) Float.parseFloat(obj.get("priority").toString())));
                t.setName(obj.get("name").toString());
                t.setDescription(obj.get("description").toString());
                t.setState(Boolean.parseBoolean(obj.get("state").toString()));
                tasks.add(t);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tasks;
    }
    
    public ArrayList<Task> getAllTasks() {
        String url = Statics.BASE_URL + "/display/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
    public boolean deleteTask(int taskId) {
        String url = Statics.BASE_URL + "/deleteTaskJSON/" + taskId;
       
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return resultOK;
    }
    
    public boolean updateTask(Task t) {
        
        String url = Statics.BASE_URL + "/updateTaskJSON/"+t.getId()+"?name=" + t.getName() + "&description=" + t.getDescription() + "&state=" + t.isState() + "&priority=" + t.getPriority() + "&child=" +14;
        
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public Task getTaskById(int taskId) throws IOException {
        String url = "http://localhost:8000/tasks/display/{id}" + taskId;
        ConnectionRequest request = new ConnectionRequest(url);
        request.setHttpMethod("GET");
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        
        byte[] data = request.getResponseData();
        if (data == null) {
            return null;
        }
        
        String content = new String(data);
        JSONParser parser = new JSONParser();
        Map<String, Object> response = parser.parseJSON(new CharArrayReader(content.toCharArray()));
        
        Task task = new Task();
        task.setId((int) response.get("id"));
        task.setName((String) response.get("name"));
        task.setDescription((String) response.get("description"));
        task.setPriority((int) response.get("priority"));
        task.setState((boolean) response.get("state"));
        return task;
    }
    
}
