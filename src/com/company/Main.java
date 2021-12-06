package com.company;

import Controller.DiscController;
import Controller.RentalController;
import Controller.UserController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        commonMenu();
    }

    private static void commonMenu() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Что вы хотите сделать?");
            System.out.println(("1-действия с пользователями\n2-действия с диском\n3-действия с заказами"));
            var num = sc.nextInt();
            if (num == 1) {
                System.out.println("1-просмотреть всех пользователей\n2-редактировать имеющего пользователя\n3-удалить пользователя\n4-найти пользователя по фамилии и номеру телефона\n5-добавить пользователя");
                var question = sc.nextInt();
                menuUser(question);
            } else if (num == 2) {
                System.out.println("1-просмотреть диски\n2-найти диск\n3-удалить диск\n 4-добавить диск");
                var question = sc.nextInt();
                menuDisc(question);
            } else if (num == 3) {
                System.out.println("1-совершить заказ\n2-вернуть диск\n3-посмотреть заказы");
                var question = sc.nextInt();
                menuRental(question);
            }
        }

    }

    private static void menuUser(int question) {
        Scanner sc = new Scanner(System.in);
        if (question == 1 || question == 5) {
            if (question == 1) {
                var userControllErempty = new UserController();
                for (var item:userControllErempty.loadUser()) {
                    System.out.println(item);
                }

            } else if (question == 5) {
                System.out.println("Введи имя");
                var name = sc.next();
                System.out.println("Введи фамилию");
                var surname = sc.next();

                System.out.println("Введи отчество");
                var patronymic = sc.next();

                System.out.println("Введи номер телефона");
                var phoneNumber = sc.next();

                System.out.println("Введи адрес");
                var address = sc.next();
                try {
                    var userController = new UserController(surname, phoneNumber);
                    if (userController.isNewUser()) {
                        userController.setNewUserData(name, surname, patronymic, phoneNumber, address);
                        if (!userController.isNewUser()) {
                            System.out.println("Пользователь добавился успешно");
                        }
                    } else {
                        System.out.println("Пользователь у нас уже есть\n" + userController.getCurrentUser());
                    }

                } catch (ArgumentNullException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.println("Введи фамилию");
            var surname = sc.next();
            System.out.println("Введи номер телефона");
            var phoneNumber = sc.next();
            try {
                var userController = new UserController(surname, phoneNumber);
                if (userController.isNewUser()) {
                    System.out.println("Такого человека нет!\n Хотите добавить?\n1-да\n2-нет ");
                    var num = sc.nextInt();
                    if (num == 1) {
                        menuUser(5);
                    } else {
                        commonMenu();
                    }

                }

                if (question == 2) {
                    System.out.println(userController.getCurrentUser());

                    System.out.println("Введи новое имя");

                    var newName = sc.next();
                    System.out.println("Введи  новую фамилию");
                    var newSurname = sc.next();
                    System.out.println("Введи новый номер телефона");
                    var newPhoneNumber = sc.next();
                    System.out.println("Введи  новый адрес");
                    var newAddress = sc.next();
                    userController.editUser(newName, newSurname, newPhoneNumber, newAddress);
                    System.out.println(userController.getCurrentUser() + "\nИзменения прошли успешно\n");
                } else if (question == 3) {
                    userController.deleteUser();
                    if (userController.getCurrentUser() == null) {
                        System.out.println("Пользователь успешно удален\n");
                    }
                } else if (question == 4) {

                    System.out.println(userController.search(surname, phoneNumber));
                }
            } catch (ArgumentNullException e) {
                System.out.println(e.getMessage());
            }


        }

    }

    private static void menuDisc(int question) {
        Scanner sc = new Scanner(System.in);
        if (question == 1 || question == 4) {
            if (question == 1) {
                var discController = new DiscController();
                for (var item:discController.loadDisc()) {
                    System.out.println(item);
                }

            } else if (question == 4) {


                try {
                    System.out.println("Введи название");
                    var name = sc.nextLine();

                    System.out.println("Введи жанр");
                    var genre = sc.next();

                    System.out.println("Введи год выпуска");
                    var year = sc.next();

                    System.out.println("Введи режиссера");
                    var producer = sc.next();

                    var discController = new DiscController(name, genre, year, producer);
                    if (discController.isNewDisc()) {
                        discController.setNewDiscData(name, genre, year, producer);//todo: проверка name
                        if (!discController.isNewDisc()) {
                            System.out.println("Диск успешно добавлен\n");
                        }

                    } else {

                        System.out.println("Такой диск у нас уже есть" + discController.getCurrentDisc());
                    }
                } catch (ArgumentNullException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            try {
                System.out.println("Введи название");
                var name = sc.nextLine();

                System.out.println("Введи жанр");
                var genre = sc.next();

                System.out.println("Введи год выпуска");
                var year = sc.next();

                System.out.println("Введи режиссера");
                var producer = sc.next();

                var discController = new DiscController(name, genre, year, producer);
                if (discController.isNewDisc()) {

                    System.out.println("Такого диска нет!\n Хотите добавить?\n1-да\n2-нет ");
                    var num = sc.nextInt();
                    if (num == 1) {
                        menuDisc(4);
                    } else {
                        commonMenu();
                    }
                }


                if (question == 3) {
                    discController.deleteDisc();
                    if (discController.getCurrentDisc() == null) {
                        System.out.println("Диск успешно удален\n");
                    }
                } else if (question == 2) {
                    System.out.println(discController.search(name, genre, year, producer));
                }
            } catch (ArgumentNullException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private static void menuRental(int question) {
        Scanner sc = new Scanner(System.in);
        if (question == 1) {
            System.out.println("Введи фамилию");

            var surname = sc.next();
            System.out.println("Введи номер телефона");
            var phoneNumber = sc.next();
            try {
                var userController = new UserController(surname, phoneNumber);
                if (userController.isNewUser()) {
                    System.out.println("Такого человека нет!\n Хотите добавить?\n1-да\n2-нет ");
                    var numOfquestion = sc.nextInt();
                    if (numOfquestion == 1) {
                        menuUser(5);
                    } else {
                        commonMenu();
                    }
                }

                sc.nextLine();
                System.out.println("Введи название");

                var name = sc.nextLine();


                System.out.println("Введи жанр");
                var genre = sc.nextLine();


                System.out.println("Введи год выпуска");
                var year = sc.next();

                System.out.println("Введи режиссера");
                var producer = sc.next();

                var discController = new DiscController(name, genre, year, producer);
                if (discController.isNewDisc()) {

                    System.out.println("Такого диска нет!\n Хотите добавить?\n1-да\n2-нет ");
                    var num = sc.nextInt();
                    if (num == 1) {
                        menuDisc(4);
                    } else {
                        commonMenu();
                    }

                }
                if(discController.getCurrentDisc().isReady()){
                    var rentalcontroller = new RentalController(userController.getCurrentUser(), discController.getCurrentDisc());
                    if(rentalcontroller.getCurrentRental()==null){
                        System.out.println("Надо вернуть диск");
                    }else {
                        System.out.println("Диск успешно выдан в прокат");
                        discController.save();
                        System.out.println(rentalcontroller.getCurrentRental());
                    }
                }else
                {
                    System.out.println("Диск занят. Надо подождать сдачу");
                }



            } catch (ArgumentNullException e) {
                System.out.println(e.getMessage());
            }

        }
        if (question == 3) {
            var rentalController = new RentalController();
            for (var item:rentalController.loadRental()) {
                System.out.println(item);
            }

        }
        if (question == 2) {

            try {

                System.out.println("Введи название");
                var name = sc.nextLine();

                System.out.println("Введи жанр");
                var genre = sc.next();

                System.out.println("Введи год выпуска");
                var year = sc.next();

                System.out.println("Введи режиссера");
                var producer = sc.next();

                var discController = new DiscController(name, genre, year, producer);


                var rentalcontroller = new RentalController();
                rentalcontroller.returnedDisk(discController.getCurrentDisc());
                discController.save();
                System.out.println("Диск успешно сдан в прокат");


            } catch (ArgumentNullException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}



