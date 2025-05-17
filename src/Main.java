import model.AddressBook;
import controller.AddressBookController;
import view.AddressBookGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddressBook addressBook = new AddressBook();
            AddressBookController controller = new AddressBookController(addressBook);
            AddressBookGUI gui = new AddressBookGUI(controller);
            gui.setVisible(true);
        });
    }
}
