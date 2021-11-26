package HCS.client.ViewModel;

import HCS.client.model.HCSModelAdminInterface;
import HCS.shared.transferObjects.Message;
import HCS.shared.transferObjects.Request;
import HCS.shared.transferObjects.RequestType;
import HCS.shared.utility.Subject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


public class UserChatViewModel implements Subject {
    private ObservableList<String> users;
    private StringProperty s,textChat;
    private HCSModelAdminInterface model;
    private StringProperty sentMessageProperty;
    private PropertyChangeSupport support;


    public UserChatViewModel(HCSModelAdminInterface model) {
        this.model = model;
        support = new PropertyChangeSupport(this);
        s = new SimpleStringProperty("");
        textChat = new SimpleStringProperty("");
        users = FXCollections.observableArrayList(getUsers());

        sentMessageProperty = new SimpleStringProperty("");
        model.addListener(RequestType.UPDATE_ACTIVE_USERS.toString(), this::updateActiveUsersaftersisconnect);
        model.addListener(RequestType.GET_ACTIVE_USERS.toString(), this::updateActiveUsers);
        model.addListener(RequestType.RECEIVE_PUBLIC.toString(), this::receivePublicMessage);
    }

    private void updateActiveUsersaftersisconnect(PropertyChangeEvent event)
    {
        String username = (String) event.getNewValue();
        users.remove(username);
    }

    private void receivePublicMessage(PropertyChangeEvent event) {
        Request request = (Request) event.getNewValue();
        Message receivedMessage = (Message) request.getArg();
        textChat.setValue(textChat.getValue() + receivedMessage.getSenderUsername() + "--->" + receivedMessage.getMessageBody() + "\n");
    }

    private void updateActiveUsers(PropertyChangeEvent event) {
     String username = (String) event.getNewValue();
     users.add(username);

    }

    public StringProperty updatemessagearea() {
        return textChat;
    }

    public StringProperty getSentMessageProperty() {
        return sentMessageProperty;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
    private ArrayList<String> getUsers(){
        return model.getActiveUsersList();
    }

    public ObservableList<String> getActiveUsersList() {
    return users;
    }

    public void sendPublicMessage() {
        model.sendPublicMessage(sentMessageProperty.getValue());
    }

    public void disconnect() {
        model.disconnect();
    }
}
