import java.util.Scanner;

public class MainOptions {
    Scanner scan = new Scanner(System.in);
    public void mainOptions(){
        String[] options = {
                "1. Visualizar agenda",
                "2. Visualizar favoritos",
                "3. Criar novo contato",
                "4. Editar contato",
                "5. Excluir contato",
                "6. Excluir agenda",
                "0. Sair da Agenda"
        };

        System.out.printf("O que voçê deseja fazer?\n");

        for (String option : options) {
            System.out.println(option);
        }

        System.out.print("\n>> ");
        selectAppOption();
        }
    private void selectAppOption() {

        int choice;

        if (scan.hasNextInt()) {

            Util.clearScreen();
            choice = scan.nextInt();

            switch (choice) {
                case 0:
                    System.out.printf("Até mais!");
                    scan.close();
                    break;
                case 1:
                    ListContact listContact = new ListContact();
                    ListContact.listContacts();
                    mainOptions();
                    break;
                case 2:
                    //falta implementar
                    mainOptions();
                    break;
                case 3:
                    InsertContact insertContact = new InsertContact();
                    InsertContact.insertContact();
                    mainOptions();
                    break;
                case 4:
                    //falta implementar
                    mainOptions();
                    break;
                case 5:
                    //falta implementar
                    mainOptions();
                    break;
                case 6:
                    //falta implementar
                    mainOptions();
                    break;
                default:
                    invalidOptions();
                    mainOptions();
            }

        } else {
            invalidOptions();
            mainOptions();
        }
    }

    private void invalidOptions() {
        Util.clearScreen();
        System.out.printf("Não consegui te entender. Só posso te ajudar com as opções abaixo.");
    }
}
