import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu{

  Map<Integer, MenuRow> menu = new HashMap<>();

  public Menu(MenuRow[] rows){
    int i = 0;
    for(MenuRow row : rows){
      menu.put(i++, new MenuRow(row.getChallenge(), row.getAction()));
    }
  }

  public MenuRow get(int i) {
        return menu.get(i);
    }

  public void print() {
        for (Map.Entry<Integer, MenuRow> pair : menu.entrySet()) {
            System.out.println(pair.getKey() + ": " + pair.getValue().getChallenge());
        }
    }

  public static void main(String[] args) {
        Driver.main(args);
    }
  
}

class MenuRow{
  String challenge;
  Runnable action;

  public MenuRow(String challenge, Runnable action){
    this.challenge = challenge;
    this.action = action;
  }

  public String getChallenge() {
        return this.challenge;
    }
    public Runnable getAction() {
        return this.action;
    }

  public void run() {
        action.run();
    }

}

class Driver{
  public static void main(String[] args){
    MenuRow[] rows = new MenuRow[]{new MenuRow("Exit", () -> main(null)),
                                  new MenuRow("Calculator", () -> Calculator.main(null)),
                                  new MenuRow("Queues and Stacks", () -> Queue.main(null))};

    Menu menu = new Menu(rows);
    while(true){
      System.out.println("");
      System.out.println("----------------------------------------");
      System.out.println("Navodit's Data Structures Challenge Labs");
      menu.print();

      try {
                Scanner sc = new Scanner(System.in);
                int selection = sc.nextInt();

                // menu action
                try {
                    MenuRow row = menu.get(selection);
                    // stop menu condition
                    if (row.getChallenge().equals("Exit"))
                        return;
                    // run option
                    row.run();
                } catch (Exception e) {
                    System.out.printf("Invalid selection %d\n", selection);
                }
            } catch (Exception e) {
                System.out.println("Not a number");
            }
    }
  }
}