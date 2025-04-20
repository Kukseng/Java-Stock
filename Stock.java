package Homework;

import java.time.LocalDate;
import java.util.*;

public class Stock {
    public static void main(String[] args) {
        int rows = 0, choice = 0;
        boolean stockInit = false;
        String[][] stock = null;
        LocalDate[][] insertDates = null;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Java Stock Program");

        do {
            System.out.println("********--|-STOCK-MANAGEMENT-|--********");
            System.out.println("[1]. Set Up Stock.");
            System.out.println("[2]. View Stock.");
            System.out.println("[3]. Insert Product to Stock.");
            System.out.println("[4]. Update Product in Stock.");
            System.out.println("[5]. Delete Product from Stock.");
            System.out.println("[6]. View Insertion history.");
            System.out.println("[7]. Exits");
            System.out.print("[+] Insert Options: ");

            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("[!] Invalid input. Please enter a number.");
                input.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("[+] Enter Number of Stock: ");
                    try {
                        rows = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("[!] Please enter a valid number.");
                        input.nextLine();
                        break;
                    }

                    stock = new String[rows][];
                    insertDates = new LocalDate[rows][];
                    for (int i = 0; i < rows; i++) {
                        System.out.println("[+] Enter number of catalogue [ " + (i + 1) + " ]");
                        try {
                            int cols = input.nextInt();
                            stock[i] = new String[cols];
                            insertDates[i] = new LocalDate[cols];
                        } catch (InputMismatchException e) {
                            System.out.println("[!] Invalid number. Skipping this stock row.");
                            input.nextLine();
                            stock[i] = new String[0];
                            insertDates[i] = new LocalDate[0];
                        }
                    }
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < stock[i].length; j++) {
                            stock[i][j] = "Empty";
                            insertDates[i][j] = null;
                        }
                    }

