package com.example.login_formulario;

import java.util.ArrayList;

class Database {

    /*Singleton pattern*/
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return (instance);
    }

    private Database() {
        this.users = new ArrayList<>();
        this.resumes = new ArrayList<>();
        createUsers();
        createResumes();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addResume(Resume resume){
        this.resumes.add(resume);
    }

    public void removeResume(String id){
        for(int i=0; i<this.resumes.size(); i++){
            if(this.resumes.get(i).getID().equals(id)){
                this.resumes.remove(i);
                break;
            }
        }
    }

    public void changePassword(String username, String newPassword){
        for (User u : this.users) {
            if (u.getUsername().equals(username)){
                u.setPassword(newPassword);
            }
        }
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Resume> getResumes() {
        return this.resumes;
    }

    private void createResumes() {
        this.resumes.add(new Resume("999", "wef", "fqf", "wef", "wef", "wef", "wef", "234", "United States", "werg", "234", "234", "Cloud Architect", "23/01/90"));
        this.resumes.add(new Resume("998", "eef", "wwe", "wef", "wef", "wef", "wef", "234", "France", "werg", "234", "234", "Cloud Services Developer", "23/01/90"));
        this.resumes.add(new Resume("997", "twef", "2ef", "wef", "wef", "wef", "wef", "234", "Italy", "werg", "234", "234", "Cloud Software and Network Engineer", "23/01/90"));
        this.resumes.add(new Resume("996", "w2e34", "wgef", "wef", "wef", "wef", "wef", "234", "India", "werg", "234", "234", "Cloud System Engineer", "23/01/90"));
    }


    private void createUsers() {
        this.users.add(new User("admin", "admin", App.getContext().getResources().getString(R.string.administratorRole)));
        this.users.add(new User("user1", "user1", App.getContext().getResources().getString(R.string.noramlUserRole)));
    }

    /* Que no se repitan los username */
    public boolean verifyUsername(String username) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) return true;
        }
        return false;
    }
    public Resume getResumeID(String ID){
        Resume resume = null;
        for(int i = 0; i<resumes.size();i++){
            if(resumes.get(i).getID().equals(ID)){
                resume = resumes.get(i);
            }
        }
        return resume;
    }
    private ArrayList<User> users;
    private ArrayList<Resume> resumes;
    private static Database instance = null;

}
