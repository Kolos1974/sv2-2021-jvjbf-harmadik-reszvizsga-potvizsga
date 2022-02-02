package contentsite;

import java.util.List;

public class ContentService {
    private List<User> allUsers;
    private List<Content> allContent;

    public List<User> getAllUsers() {
        return allUsers;
    }

    public List<Content> getAllContent() {
        return allContent;
    }

    public void registerUser(String name, String password){
        User newUser = new User(name, password);
        if (!(getAllUsers().contains(newUser))){
            allUsers.add(newUser);
        }
    }

    public void addContent(Content content){
        if (!isContent(content)){
            allContent.add(content);
        }
    }

    private boolean isContent(Content c){
        boolean found = false;
        for (Content element : allContent){
            if (element.getTitle().equals(c)){
                found = true;
            }
        }
        return found;
    }

    public void logIn(String username, String password){

    }

    public void clickOnContent(User user, Content content){


    }


}
