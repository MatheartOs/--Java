package model;

import java.io.Serializable;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddressBook implements Serializable {
    private List<Contact> contacts;
    private int nextId = 1;

    public AddressBook() {
        contacts = new ArrayList<>();
        loadFromFile();
    }

    public void addContact(String name, String gender, String group, String address, String email, String phone, String remark) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Contact contact = new Contact(nextId++, name, gender, group, address, email, phone, remark, now);
        contacts.add(contact);
        saveToFile();
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        // 编号前移，所有联系人编号重新从1递增
        int newId = 1;
        for (Contact c : contacts) {
            c.setId(newId++);
        }
        nextId = contacts.size() + 1;
        saveToFile();
    }

    public void updateContact(Contact contact, String name, String gender, String group, String address, String email, String phone, String remark) {
        contact.setName(name);
        contact.setGender(gender);
        contact.setGroup(group);
        contact.setAddress(address);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setRemark(remark);
        saveToFile();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact findContactByName(String name) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public List<Contact> searchContacts(String keyword) {
        List<Contact> result = new ArrayList<>();
        for (Contact c : contacts) {
            if (String.valueOf(c.getId()).contains(keyword)
                || c.getName().contains(keyword)
                || c.getGender().contains(keyword)
                || c.getGroup().contains(keyword)
                || c.getAddress().contains(keyword)
                || c.getEmail().contains(keyword)
                || c.getPhone().contains(keyword)) {
                result.add(c);
            }
        }
        return result;
    }

    public Map<String, List<Contact>> getGroups() {
        Map<String, List<Contact>> groupMap = new HashMap<>();
        for (Contact c : contacts) {
            groupMap.computeIfAbsent(c.getGroup(), k -> new ArrayList<>()).add(c);
        }
        return groupMap;
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("save.txt"))) {
            for (Contact c : contacts) {
                pw.println(c.getId() + "," + c.getName() + "," + c.getGender() + "," + c.getGroup() + "," + c.getAddress() + "," + c.getEmail() + "," + c.getPhone() + "," + c.getRemark().replace("\n"," ").replace(","," ") + "," + (c.getCreateTime() == null ? "" : c.getCreateTime()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File("save.txt");
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",", 9);
                if (arr.length >= 8) {
                    int id = Integer.parseInt(arr[0]);
                    String remark = arr[7];
                    String createTime = arr.length == 9 ? arr[8] : "";
                    contacts.add(new Contact(id, arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], remark, createTime));
                    if (id >= nextId) nextId = id + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
