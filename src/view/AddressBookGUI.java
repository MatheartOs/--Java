package view;

import controller.AddressBookController;
import model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookGUI extends JFrame {
    private AddressBookController controller;
    private DefaultListModel<Contact> contactListModel;
    private JList<Contact> contactJList;
    private JTextField nameField, phoneField, emailField, groupField;
    private JTextField searchField;
    private JButton searchButton;
    private JTextField genderField, addressField;

    public AddressBookGUI(AddressBookController controller) {
        this.controller = controller;
        setTitle("通讯录管理系统");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // ignore
        }
        // 设置全局白底黑字
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("OptionPane.messageForeground", Color.BLACK);
        UIManager.put("Label.foreground", Color.BLACK);
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextField.foreground", Color.BLACK);
        UIManager.put("TextArea.background", Color.WHITE);
        UIManager.put("TextArea.foreground", Color.BLACK);
        UIManager.put("ScrollPane.background", Color.WHITE);
        UIManager.put("TitledBorder.titleColor", Color.BLACK);
        UIManager.put("Button.background", new Color(240,240,240));
        UIManager.put("Button.foreground", Color.BLACK);
        initComponents();
    }

    private void initComponents() {
        contactListModel = new DefaultListModel<>();
        contactJList = new JList<>(contactListModel);
        contactJList.setFont(new Font("微软雅黑", Font.PLAIN, 13)); // 字体小一些
        contactJList.setSelectionBackground(new Color(135, 206, 250));
        contactJList.setSelectionForeground(Color.BLACK);
        contactJList.setFixedCellHeight(28);
        contactJList.setBackground(Color.WHITE);
        contactJList.setForeground(Color.BLACK);
        contactJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Contact) {
                    Contact c = (Contact) value;
                    String time = c.getCreateTime();
                    // 用index+1作为显示编号，保证始终连续
                    label.setText((index + 1) + ". " + c.getName() + " [" + c.getGender() + "] (" + c.getGroup() + ") - " + c.getPhone() + ", " + c.getEmail() + ", " + c.getAddress() + (time != null && !time.isEmpty() ? "  [" + time + "]" : ""));
                }
                return label;
            }
        });
        JScrollPane scrollPane = new JScrollPane(contactJList);
        scrollPane.setPreferredSize(new Dimension(580, 0)); // 列表更宽
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "联系人列表", 0, 0, null, Color.BLACK));

        // 搜索面板
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchPanel.setBackground(Color.WHITE);
        searchField = new JTextField();
        searchField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.BLACK);
        searchButton = new JButton("检索");
        searchButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
        searchButton.setBackground(new Color(200, 220, 255));
        searchButton.setForeground(Color.BLACK);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchContacts();
            }
        });
        JLabel searchLabel = new JLabel("检索关键字:");
        searchLabel.setForeground(Color.BLACK);
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setPreferredSize(new Dimension(420, 0)); // 信息表也适当加宽
        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "联系人信息", 0, 0, null, Color.BLACK));
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("姓名:"); nameLabel.setForeground(Color.BLACK);
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        nameField = new JTextField();
        nameField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        nameField.setBackground(Color.WHITE);
        nameField.setForeground(Color.BLACK);
        nameField.setPreferredSize(new Dimension(90, 26));
        inputPanel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        JLabel genderLabel = new JLabel("性别:"); genderLabel.setForeground(Color.BLACK);
        inputPanel.add(genderLabel, gbc);
        gbc.gridx = 1;
        genderField = new JTextField();
        genderField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        genderField.setBackground(Color.WHITE);
        genderField.setForeground(Color.BLACK);
        genderField.setPreferredSize(new Dimension(90, 26));
        inputPanel.add(genderField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        JLabel groupLabel = new JLabel("分组:"); groupLabel.setForeground(Color.BLACK);
        inputPanel.add(groupLabel, gbc);
        gbc.gridx = 1;
        groupField = new JTextField();
        groupField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        groupField.setBackground(Color.WHITE);
        groupField.setForeground(Color.BLACK);
        groupField.setPreferredSize(new Dimension(90, 26));
        inputPanel.add(groupField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        JLabel addressLabel = new JLabel("地址:"); addressLabel.setForeground(Color.BLACK);
        inputPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        addressField = new JTextField();
        addressField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        addressField.setBackground(Color.WHITE);
        addressField.setForeground(Color.BLACK);
        addressField.setPreferredSize(new Dimension(90, 26));
        inputPanel.add(addressField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        JLabel emailLabel = new JLabel("邮箱:"); emailLabel.setForeground(Color.BLACK);
        inputPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        emailField = new JTextField();
        emailField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        emailField.setBackground(Color.WHITE);
        emailField.setForeground(Color.BLACK);
        emailField.setPreferredSize(new Dimension(90, 26));
        inputPanel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        JLabel phoneLabel = new JLabel("电话:"); phoneLabel.setForeground(Color.BLACK);
        inputPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        phoneField = new JTextField();
        phoneField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        phoneField.setBackground(Color.WHITE);
        phoneField.setForeground(Color.BLACK);
        phoneField.setPreferredSize(new Dimension(90, 26));
        inputPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new GridLayout(2, 3, 10, 8));
        btnPanel.setBackground(Color.WHITE);
        JButton addButton = new JButton("添加联系人");
        addButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
        JButton removeButton = new JButton("删除联系人");
        removeButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
        JButton editButton = new JButton("修改联系人");
        editButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
        JButton groupButton = new JButton("列出所有群组");
        groupButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
        JButton remarkButton = new JButton("查看备注");
        remarkButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
        btnPanel.add(addButton);
        btnPanel.add(removeButton);
        btnPanel.add(editButton);
        btnPanel.add(groupButton);
        btnPanel.add(remarkButton);
        inputPanel.add(btnPanel, gbc);

        addButton.setBackground(new Color(200, 255, 200));
        addButton.setForeground(Color.BLACK);
        removeButton.setBackground(new Color(255, 200, 200));
        removeButton.setForeground(Color.BLACK);
        editButton.setBackground(new Color(255, 230, 180));
        editButton.setForeground(Color.BLACK);
        groupButton.setBackground(new Color(200, 220, 255));
        groupButton.setForeground(Color.BLACK);
        remarkButton.setBackground(new Color(220, 220, 255));
        remarkButton.setForeground(Color.BLACK);
        for (JButton btn : new JButton[]{addButton, removeButton, editButton, groupButton, remarkButton}) {
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        }

        addButton.addActionListener(e -> addContact());
        removeButton.addActionListener(e -> removeContact());
        editButton.addActionListener(e -> showEditDialog());
        groupButton.addActionListener(e -> showGroups());
        remarkButton.addActionListener(e -> showRemark());

        setLayout(new BorderLayout(10, 10));
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.EAST);
        // 右下角欢迎语
        JLabel welcomeLabel = new JLabel("欢迎使用NEUQ通讯录系统(｀・ω・´)");
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        welcomeLabel.setForeground(new Color(70, 130, 180));
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.add(welcomeLabel);
        add(welcomePanel, BorderLayout.SOUTH);
        refreshContactList();
    }

    private void addContact() {
        String name = nameField.getText();
        String gender = genderField.getText();
        String group = groupField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        if (!name.isEmpty()) {
            controller.addContact(name, gender, group, address, email, phone);
            refreshContactList();
        }
    }

    private void removeContact() {
        Contact selected = contactJList.getSelectedValue();
        if (selected != null) {
            controller.removeContact(selected);
            refreshContactList();
        }
    }

    private void refreshContactList() {
        contactListModel.clear();
        for (Contact c : controller.getContacts()) {
            contactListModel.addElement(c);
        }
    }

    private void searchContacts() {
        String keyword = searchField.getText();
        contactListModel.clear();
        if (keyword == null || keyword.isEmpty()) {
            for (Contact c : controller.getContacts()) {
                contactListModel.addElement(c);
            }
        } else {
            for (Contact c : controller.searchContacts(keyword)) {
                contactListModel.addElement(c);
            }
        }
    }

    private void showGroups() {
        StringBuilder sb = new StringBuilder();
        var groupMap = controller.getGroups();
        int maxGroupLen = 0;
        for (var group : groupMap.keySet()) {
            if (group != null && group.length() > maxGroupLen) maxGroupLen = group.length();
        }
        for (var entry : groupMap.entrySet()) {
            String groupName = entry.getKey() == null ? "(未分组)" : entry.getKey();
            sb.append(String.format("群组: %s\n", padRight(groupName, maxGroupLen)));
            for (Contact c : entry.getValue()) {
                sb.append("    ").append(c.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "暂无群组", "所有群组及联系人", JOptionPane.INFORMATION_MESSAGE);
    }

    private String padRight(String s, int n) {
        if (s == null) s = "";
        return String.format("%-" + n + "s", s);
    }

    private void showRemark() {
        Contact selected = contactJList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "请先选中联系人！");
            return;
        }
        String remark = selected.getRemark();
        String createTime = selected.getCreateTime();
        String msg = (remark == null || remark.isEmpty() ? "无备注" : remark) +
            (createTime != null && !createTime.isEmpty() ? "\n添加时间：" + createTime : "");
        JOptionPane.showMessageDialog(this, msg, "备注与添加时间", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showEditDialog() {
        Contact selected = contactJList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "请先选中要修改的联系人！");
            return;
        }
        JTextField nameF = new JTextField(selected.getName());
        JTextField genderF = new JTextField(selected.getGender());
        JTextField groupF = new JTextField(selected.getGroup());
        JTextField addressF = new JTextField(selected.getAddress());
        JTextField emailF = new JTextField(selected.getEmail());
        JTextField phoneF = new JTextField(selected.getPhone());
        JTextArea remarkF = new JTextArea(selected.getRemark(), 3, 20);
        remarkF.setBackground(Color.WHITE);
        remarkF.setForeground(Color.BLACK);
        JPanel panel = new JPanel(new GridLayout(7,2));
        panel.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel("姓名:"); nameLabel.setForeground(Color.BLACK);
        JLabel genderLabel = new JLabel("性别:"); genderLabel.setForeground(Color.BLACK);
        JLabel groupLabel = new JLabel("分组:"); groupLabel.setForeground(Color.BLACK);
        JLabel addressLabel = new JLabel("地址:"); addressLabel.setForeground(Color.BLACK);
        JLabel emailLabel = new JLabel("邮箱:"); emailLabel.setForeground(Color.BLACK);
        JLabel phoneLabel = new JLabel("电话:"); phoneLabel.setForeground(Color.BLACK);
        JLabel remarkLabel = new JLabel("备注:"); remarkLabel.setForeground(Color.BLACK);
        panel.add(nameLabel); panel.add(nameF);
        panel.add(genderLabel); panel.add(genderF);
        panel.add(groupLabel); panel.add(groupF);
        panel.add(addressLabel); panel.add(addressF);
        panel.add(emailLabel); panel.add(emailF);
        panel.add(phoneLabel); panel.add(phoneF);
        panel.add(remarkLabel); panel.add(new JScrollPane(remarkF));
        int result = JOptionPane.showConfirmDialog(this, panel, "编辑联系人", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            controller.updateContact(selected, nameF.getText(), genderF.getText(), groupF.getText(), addressF.getText(), emailF.getText(), phoneF.getText(), remarkF.getText());
            refreshContactList();
        }
    }
}