                    System.out.println("---- SET UP STOCK SUCCEEDED ----");
                    for (int i = 0; i < rows; i++) {
                        System.out.print("Stock[" + (i + 1) + "] ==> ");
                        for (int j = 0; j < stock[i].length; j++) {
                            System.out.print("[" + (j + 1) + "-:" + stock[i][j] + "] ");
                        }
                        System.out.println();
                    }
                    stockInit = true;
                }

                case 2 -> {
                    if (!stockInit) {
                        System.out.println("Stock not Set Up yet! Choose Option [1]");
                    } else {
                        for (int i = 0; i < stock.length; i++) {
                            boolean stockFull = true;
                            System.out.print("Stock[" + (i + 1) + "] ==> ");
                            for (int j = 0; j < stock[i].length; j++) {
                                if (stock[i][j].equalsIgnoreCase("Empty")) {
                                    System.out.print("[" + (j + 1) + " - " + stock[i][j] + "] ");
                                    stockFull = false;
                                }
                                System.out.print("[" + stock[i][j] + "] ");
                            }
                            if (stockFull) {
                                System.out.print("=> Stock Full");
                            }
                            System.out.println();
                        }
                    }
                    input.nextLine();
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }

                case 3 -> {
                    System.out.println("-------------- Insert Product To Stock --------------");
                    if (!stockInit) {
                        System.out.println("[*[ Stock not Set Up yet! Choose Option [1]");
                    } else {
                        boolean hasEmpty = false;
                        System.out.print("Stock Number Available : ");
                        for (int i = 0; i < stock.length; i++) {
                            for (int j = 0; j < stock[i].length; j++) {
                                if (stock[i][j].equalsIgnoreCase("Empty")) {
                                    System.out.print(" [" + (i + 1) + "] | ");
                                    hasEmpty = true;
                                    break;
                                }
                            }
                        }
                        System.out.println();
                        if (!hasEmpty) {
                            System.out.println("[*] No available stock with empty slots.");
                        } else {
                            System.out.print("[+] Insert Stock number: ");
                            int row;
                            try {
                                row = input.nextInt() - 1;
                            } catch (InputMismatchException e) {
                                System.out.println("[!] Invalid input.");
                                input.nextLine();
                                break;
                            }

                            if (row < 0 || row >= stock.length) {
                                System.out.println("[*] Invalid Stock number.");
                            } else {
                                System.out.println("[*] Stock [" + (row + 1) + "] Details:");
                                boolean rowHasEmpty = false;
                                System.out.print("Stock [" + (row + 1) + "] ==> ");
                                for (int j = 0; j < stock[row].length; j++) {
                                    System.out.print("[" + (j + 1) + " - " + stock[row][j] + "] ");
                                }
                                System.out.println();

                                System.out.print("[*] Catalogue number available: ");
                                for (int j = 0; j < stock[row].length; j++) {
                                    if (stock[row][j].equalsIgnoreCase("Empty")) {
                                        System.out.print((j + 1) + " | ");
                                        rowHasEmpty = true;
                                    }
                                }
                                System.out.println();
                                if (!rowHasEmpty) {
                                    System.out.println("[*] That stock has no empty slots.");
                                } else {
                                    System.out.print("[+] Insert number of catalogue: ");
                                    int col;
                                    try {
                                        col = input.nextInt() - 1;
                                    } catch (InputMismatchException e) {
                                        System.out.println("[!] Invalid input.");
                                        input.nextLine();
                                        break;
                                    }

                                    if (col < 0 || col >= stock[row].length || !stock[row][col].equalsIgnoreCase("Empty")) {
                                        System.out.println("[*] Invalid or non-empty catalogue selected.");
                                    } else {
                                        input.nextLine();
                                        System.out.print("[+] Enter product name: ");
                                        String product = input.nextLine();
                                        stock[row][col] = product;
                                        insertDates[row][col] = LocalDate.now();
                                        System.out.println("[*] Product inserted at Stock[" + (row + 1) + "][" + (col + 1) + "]");
                                    }
                                }
                            }
                        }
                    }
                    input.nextLine();
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }

                case 4 -> {
                    System.out.println("-------------- Update Product in Stock --------------");
                    if (!stockInit) {
                        System.out.println("[*] Stock not Set Up yet! Choose Option [1]");
                    } else {
                        System.out.print("Available Stock Number ==> : ");
                        for (int i = 0; i < stock.length; i++) {
                            System.out.print((i + 1) + " | ");
                        }
                        System.out.println();
                        System.out.print("[+] Enter stock number to update: ");
                        int row;
                        try {
                            row = input.nextInt() - 1;
                        } catch (InputMismatchException e) {
                            System.out.println("[!] Invalid input.");
                            input.nextLine();
                            break;
                        }

                        if (row < 0 || row >= stock.length) {
                            System.out.println("[*] Invalid Stock number.");
                        } else {
                            input.nextLine();
                            System.out.print("[+] Enter the product name to update: ");
                            String oldProduct = input.nextLine();

                            boolean found = false;
                            for (int i = 0; i < stock.length; i++) {
                                for (int j = 0; j < stock[i].length; j++) {
                                    if (stock[i][j].equalsIgnoreCase(oldProduct)) {
                                        System.out.print("[*] Stock: [" + oldProduct + "] => Enter new product name: ");
                                        String newProduct = input.nextLine();
                                        stock[i][j] = newProduct;
                                        System.out.println("[*] Product updated successfully.");
                                        found = true;
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("[*] Product " + oldProduct + " not found in stock.");
                            }
                        }
                    }
                    input.nextLine();
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }

                case 5 -> {
                    System.out.println("-------------- Delete Product from Stock --------------");
                    if (!stockInit) {
                        System.out.println("[*] Stock not Set Up yet! Choose Option [1]");
                    } else {
                        input.nextLine();
                        System.out.print("[+] Enter the product name to delete: ");
                        String deleteName = input.nextLine();

                        boolean found = false;
                        for (int i = 0; i < stock.length; i++) {
                            for (int j = 0; j < stock[i].length; j++) {
                                if (stock[i][j].equalsIgnoreCase(deleteName)) {
                                    stock[i][j] = "Empty";
                                    insertDates[i][j] = null;
                                    System.out.println("[*] Deleted '" + deleteName + "' from Stock[" + (i + 1) + "][" + (j + 1) + "]");
                                    found = true;
                                }
                            }
                        }
                        if (!found) {
                            System.out.println("[*] Product '" + deleteName + "' not found.");
                        }
                    }
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }

                case 6 -> {
                    System.out.println("[+] Insertion History:");
                    if (!stockInit) {
                        System.out.println("[*] Stock not Set Up yet! Choose Option [1]");
                    } else {
                        boolean hasHistory = false;
                        for (int i = 0; i < stock.length; i++) {
                            for (int j = 0; j < stock[i].length; j++) {
                                if (!stock[i][j].equalsIgnoreCase("Empty") && insertDates[i][j] != null) {
                                    System.out.println("[*] Product: " + stock[i][j] +
                                            " | Date Inserted: " + insertDates[i][j]);
                                    hasHistory = true;
                                }
                            }
                        }
                        if (!hasHistory) {
                            System.out.println("[*] No products have been inserted yet.");
                        }
                    }
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                }
                case 7 -> System.out.println("[*] Thank you for using !");
                default -> System.out.println("[!] Invalid option.  choose 1–7.");
            }
        } while (choice != 7);
    }
}
