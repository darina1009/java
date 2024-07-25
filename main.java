import java.util.*;

class Notebook {
    private String model;
    private int ram;
    private int hdd;
    private String os;
    private String color;

    public Notebook(String model, int ram, int hdd, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "model='" + model + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

class NotebookStore {
    private Set<Notebook> notebooks;

    public NotebookStore() {
        notebooks = new HashSet<>();
    }

    public void addNotebook(Notebook notebook) {
        notebooks.add(notebook);
    }

    public void filterNotebooks() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filterParams = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.print("> ");
        int criterion = scanner.nextInt();

        switch (criterion) {
            case 1:
                System.out.print("Введите минимальное значение ОЗУ: ");
                int minRam = scanner.nextInt();
                filterParams.put("ram", minRam);
                break;
            case 2:
                System.out.print("Введите минимальное значение объема ЖД: ");
                int minHdd = scanner.nextInt();
                filterParams.put("hdd", minHdd);
                break;
            case 3:
                System.out.print("Введите операционную систему: ");
                String os = scanner.next();
                filterParams.put("os", os);
                break;
            case 4:
                System.out.print("Введите цвет: ");
                String color = scanner.next();
                filterParams.put("color", color);
                break;
            default:
                System.out.println("Неверный критерий фильтрации");
                return;
        }

        Set<Notebook> filteredNotebooks = new HashSet<>();
        for (Notebook notebook : notebooks) {
            boolean matchesFilter = true;
            for (Map.Entry<String, Object> entry : filterParams.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                switch (key) {
                    case "ram":
                        if (notebook.getRam() < (int) value) {
                            matchesFilter = false;
                        }
                        break;
                    case "hdd":
                        if (notebook.getHdd() < (int) value) {
                            matchesFilter = false;
                        }
                        break;
                    case "os":
                        if (!notebook.getOs().equals(value)) {
                            matchesFilter = false;
                        }
                        break;
                    case "color":
                        if (!notebook.getColor().equals(value)) {
                            matchesFilter = false;
                        }
                        break;
                }
            }
            if (matchesFilter) {
                filteredNotebooks.add(notebook);
            }
        }

        System.out.println("Ноутбуки, отвечающие фильтру:");
        for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook);
        }
    }
}

public class Printer {
    public static void main(String[] args) {
        NotebookStore store = new NotebookStore();
        store.addNotebook(new Notebook("Lenovo ThinkPad X1 Carbon", 16, 512, "Windows 10", "black"));
        store.addNotebook(new Notebook("MacBook Pro", 16, 1024, "macOS", "silver"));
        store.addNotebook(new Notebook("Dell XPS 13", 8, 256, "Windows 10", "silver"));
        store.addNotebook(new Notebook("HP Spectre x360", 16, 512, "Windows 10", "black"));
        store.addNotebook(new Notebook("Asus ZenBook S13", 8, 512, "Windows 10", "blue"));

        store.filterNotebooks();
    }
}