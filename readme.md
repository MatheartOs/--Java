# 通讯录管理系统

本项目是一个基于 Java 的大型通讯录管理系统，采用多文件结构，涵盖 Java 多数关键特性，并实现了 GUI 界面。

## 项目结构
- src/
  - Main.java
  - model/
    - Contact.java
    - AddressBook.java
  - view/
    - AddressBookGUI.java
  - controller/
    - AddressBookController.java

## 功能特性
- 增、删、查、改联系人
- 支持联系人分组
- 图形化用户界面（GUI）
- 数据持久化（可选扩展）

## 运行方式
1. 安装 JDK 8 及以上版本。
2. 进入 src 目录，编译所有 Java 文件：
   ```shell
   javac Main.java model/*.java view/*.java controller/*.java
   ```
3. 运行主程序：
   ```shell
   java Main
   ```

