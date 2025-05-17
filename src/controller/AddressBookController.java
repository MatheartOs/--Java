package controller;

import model.AddressBook;
import model.Contact;
import java.util.List;
import java.util.Map;

public class AddressBookController {
    private AddressBook addressBook;

    public AddressBookController(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    // 兼容旧接口，remark 为空
    public void addContact(String name, String gender, String group, String address, String email, String phone) {
        addressBook.addContact(name, gender, group, address, email, phone, "");
    }

    public void addContact(String name, String gender, String group, String address, String email, String phone, String remark) {
        addressBook.addContact(name, gender, group, address, email, phone, remark);
    }

    public void removeContact(Contact contact) {
        addressBook.removeContact(contact);
    }

    // 兼容旧接口，remark 为空
    public void updateContact(Contact contact, String name, String gender, String group, String address, String email, String phone) {
        addressBook.updateContact(contact, name, gender, group, address, email, phone, "");
    }

    public void updateContact(Contact contact, String name, String gender, String group, String address, String email, String phone, String remark) {
        addressBook.updateContact(contact, name, gender, group, address, email, phone, remark);
    }

    public List<Contact> getContacts() {
        return addressBook.getContacts();
    }

    public Contact findContactByName(String name) {
        return addressBook.findContactByName(name);
    }

    public List<Contact> searchContacts(String keyword) {
        return addressBook.searchContacts(keyword);
    }

    public Map<String, List<Contact>> getGroups() {
        return addressBook.getGroups();
    }
}
