import java.util.Scanner;

public class Agenda {

    ServiceContacts serviceContacts = new ServiceContacts();

    public void open() {
       showMenu();
       selectOption();
    }

    private void showMenu() {
        String menuOptions = String.join("\n",
                "O que você deseja fazer?",
                "1. Visualizar agenda",
                "2. Visualizar favoritos",
                "3. Criar novo contato",
                "4. Editar contato",
                "5. Excluir contato",
                "6. Excluir agenda",
                "0. Sair da Agenda"
        );
        System.out.println(menuOptions);
    }

    private void selectOption() {
        System.out.print(">> ");
        execMenuOption(new Scanner(System.in).nextInt());
    }

    private void execMenuOption(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Até mais!");
                return;
            case 1:
                serviceContacts.listContacts(false);
                break;
            case 2:
                serviceContacts.listContacts(true);
                break;
            case 3:
                serviceContacts.insertContact();
                break;
            case 4:
                serviceContacts.editContact();
                break;
            case 5:
                serviceContacts.deleteContact();
                break;
            case 6:
                serviceContacts.deleteAllContact();
                break;
            default:
                invalidOptions();
        }

        open();
    }

    private void invalidOptions() {
        Util.clearScreen();
        System.out.println("Não consegui entender. Por favor, escolha uma opção válida.");
    }


}
